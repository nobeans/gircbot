//@GrabResolver(name="local", root="file://localhost/Users/ynak/.m2/repository")
//@Grab("org.jggug.kobo:gircbot:0.1-SNAPSHOT")
import org.jggug.kobo.gircbot.builder.*
import org.jggug.kobo.gircbot.core.*

new GircBotBuilder(debug:true).config { irc ->
    server {
        host "silver"
        port 6667
    }
    nick {
        name "cobot_"
        primaryOrder "cobot", "cobot_", "cobot__"
    }
    channel { autoJoinTo "#test", "#lounge" }
    reactors new Reactor(irc) {
        void onMessage(String channel, String sender, String login, String hostname, String message) {
            println "$channel $message"
        }
    }
    jobs()
}.start()
