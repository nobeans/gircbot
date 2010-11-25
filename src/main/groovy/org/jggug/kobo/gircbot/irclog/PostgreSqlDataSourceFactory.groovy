package org.jggug.kobo.gircbot.irclog

import javax.sql.DataSource
import org.postgresql.jdbc3.Jdbc3SimpleDataSource

class PostgreSqlDataSourceFactory {

    static DataSource newInstance(Map propMap = [:]) {
        def defaultMap = [
            host: "localhost",
            port: 5432,
            database: "irclog",
            user: "postgres",
            password: "",
        ]
        def map = defaultMap + propMap
        def dataSource = new Jdbc3SimpleDataSource()
        dataSource.serverName = map.host
        dataSource.portNumber = map.port as int
        dataSource.databaseName = map.database
        dataSource.user = map.user
        dataSource.password = map.password
        dataSource
    }
}
