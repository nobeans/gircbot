@GrabResolver(name="cloudbees", root="https://repository-kobo.forge.cloudbees.com/snapshot/")
@Grab("org.jggug.kobo:gircbot:0.1-SNAPSHOT")
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
