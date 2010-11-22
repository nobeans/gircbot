//@Grape("org.jggug.kobo:gircbot:*")
import org.jggug.kobo.gircbot.builder.*

new GircBotBuilder(debug:false).config { irc ->
    server {
        host "localhost"
        port "6667"
    }
    nick {
        name "cobot"
        primaryOrder "cobot", "cobot_", "cobot__"
    }
    channel { autoJoinTo "#test" }
    reactors()
    jobs()
}.start()
