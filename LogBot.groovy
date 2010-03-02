import java.util.regex.*
import java.text.SimpleDateFormat

@Grab("pircbot:pircbot:1.4.2")
import org.jibble.pircbot.*

public class LogBot extends PircBot {

    private writeLog(type, channel, nick, message) {
        String time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())
        println "$time $channel $nick $type $message"
    }

    public void onJoin(String channel, String sender, String login, String hostname) {
        writeLog("JOIN", channel, sender, "* $sender (${login}@${hostname}) has joined $channel")
        op(channel, sender) // auto op provider
    }

    public void onMessage(String channel, String sender, String login, String hostname, String message) {
        writeLog("PRIVMSG", channel, sender, message)

        // なると消失状態に対応するため、"!bye"発言に反応してPARTするようにしている。
        if (message ==~ /!bye/) {
            partChannel(channel, "またいつの日かお会いしましょう...")
        }
    }

    public void onNotice(String sourceNick, String sourceLogin, String sourceHostname, String target, String notice) {
        writeLog("NOTICE", target, sourceNick, notice)
    }

    public void onMode(String channel, String sourceNick, String sourceLogin, String sourceHostname, String mode) {
        writeLog("MODE", channel, sourceNick, "* " + sourceNick + " sets mode " + mode)
    }

    public void onPart(String channel, String sender, String login, String hostname) {
        writeLog("PART", channel, sender, "* " + sender + " (" + login + "@" + hostname + ") has left " + channel)
    }

    public void onTopic(String channel, String topic, String setBy, long date, boolean changed) {
        if (changed) writeLog("TOPIC", channel, setBy, topic)
    }

    public void onKick(String channel, String kickerNick, String kickerLogin, String kickerHostname, String recipientNick, String reason) {
        writeLog("TOPIC", channel, kickerNick, "* " + recipientNick + " was kicked from " + channel + " by " + kickerNick)
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

    //public void onNickChange(String oldNick, String login, String hostname, String newNick) {}
    //public void onPrivateMessage(String sender, String login, String hostname, String message) {}
    //public void onQuit(String sourceNick, String sourceLogin, String sourceHostname, String reason) {}
    //public void onTime(String sourceNick, String sourceLogin, String sourceHostname, String target) {}
}

