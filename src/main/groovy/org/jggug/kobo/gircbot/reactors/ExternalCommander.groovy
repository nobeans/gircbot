package org.jggug.kobo.gircbot.reactors

import org.jggug.kobo.gircbot.core.Reactor
import groovy.util.logging.Commons

@Commons
class ExternalCommander extends Reactor {

    File commandDir

    ExternalCommander(File commandDir) {
        this.commandDir = commandDir
    }

    @Override
    void onMessage(String channel, String sender, String login, String hostname, String message) {
        try {
            def tokens = message.trim().split(/\s+/)
            if (tokens.size() < 2) return

            def target = tokens.head()
            def command = tokens.tail().head()
            def params = tokens.tail().tail()
            if (target != ircControl.name) return // react if for me
            if (command.contains(".")) return // avoid directory traversal

            def commandFile = new File(commandDir, "${command}.groovy")
            if (commandFile.exists()) {
                log.debug "Running ${command}(${params.join(', ')})..."
                def binding = new Binding([
                    ircControl: ircControl,
                    context: [
                        channel: channel,
                        sender: sender,
                        login: login,
                        hostname: hostname,
                        message: message,
                    ],
                    params: params,
                ])
                def shell = new GroovyShell(binding)
                def result = shell.evaluate(commandFile)
                if (result) {
                    ircControl.sendNotice(channel, result)
                }
                log.debug "Done: ${command}(${params.join(', ')}) => $result"
            }
        } catch (e) {
            log.warn "Failed to run a command: $message", e
            //ircControl.sendNotice(channel, "ERROR: unexpected error: ${e.message}")
        }
    }
}
