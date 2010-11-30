package org.jggug.kobo.gircbot.jobs

import org.jggug.kobo.gircbot.core.IrcControl
import org.jggug.kobo.gircbot.core.Job

public class Reminder extends Job {

    protected Reminder(IrcControl ircControl) {
        super(ircControl)
    }

    @Override
    public void invoke(Date time) {
        ircControl.sendMessage("#test", "Hey! I'm invoked at ${time}!")
    }

}
