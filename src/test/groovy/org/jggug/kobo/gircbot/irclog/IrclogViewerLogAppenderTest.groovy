package org.jggug.kobo.gircbot.irclog;

import org.junit.Test
import static org.junit.Assert.*

public class IrclogViewerLogAppenderTest {

    @Test
    public void testname() throws Exception {
        def dao = new IrclogViewerDao()
        IrclogViewerLogAppender appender = new IrclogViewerLogAppender(dao)
        appender.append("PRIVMSG", "#test", "nobeans", "Hello!")
    }
}
