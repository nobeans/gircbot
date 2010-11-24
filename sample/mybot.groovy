@GrabResolver(name="local", root="file://localhost/Users/ynak/.m2/repository")
@Grab("org.jggug.kobo:gircbot:0.1-SNAPSHOT")
import org.jggug.kobo.gircbot.builder.*
import org.jggug.kobo.gircbot.core.*
import org.jggug.kobo.gircbot.reactors.*

new GircBotBuilder(debug:true).config { IrcControl irc ->
    server {
        host "silver"
        port 6667
    }
    nick {
        name "cobot"
        primaryOrder "cobot", "cobot_", "cobot__"
    }
    channel { autoJoinTo "#test", "#lounge" }
    reactors (
        new Reactor(irc) {
            void onMessage(String channel, String sender, String login, String hostname, String message) {
                println "$channel $message"
            }
        },
        new InviteAndByeResponder(irc),
    )
    jobs()
}.start()
