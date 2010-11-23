package org.jggug.kobo.gircbot.core;

import org.jibble.pircbot.User;

public interface IrcControl {

    User[] getUsers(String channel);

    User getUser(String channel);

    String getNick();

    void sendMessage(String target, String message);

    void sendNotice(String target, String notice);

    void op(String channel, String login);

    void joinChannel(String channel);

    void joinChannel(String channel, String key);

    void partChannel(String channel);


}
