@Grape("org.jggug.kobo:gircbot:*")
import org.jggug.kobo.gircbot.builder.*
import org.jggug.kobo.gircbot.reactors.*
import org.jggug.kobo.gircbot.jobs.*
import org.jggug.kobo.gircbot.irclog.*

def dao = new IrclogViewerDao()

new GircBotBuilder().config { irc ->
    server {
        host = "localhost"
        port = "6667"
    }
    nick {
        self = "cobot"
        primary.order = ["cobot", "cobot_", "cobot__"]
    }
    channel {
        autoJoinTo = dao.getAllChannelNames()
    }
    reactors = [
        new Logger(irc, new IrclogViewerLogAdapter(dao:dao)),
        new Dictionary(irc, new File(System.properties['user.home'], ".girc-dict")),
        new OpDistributor(irc),
        new InviteAndByeResponder(irc),
    ]
    jobs = [
        new ModeKeeper(irc),
        new Reminder(irc),
    ]
}.start()
