import org.jggug.kobo.gircbot.irclog.PostgreSqlDataSourceFactory;

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

new GircBotBuilder(debug:true).config { IrcControl irc ->
    server {
        host "silver"
        port 6667
    }
    nick {
        name "cobot_"
        primaryOrder "cobot", "cobot_", "cobot__"
    }
    channel { autoJoinTo "#test", "#lounge" }
    reactors (
        new Reactor(irc) {
            void onMessage(String channel, String sender, String login, String hostname, String message) {
                irc.sendMessage "#test", "1> $channel $message"
                irc.sendMessage "ynak", "3> $channel $message"
            }
        },
        new OpDistributor(irc),
        new InviteAndByeResponder(irc),
        new Debugger(irc),
        new Logger(irc, new IrclogViewerLogAppender(dao:dao, defaultChannel:"#lounge"))
    )
    jobs()
}.start()
