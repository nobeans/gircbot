Gircbot
=======

Gircbot is a framework to make a IRC bot by Groovy.
It's powered by [Pircbot](http://www.jibble.org/pircbot.php).


Example
-------

You can write your IRC bot as follows:

```groovy
@GrabResolver(name="bintray", root="http://dl.bintray.com/nobeans/maven")
@Grab("org.jggug.kobo:gircbot:0.2")
import org.jggug.kobo.gircbot.builder.GircBotBuilder
import org.jggug.kobo.gircbot.core.Reactor
import org.jggug.kobo.gircbot.reactors.Dictionary
import org.jggug.kobo.gircbot.reactors.OpDistributor
import org.jggug.kobo.gircbot.reactors.InviteAndByeResponder
import org.jggug.kobo.gircbot.jobs.Reminder

new GircBotBuilder().config {
    server {
        host "localhost"
        port 6667
    }
    nick "gircbot"
    channel { autoJoinTo "#test", "#lounge" }
    reactors(
        new Dictionary(new File(System.properties['user.home'], ".gircbot-dictionary")),
        new OpDistributor(),
        new InviteAndByeResponder(),
        new Reactor() {
            void onMessage(String channel, String sender, String login, String hostname, String message) {
                println "$channel $message"
            }
        },
    )
    jobs(
        new Reminder(new File(System.properties['user.home'], ".gircbot-reminder")),
    )
}.start()
```

You can also write this with configuration format. See [the sample script](https://github.com/nobeans/gircbot/blob/master/sample/configbot.groovy):


Code Status
-----------

[![Build Status](https://travis-ci.org/nobeans/gircbot.svg?branch=master)](https://travis-ci.org/nobeans/gircbot)


License
-------

Gircbot is released under the [Apache 2.0 License](http://www.apache.org/licenses/LICENSE-2.0)

