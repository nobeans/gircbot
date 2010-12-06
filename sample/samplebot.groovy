//@GrabResolver(name="local", root="file://localhost/Users/ynak/.m2/repository")
//@Grab("org.jggug.kobo:gircbot:0.1-SNAPSHOT")
import org.jggug.kobo.gircbot.builder.*
import org.jggug.kobo.gircbot.core.*
import org.jggug.kobo.gircbot.reactors.*
import org.jggug.kobo.gircbot.jobs.*
import org.jggug.kobo.gircbot.irclog.*

def dao = new IrclogViewerDao()

new GircBotBuilder(debug:true).config { irc ->
    server {
        host "silver"
        port 6667
    }
    nick {
        name "cobot_"
        primaryOrder "cobot", "cobot_", "cobot__"
    }
    channel {
        autoJoinTo = dao.getAllChannelNames()
    }
    reactors [
        new Logger(irc, new IrclogViewerLogAdapter(dao:dao)),
        new Dictionary(irc, new File(System.properties['user.home'], ".girc-dict")),
        new OpDistributor(irc),
        new InviteAndByeResponder(irc),
    ]
    jobs [
        new ModeKeeper(irc),
        new Reminder(irc),
    ]
}.start()

