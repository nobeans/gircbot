package org.jggug.kobo.gircbot.irclog

import java.util.List
import java.security.MessageDigest
import javax.sql.DataSource
import groovy.sql.Sql

class IrclogViewerDao {

    final requireds = ['channel', 'nick', 'type', 'message']

    private DataSource dataSource

    IrclogViewerDao(DataSource dataSource) {
        this.dataSource = dataSource
    }

    String createInsertSql(Map map) { // TODO SqlInjection
        if (map.findAll{ it.key in requireds }.size() != requireds.size()) {
            throw new IllegalArgumentException("Invalid aruguments: $map")
        }
        def time = new Date().format("yyyy-MM-dd HH:mm:ss.SSS")
        def permaId = createPermaId(map + [time:time])
        return """\
            |INSERT INTO irclog (id, channel_id, channel_name, message, nick, perma_id, time, type)
            |  SELECT 
            |    nextval('hibernate_sequence'),
            |    CASE
            |      WHEN EXISTS (SELECT 1 FROM channel WHERE name = ${quoteValue(map.channel)})
            |        THEN (SELECT DISTINCT id FROM channel WHERE name = ${quoteValue(map.channel)})
            |      ELSE NULL
            |    END,
            |    ${quoteValue(map.channel)},
            |    ${quoteValue(map.message)},
            |    ${quoteValue(map.nick)},
            |    ${quoteValue(permaId)},
            |    ${quoteValue(time)},
            |    ${quoteValue(map.type)}
            |  WHERE NOT EXISTS (SELECT 1 FROM irclog WHERE perma_id = '${permaId}');
            |""".stripMargin("|")
    }

    private static quoteValue(value) {
        """E'${value.replaceAll("'", "''").replaceAll("\\\\", "\\\\\\\\")}'"""
    }

    private static createPermaId(map) {
        def base = "${map.time},${map.channel},${map.nick},${map.type},${map.message}"
        def permaId = MessageDigest.getInstance("MD5").digest(base.getBytes("UTF-8")).collect{ String.format("%02x", it & 0xff) }.join("")
        assert permaId.size() == 32
        return permaId
    }

    int executeSql(String sql) {
        Sql.newInstance(dataSource).executeUpdate(sql)
    }

    /**
     * return channel names which are registered to irclog-viewer and posted any messages in the month.
     * @return
     */
    List<String> getAllActiveChannelNames() {
        Sql.newInstance(dataSource).rows(
            "SELECT channel_name FROM irclog WHERE time > (CURRENT_TIMESTAMP - INTERVAL '2 weeks') AND channel_id IS NOT NULL GROUP BY channel_name ORDER BY channel_name"
        ).collect { it.channel_name }
    }
}
