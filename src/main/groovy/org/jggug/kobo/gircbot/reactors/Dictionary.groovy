package org.jggug.kobo.gircbot.reactors

import org.jggug.kobo.gircbot.core.*

public class Dictionary extends Reactor {

    File dictionaryFile
    String charset

    public Dictionary(IrcControl ircControl, File dictionaryFile, String charset = "UTF-8") {
        super(ircControl)
        this.dictionaryFile = dictionaryFile
        this.charset = charset
    }

    @Override
    public void onMessage(String channel, String sender, String login, String hostname, String message) {
        loadDictionary().each { pattern, value ->
            if (message ==~ pattern) {
                ircControl.sendMessage(channel, "${value} > ${sender}")
            }
        }
    }

    private Properties loadDictionary() {
        // dyanmically loading in every time
        def dictionary = new Properties()
        dictionaryFile.withReader(charset) { reader ->
            dictionary.load(reader)
        }
        return dictionary
    }
}
