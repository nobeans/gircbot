import org.jggug.kobo.gircbot.builder.GircBotBuilder
import org.jggug.kobo.gircbot.core.*
import org.jggug.kobo.gircbot.reactors.*

new GircBotBuilder(debug:true).config { IrcControl irc ->
    server {
        host "silver"
        port 6667
    }
    nick {
        name "cobot_"
        primaryOrder "cobot", "cobot_", "cobot__"
    }
    channel { autoJoinTo "#test", "#lounge" }
    reactors (
        new Reactor(irc) {
            void onMessage(String channel, String sender, String login, String hostname, String message) {
                irc.sendMessage "#test", "1> $channel $message"
                irc.sendMessage "ynak", "3> $channel $message"
            }
        },
        new OpDistributor(irc),
        new InviteAndByeResponder(irc),
        new Debugger(irc),
    )
    jobs()
}.start()
