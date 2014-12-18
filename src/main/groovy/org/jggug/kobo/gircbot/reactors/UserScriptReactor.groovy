package org.jggug.kobo.gircbot.reactors

import groovy.transform.TupleConstructor
import groovy.util.logging.Commons
import org.jggug.kobo.gircbot.core.Reactor

@Commons
@TupleConstructor
class UserScriptReactor extends Reactor {

    File scriptDir

    @Override
    void onMessage(String channel, String sender, String login, String hostname, String message) {
        def tokens = message.trim().split(/\s+/) as List

        // If there are not enough message, just ignore. It's just a normal message, not a command.
        if (tokens.size() < 2) return

        // Do react if the command only for me
        def target = tokens.head()
        if (target != ircControl.nick) return // just ignore

        def commandList = tokens.tail()
        def command = commandList.head()
        def args = commandList.tail()

        // Avoiding directory traversal
        if (command.contains(".")) {
            ircControl.sendNotice(channel, "Pardon me? > ${sender}")
            return
        }

        def commandFile = new File(scriptDir, "${command}.groovy")
        if (!commandFile.exists()) {
            ircControl.sendNotice(channel, "${command}? No idea. > ${sender}")
            return
        }

        // Run a script
        def context = [
            channel: channel,
            sender: sender,
            login: login,
            hostname: hostname,
            message: message,
        ]
        def result = runScript(commandFile, args, context)

        // If the result isn't empty, send it to the channel.
        if (result) {
            ircControl.sendNotice(channel, result)
        }
    }

    private String runScript(File commandFile, List<String> args, Map<?, ?> context) {
        def formattedCommand = "command=${commandFile.name}, args=${args}, context=${context}"
        try {
            log.debug "Running $formattedCommand..."
            def binding = new Binding([
                ircControl: ircControl,
                args: args,
                context: context,
            ])
            def shell = new GroovyShell(binding)
            def result = shell.evaluate(commandFile)?.toString()
            log.debug "Done: $formattedCommand => $result"
            return result

        } catch (e) {
            log.warn "Failed to run a command: $formattedCommand", e
        }
    }
}
