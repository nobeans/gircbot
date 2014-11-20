@GrabResolver(name="bintray", root="http://dl.bintray.com/nobeans/maven")
@Grab("org.jggug.kobo:gircbot:0.2")
import org.jggug.kobo.gircbot.builder.GircBotBuilder

// You can write this in a configuration file
def config = new ConfigSlurper().parse '''
import org.jggug.kobo.gircbot.core.Reactor
import org.jggug.kobo.gircbot.reactors.Dictionary
import org.jggug.kobo.gircbot.reactors.OpDistributor
import org.jggug.kobo.gircbot.reactors.InviteAndByeResponder
import org.jggug.kobo.gircbot.jobs.Reminder

gircbot {
    server {
        host = "localhost"
        port = 6667
    }
    nick = "gircbot"
    channel {
        autoJoinTo = ["#test", "#lounge"]
    }
    reactors = [
        new Dictionary(new File(System.properties['user.home'], ".gircbot-dictionary")),
        new OpDistributor(),
        new InviteAndByeResponder(),
        new Reactor() {
            void onMessage(String channel, String sender, String login, String hostname, String message) {
                println "$channel $message"
            }
        },
    ]
    jobs = [
        new Reminder(new File(System.properties['user.home'], ".gircbot-reminder")),
    ]
}
'''

new GircBotBuilder(config: config.gircbot.flatten()).start()

