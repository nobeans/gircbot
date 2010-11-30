import org.jggug.kobo.gircbot.builder.GircBotBuilder
import org.jggug.kobo.gircbot.core.*
import org.jggug.kobo.gircbot.irclog.*
import org.jggug.kobo.gircbot.jobs.Reminder;
import org.jggug.kobo.gircbot.reactors.*
import com.sun.org.apache.regexp.internal.RE;

def dataSource = PostgreSqlDataSourceFactory.newInstance(
    host: "localhost",
    port: 5432,
    database: "irclog",
    user: "postgres",
    password: "",
)
def dao = new IrclogViewerDao(dataSource)

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
    jobs (
        new Reminder(irc),
    )
}.start()
