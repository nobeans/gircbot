package org.jggug.kobo.gircbot.reactors

import org.jggug.kobo.gircbot.core.Reactor

public class Dictionary extends Reactor {

    File dictionaryFile
    String charset

    public Dictionary(File dictionaryFile, String charset = "UTF-8") {
        this.dictionaryFile = dictionaryFile
        this.charset = charset
    }

    @Override
    public void onMessage(String channel, String sender, String login, String hostname, String message) {
        loadDictionary().each { String pattern, String value ->
            if (message ==~ pattern) {
                def response = value.replaceAll(/#SENDER/, sender)
                ircControl.sendMessage(channel, response)
            }
        }
    }

    private Properties loadDictionary() {
        // dyanmically loading in every time
        def dictionary = new Properties()
        if (dictionaryFile.isFile()) {
            dictionaryFile.withReader(charset) { reader ->
                dictionary.load(reader)
            }
        }
        return dictionary
    }
}
