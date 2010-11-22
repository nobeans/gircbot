@Grape("org.jggug.kobo:gircbot:*")
import org.jggug.kobo.gircbot.builder.*

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
        autoJoinTo = []
    }
    reactors = [
        new Reactor() {
            void privmsg(param) {
                println "DEBUG: ${param.message}"
            }
        }
    ]
    jobs = []
}.start()

