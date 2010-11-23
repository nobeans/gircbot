package org.jggug.kobo.gircbot.core;

import org.jibble.pircbot.User;

public interface IrcControl {

    User[] getUsers(String channel);

    String getNick();

    void sendMessage(String target, String message);

    void sendNotice(String target, String notice);
}
