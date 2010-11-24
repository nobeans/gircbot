package org.jggug.kobo.gircbot.irclog

import java.util.List
import java.security.MessageDigest
import javax.sql.DataSource
import org.postgresql.jdbc3.Jdbc3SimpleDataSource
import groovy.sql.Sql

class IrclogViewerDao {

    final requireds = ['channel', 'nick', 'type', 'message']

    private DataSource dataSource

    IrclogViewerDao(Map propMap = [:]) {
        dataSource = newDataSource(propMap)
    }

    String createInsertSql(Map map) { // TODO SqlInjection
        if (map.findAll{ it.key in requireds }.size() != requireds.size()) {
            throw new IllegalArgumentException("Invalid aruguments: $map")
        }
        def now = new Date()
        def timeSec = now.format("yyyy-MM-dd HH:mm:ss")
        def timeMsec = now.format("yyyy-MM-dd HH:mm:ss.SSS")
        def permaId = createPermaId(map + [time:timeSec])
        return """\
            |INSERT INTO irclog (id, channel_id, channel_name, message, nick, perma_id, time, type)
            |  SELECT 
            |    nextval('hibernate_sequence'),
            |    CASE
            |      WHEN EXISTS (SELECT 1 FROM channel WHERE name = E'${map.channel}')
            |        THEN (SELECT DISTINCT id FROM channel WHERE name = E'${map.channel}')
            |      ELSE NULL
            |    END,
            |    E'${map.channel}',
            |    E'${map.message}',
            |    E'${map.nick}',
            |    E'${permaId}',
            |    E'${timeMsec}',
            |    E'${map.type}'
            |  WHERE NOT EXISTS (SELECT 1 FROM irclog WHERE perma_id = '${permaId}');
            |""".stripMargin("|")
    }

    private static createPermaId(map) {
        def base = "${map.time},${map.channel},${map.nick},${map.type},${map.message}"
        def permaId = MessageDigest.getInstance("MD5").digest(base.getBytes("UTF-8")).collect{ String.format("%02x", it & 0xff) }.join("")
        assert permaId.size() == 32
        return permaId
    }

    void executeSql(String sql) {
        Sql.newInstance(dataSource).execute(sql)
    }

    List<String> getAllActiveChannelNames() { // TODO specify period condition within a month
        Sql.newInstance(dataSource).rows("SELECT channel_name FROM irclog GROUP BY channel_name").collect {
            it.channel_name
        }
    }

    private static DataSource newDataSource(Map propMap) {
        def defaultMap = [
            serverName: "localhost",
            portNumber: 5432,
            databaseName: "irclog",
            user: "postgres",
            password: "postgres",
        ]
        def map = defaultMap + propMap
        def dataSource = new Jdbc3SimpleDataSource()
        dataSource.serverName = map.serverName
        dataSource.portNumber = map.portNumber
        dataSource.databaseName = map.databaseName
        dataSource.user = map.user
        dataSource.password = map.password
        dataSource
    }
}
