package org.jggug.kobo.gircbot.reactors;

public interface LogAppender {

    @Deprecated
    void append(String message);

    void append(String type, String channel, String nick, String message);
}
