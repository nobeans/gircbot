package org.jggug.kobo.gircbot.reactors;

import org.jggug.kobo.gircbot.core.MessageUtils;
import org.jggug.kobo.gircbot.core.Reactor;

public class InviteAndByeResponder extends Reactor {

    private static final String COMMAND_BYE = "!bye";

    @Override
    public void onInvite(String targetNick, String sourceNick, String sourceLogin, String sourceHostname, String channel) {
        ircControl.joinChannel(channel);
        ircControl.sendMessage(channel, MessageUtils.getMessage("reactors.InviteAndByeResponder.onInvite.join", sourceNick));
    }

    @Override
    public void onMessage(String channel, String sender, String login, String hostname, String message) {
        if (message.trim().equals(COMMAND_BYE)) {
            ircControl.sendMessage(channel, MessageUtils.getMessage("reactors.InviteAndByeResponder.onMessage.bye", sender));
            sleep(3000);
            ircControl.partChannel(channel);
        }
    }

}
