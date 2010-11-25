package org.jggug.kobo.gircbot.irclog;

import org.junit.Test
import static org.junit.Assert.*

public class IrclogViewerDaoTest {

    @Test
    public void testname() throws Exception {
        IrclogViewerDao dao = new IrclogViewerDao(PostgreSqlDataSourceFactory.newInstance())
        
        def irclog = [channel:"#test1", nick:"nobeans", type:"PRIVMSG", message:"Hello!"]
        
        def sql = dao.createInsertSql(irclog)
        assert dao.executeSql(sql) == 1
    }

    @Test
    public void getAll() throws Exception {
        IrclogViewerDao dao = new IrclogViewerDao(PostgreSqlDataSourceFactory.newInstance())
        println dao.getAllActiveChannelNames()
    }
}
