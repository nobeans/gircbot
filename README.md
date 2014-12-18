Gircbot
=======

Gircbot is a framework to make a IRC bot by Groovy.
It's powered by [Pircbot](http://www.jibble.org/pircbot.php).


Example
-------

You can write your IRC bot as follows:

```groovy
@GrabResolver(name="bintray", root="http://dl.bintray.com/nobeans/maven")
@Grab("org.jggug.kobo:gircbot:0.4")
import org.jggug.kobo.gircbot.builder.GircBotBuilder
import org.jggug.kobo.gircbot.core.*
import org.jggug.kobo.gircbot.jobs.*
import org.jggug.kobo.gircbot.reactors.*

new GircBotBuilder().config {
    server {
        host "localhost"
        port 6667
    }
    nick "gircbot"
    channel {
        autoJoinTo "#test", "#lounge"
    }
    reactors(
        // You can define a static response for a specific keyword in `dictionary.properties`.
        new Dictionary(new File("dictionary.properties")),

        // The boot distributes "OP (operator permission)" to a user
        new OpDistributor(),

        // Reacting for "/invite" and "!bye". this is requried when nobody has OP.
        new InviteAndByeResponder(),

        // You can add a script as `Reactor` into a `reactors` directory as you want.
        new UserScriptReactor(new File("reactors")),

        // You can add your local Reactor here.
        new Reactor() {
            void onMessage(String channel, String sender, String login, String hostname, String message) {
                if (message == "OK?") {
                    ircControl.sendNotice(channel, "OK! > $sender")
                }
            }
        },
    )
    jobs(
        // You can define a job which send a message at a specific date and time in `reminder.groovy`.
        new Reminder(new File("reminder.groovy")),

        // You can add a script as `Job` into a `job` directory as you want.
        new UserScriptJob(new File("jobs")),

        // You can add your local Job here.
        new Job() {
            void invoke(Date time) {
                if (time.format("yyyy-MM-dd HH:mm:ss") =~ /:00$/) {
                    ircControl.sendNotice(channel, "10 seconds passed.")
                }
            }
        },
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

