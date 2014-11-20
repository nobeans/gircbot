@GrabResolver(name="bintray", root="http://dl.bintray.com/nobeans/maven")
@Grab("org.jggug.kobo:gircbot:0.2")
import org.jggug.kobo.gircbot.builder.*
import org.jggug.kobo.gircbot.core.*
import org.jggug.kobo.gircbot.reactors.*

new GircBotBuilder().config { IrcControl irc ->
    server {
        host "localhost"
        port 6667
    }
    nick "cobot"
    channel { autoJoinTo "#test", "#lounge" }
    reactors [
        new Reactor(irc) {
            void onMessage(String channel, String sender, String login, String hostname, String message) {
                println "$channel $message"
            }
        },
        new InviteAndByeResponder(irc),
    ]
    jobs()
}.start()
