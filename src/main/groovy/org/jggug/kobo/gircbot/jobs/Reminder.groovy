package org.jggug.kobo.gircbot.jobs

import org.jggug.kobo.gircbot.core.Job

public class Reminder extends Job {

    File dataFile
    String charset

    public Reminder(File dataFile, String charset = "UTF-8") {
        this.dataFile = dataFile
        this.charset = charset
    }

    private Map loadData() {
        // dyanmically loading in every time
        if (!dataFile.isFile()) {
            return [:]
        }
        def data = new GroovyShell().evaluate(dataFile.text)
        return data
    }

    @Override
    public void invoke(Date time) {
        loadData().each { pattern, map ->
            if (time.format("yyyy-MM-dd HH:mm:ss") =~ pattern) {
                if (!map.message || !map.target) return
                def type = map.type ?: ""
                if (type.toUpperCase() == 'NOTICE') {
                    ircControl.sendNotice(map.target, "${map.message} > ${map.target}")
                } else {
                    ircControl.sendMessage(map.target, "${map.message} > ${map.target}")
                }
            }
        }
    }

}
