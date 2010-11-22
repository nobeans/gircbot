package org.jggug.kobo.gircbot.core;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jibble.pircbot.User;

public class PrimaryMonitor {

    private static final Log LOG = LogFactory.getLog(PrimaryMonitor.class);

    protected String defaultChannel;
    protected List<String> orderedPrimaryNicks;
    protected IrcControl ircControl;

    public PrimaryMonitor(String defaultChannel, List<String> orderedPrimaryNicks, IrcControl ircControl) {
        this.defaultChannel = defaultChannel;
        this.orderedPrimaryNicks = orderedPrimaryNicks;
        this.ircControl = ircControl;
    }

    public boolean isPrimary(String channel) {
        String myNick = ircControl.getNick();

        List<String> joinedNicks = getJoinedNicks(channel);
        LOG.debug(String.format("joinedNicks: %s", joinedNicks));
        if (!joinedNicks.contains(myNick)) {
            throw new IllegalStateException(String.format("why isn't there this bot in joinedNicks? : %s in %s", myNick, joinedNicks));
        }

        LOG.debug(String.format("orderedPrimaryNicks: %s", orderedPrimaryNicks));
        if (!orderedPrimaryNicks.contains(myNick)) {
            throw new IllegalStateException(String.format("why isn't there this bot in orderedPrimaryNicks? : %s in %s", myNick, orderedPrimaryNicks));
        }

        List<String> workNicks = new ArrayList<String>(orderedPrimaryNicks);
        workNicks.retainAll(joinedNicks);
        LOG.debug(String.format("orderedPrimaryNicks: %s -> %s", orderedPrimaryNicks, workNicks));

        int index = workNicks.indexOf(myNick);
        LOG.debug(String.format("checking: %s in %s(index: %d)", myNick, workNicks, index));
        return index == 0; // when it's first element, this bot is primary. 
    }

    protected List<String> getJoinedNicks(String channel) {
        List<String> nicks = new ArrayList<String>();
        for (User user : ircControl.getUsers(channel)) {
            nicks.add(user.getNick());
        }
        return nicks;
    }

    public boolean isPrimaryGlobally() {
        return isPrimary(defaultChannel);
    }
}
