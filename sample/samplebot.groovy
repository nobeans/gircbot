@GrabResolver(name="bintray", root="http://dl.bintray.com/nobeans/maven")
@Grab("org.jggug.kobo:gircbot:0.2")
import org.jggug.kobo.gircbot.builder.*
import org.jggug.kobo.gircbot.core.*
import org.jggug.kobo.gircbot.reactors.*
import org.jggug.kobo.gircbot.jobs.*

new GircBotBuilder().config { IrcControl irc ->
    server {
        host "localhost"
        port 6667
    }
    nick "cobot_"
    channel { autoJoinTo "#test", "#lounge" }
    reactors [
        new Dictionary(irc, new File(System.properties['user.home'], ".gircbot-dictionary")),
        new OpDistributor(irc),
        new InviteAndByeResponder(irc),
    ]
    jobs [
        new Reminder(irc, new File(System.properties['user.home'], ".gircbot-reminder")),
    ]
}.start()

