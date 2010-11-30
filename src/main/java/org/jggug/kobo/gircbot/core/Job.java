package org.jggug.kobo.gircbot.core;

import java.util.Date;

public class Job implements TimeEventListener {

    protected IrcControl ircControl;

    protected Job(IrcControl ircControl) {
        this.ircControl = ircControl;
    }

    @Override
    public void invoke(Date time) {
    }

}
