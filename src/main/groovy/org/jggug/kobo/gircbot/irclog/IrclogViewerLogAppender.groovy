package org.jggug.kobo.gircbot.irclog

import org.apache.commons.logging.Log
import org.apache.commons.logging.LogFactory
import org.jggug.kobo.gircbot.reactors.LogAppender

class IrclogViewerLogAppender implements LogAppender {

    private static final Log LOG = LogFactory.getLog(IrclogViewerLogAppender.class)

    private IrclogViewerDao dao

    IrclogViewerLogAppender(IrclogViewerDao dao) {
        this.dao = dao
    }

    @Override
    void append(String message) {
    }

    @Override
    void append(String type, String channel, String nick, String message) {
        def map = [
            type: type,
            channel: channel,
            nick: nick,
            message: message,
        ]
        String sql = dao.createInsertSql(map)
        try {
            dao.executeSql(sql)
        } catch (Exception e) {
            LOG.error("Failed to insert into database", e)
            
            // TODO to append individual sql files
            println(sql)
        }
    }

}
