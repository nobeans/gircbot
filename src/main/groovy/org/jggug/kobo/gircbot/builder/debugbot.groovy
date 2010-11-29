import org.jggug.kobo.gircbot.builder.GircBotBuilder
import org.jggug.kobo.gircbot.core.*
import org.jggug.kobo.gircbot.irclog.*
import org.jggug.kobo.gircbot.reactors.*

def dataSource = PostgreSqlDataSourceFactory.newInstance(
    host: "localhost",
    port: 5432,
    database: "irclog",
    user: "postgres",
    password: "",
)
def dao = new IrclogViewerDao(dataSource)
println dao.allActiveChannelNames

new GircBotBuilder(debug:true).config { IrcControl irc ->
    server {
        host "silver"
        port 6667
    }
    nick {
        name "cobot_"
        primaryOrder "cobot", "cobot_", "cobot__"
    }
    channel { autoJoinTo dao.allActiveChannelNames }
    reactors (
        new Logger(irc, new IrclogViewerLogAppender(dao:dao, defaultChannel:"#lounge")),
        new Dictionary(irc, new File("${System.properties['user.home']}/.gircbot-dict")),
        new OpDistributor(irc),
        new InviteAndByeResponder(irc),
        new Debugger(irc),
    )
    jobs()
}.start()
