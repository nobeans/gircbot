package org.jggug.kobo.gircbot.reactors;

public interface LogAppender {

    void append(String type, String channel, String nick, String message);
}
