package org.jggug.kobo.gircbot.jobs

import groovy.io.FileType
import groovy.transform.TupleConstructor
import groovy.util.logging.Commons
import org.jggug.kobo.gircbot.core.Job

@Commons
@TupleConstructor
class UserScriptJob extends Job {

    File scriptDir

    @Override
    void invoke(Date time) {
        if (!scriptDir.exists()) return
        scriptDir.eachFileRecurse(FileType.FILES) { File file ->
            runScript(file, time)
        }
    }

    private void runScript(File scriptFile, Date time) {
        if (!scriptFile.exists()) return
        def formattedCommand = "Running command=${scriptFile.name}, time=${time.format("yyyy-MM-dd HH:mm:ss")}"
        log.debug "Running ${formattedCommand}..."
        try {
            def binding = new Binding([
                ircControl: ircControl,
                context: [time: time],
            ])
            def shell = new GroovyShell(binding)
            shell.evaluate(scriptFile)
        } catch (e) {
            log.debug "Failed to run a script: ${formattedCommand}", e
        }
        log.debug "Done: ${formattedCommand}"
    }
}
