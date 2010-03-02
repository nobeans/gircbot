@Grab("pircbot:pircbot:1.4.2")
import org.jibble.pircbot.*

class LogBot extends PircBot {

    private appender

    public void onJoin(String channel, String sender, String login, String hostname) {
        append("JOIN", channel, sender, "* $sender (${login}@${hostname}) has joined $channel")
        op(channel, sender) // auto op provider
    }

    public void onMessage(String channel, String sender, String login, String hostname, String message) {
        append("PRIVMSG", channel, sender, message)

        // なると消失状態に対応するため、"!bye"発言に反応してPARTするようにしている。
        if (message ==~ /!bye/) {
            partChannel(channel, "またいつの日かお会いしましょう...")
        }
    }

    public void onNotice(String sourceNick, String sourceLogin, String sourceHostname, String target, String notice) {
        append("NOTICE", target, sourceNick, notice)
    }

    public void onMode(String channel, String sourceNick, String sourceLogin, String sourceHostname, String mode) {
        append("MODE", channel, sourceNick, "* " + sourceNick + " sets mode " + mode)
    }

    public void onPart(String channel, String sender, String login, String hostname) {
        append("PART", channel, sender, "* " + sender + " (" + login + "@" + hostname + ") has left " + channel)
    }

    public void onTopic(String channel, String topic, String setBy, long date, boolean changed) {
        if (changed) append("TOPIC", channel, setBy, topic)
    }

    public void onKick(String channel, String kickerNick, String kickerLogin, String kickerHostname, String recipientNick, String reason) {
        append("TOPIC", channel, kickerNick, "* " + recipientNick + " was kicked from " + channel + " by " + kickerNick)
    }

    // INVITEされればどこへでも。ただし入室キーが設定されてるチャンネルは無理。
    public void onInvite(String targetNick, String sourceNick, String sourceLogin, String sourceHostname, String channel) {
        joinChannel(channel)
        sendNotice(channel, "一生懸命ロギングするのでよろしくお願いします!")
    }

    public void onDisconnect() {
        while (!isConnected()) {
            try {
                reconnect()
                joinedChannel.each { ch -> joinChannel(ch) }
            } catch (Exception e) {
                try {
                    Thread.sleep(10000)
                } catch (InterruptedException e2) {
                    // Do nothing.
                }
            }
        }
    }

    private append(type, channel, nick, message) {
        String time = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())
        appender.write(time, channel, nick, type, message)
    }
}

