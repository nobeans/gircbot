@GrabResolver(name="bintray", root="http://dl.bintray.com/nobeans/maven")
@Grab("org.jggug.kobo:gircbot:0.2")
import org.jggug.kobo.gircbot.builder.*
import org.jggug.kobo.gircbot.core.*

new GircBotBuilder().config { IrcControl irc ->
    server {
        host "localhost"
        port 6667
    }
    nick "cobot_"
    channel { autoJoinTo "#test", "#lounge" }
    reactors()
    jobs()
}.start()
