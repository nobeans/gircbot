package org.jggug.kobo.gircbot.reactors;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jggug.kobo.gircbot.core.IrcControl;
import org.jggug.kobo.gircbot.core.MessageUtils;
import org.jggug.kobo.gircbot.core.Reactor;
import org.jibble.pircbot.User;

public class OpDistributor extends Reactor {

    private static final Log LOG = LogFactory.getLog(OpDistributor.class);

    @Override
    public void onJoin(String channel, String sender, String login, String hostname) {
        User user = ircControl.getUser(channel);
        if (user == null || !user.isOp()) {
            LOG.debug(String.format("cannot give op to %s in %s", sender, channel));
            return;
        }
        LOG.debug(String.format("giving op to %s in %s", sender, channel));
        ircControl.op(channel, sender);
    }

    @Override
    public void onOp(String channel, String sourceNick, String sourceLogin, String sourceHostname, String recipient) {
        if (recipient.equals(ircControl.getNick())) {
            ircControl.sendNotice(channel, MessageUtils.getMessage("reactors.opDistributor.onOp", sourceNick));
        }
    }

    @Override
    public void onDeop(String channel, String sourceNick, String sourceLogin, String sourceHostname, String recipient) {
        if (recipient.equals(ircControl.getNick())) {
            ircControl.sendNotice(channel, MessageUtils.getMessage("reactors.opDistributor.onDeop", sourceNick));
        }
    }

}
