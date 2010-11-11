@Grab('postgresql:postgresql:*')
import org.postgresql.jdbc3.Jdbc3SimpleDataSource
import javax.sql.DataSource

class DataSourceFactory {

    DataSource newInstance(Map propMap) {
        def dataSource = new Jdbc3SimpleDataSource()
        def defaultMap = {
            serverName = "silver"
            portNumber = 5432
            databaseName = "irclog"
            user = "postgres"
            password = "postgres"
        }
        dataSource.properties = defaultMap + propMap
        dataSource
    }

}
