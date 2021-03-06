package org.jggug.kobo.gircbot.core;

import java.util.ArrayList;
import java.util.List;
import org.jibble.pircbot.DccChat;
import org.jibble.pircbot.DccFileTransfer;
import org.jibble.pircbot.PircBot;
import org.jibble.pircbot.User;

public class GircBot extends PircBot implements IrcControl, IrcEventSource {

    // ----------------------------------------
    // for IRC Event Source
    // ----------------------------------------

    private final List<IrcEventListener> listeners = new ArrayList<IrcEventListener>();

    public void addIrcEventListener(IrcEventListener listener) {
        listeners.add(listener);
    }

    @Override
    public void onConnect() {
        super.onConnect();
        for (IrcEventListener listener : listeners) {
            listener.onConnect();
        }
    }

    @Override
    public void onDisconnect() {
        super.onDisconnect();
        for (IrcEventListener listener : listeners) {
            listener.onDisconnect();
        }
    }

    @Override
    public void onServerResponse(int code, String response) {
        super.onServerResponse(code, response);
        for (IrcEventListener listener : listeners) {
            listener.onServerResponse(code, response);
        }
    }

    @Override
    public void onUserList(String channel, User[] users) {
        super.onUserList(channel, users);
        for (IrcEventListener listener : listeners) {
            listener.onUserList(channel, users);
        }
    }

    @Override
    public void onMessage(String channel, String sender, String login, String hostname, String message) {
        super.onMessage(channel, sender, login, hostname, message);
        for (IrcEventListener listener : listeners) {
            listener.onMessage(channel, sender, login, hostname, message);
        }
    }

    @Override
    public void onPrivateMessage(String sender, String login, String hostname, String message) {
        super.onPrivateMessage(sender, login, hostname, message);
        for (IrcEventListener listener : listeners) {
            listener.onPrivateMessage(sender, login, hostname, message);
        }
    }

    @Override
    public void onAction(String sender, String login, String hostname, String target, String action) {
        super.onAction(sender, login, hostname, target, action);
        for (IrcEventListener listener : listeners) {
            listener.onAction(sender, login, hostname, target, action);
        }
    }

    @Override
    public void onNotice(String sourceNick, String sourceLogin, String sourceHostname, String target, String notice) {
        super.onNotice(sourceNick, sourceLogin, sourceHostname, target, notice);
        for (IrcEventListener listener : listeners) {
            listener.onNotice(sourceNick, sourceLogin, sourceHostname, target, notice);
        }
    }

    @Override
    public void onJoin(String channel, String sender, String login, String hostname) {
        super.onJoin(channel, sender, login, hostname);
        for (IrcEventListener listener : listeners) {
            listener.onJoin(channel, sender, login, hostname);
        }
    }

    @Override
    public void onPart(String channel, String sender, String login, String hostname) {
        super.onPart(channel, sender, login, hostname);
        for (IrcEventListener listener : listeners) {
            listener.onPart(channel, sender, login, hostname);
        }
    }

    @Override
    public void onNickChange(String oldNick, String login, String hostname, String newNick) {
        super.onNickChange(oldNick, login, hostname, newNick);
        for (IrcEventListener listener : listeners) {
            listener.onNickChange(oldNick, login, hostname, newNick);
        }
    }

    @Override
    public void onKick(String channel, String kickerNick, String kickerLogin, String kickerHostname, String recipientNick, String reason) {
        super.onKick(channel, kickerNick, kickerLogin, kickerHostname, recipientNick, reason);
        for (IrcEventListener listener : listeners) {
            listener.onKick(channel, kickerNick, kickerLogin, kickerHostname, recipientNick, reason);
        }
    }

    @Override
    public void onQuit(String sourceNick, String sourceLogin, String sourceHostname, String reason) {
        super.onQuit(sourceNick, sourceLogin, sourceHostname, reason);
        for (IrcEventListener listener : listeners) {
            listener.onQuit(sourceNick, sourceLogin, sourceHostname, reason);
        }
    }

    @Override
    public void onTopic(String channel, String topic, String setBy, long date, boolean changed) {
        super.onTopic(channel, topic, setBy, date, changed);
        for (IrcEventListener listener : listeners) {
            listener.onTopic(channel, topic, setBy, date, changed);
        }
    }

    @Override
    public void onChannelInfo(String channel, int userCount, String topic) {
        super.onChannelInfo(channel, userCount, topic);
        for (IrcEventListener listener : listeners) {
            listener.onChannelInfo(channel, userCount, topic);
        }
    }

    @Override
    public void onMode(String channel, String sourceNick, String sourceLogin, String sourceHostname, String mode) {
        super.onMode(channel, sourceNick, sourceLogin, sourceHostname, mode);
        for (IrcEventListener listener : listeners) {
            listener.onMode(channel, sourceNick, sourceLogin, sourceHostname, mode);
        }
    }

    @Override
    public void onUserMode(String targetNick, String sourceNick, String sourceLogin, String sourceHostname, String mode) {
        super.onUserMode(targetNick, sourceNick, sourceLogin, sourceHostname, mode);
        for (IrcEventListener listener : listeners) {
            listener.onUserMode(targetNick, sourceNick, sourceLogin, sourceHostname, mode);
        }
    }

    @Override
    public void onOp(String channel, String sourceNick, String sourceLogin, String sourceHostname, String recipient) {
        super.onOp(channel, sourceNick, sourceLogin, sourceHostname, recipient);
        for (IrcEventListener listener : listeners) {
            listener.onOp(channel, sourceNick, sourceLogin, sourceHostname, recipient);
        }
    }

    @Override
    public void onDeop(String channel, String sourceNick, String sourceLogin, String sourceHostname, String recipient) {
        super.onDeop(channel, sourceNick, sourceLogin, sourceHostname, recipient);
        for (IrcEventListener listener : listeners) {
            listener.onDeop(channel, sourceNick, sourceLogin, sourceHostname, recipient);
        }
    }

    @Override
    public void onVoice(String channel, String sourceNick, String sourceLogin, String sourceHostname, String recipient) {
        super.onVoice(channel, sourceNick, sourceLogin, sourceHostname, recipient);
        for (IrcEventListener listener : listeners) {
            listener.onVoice(channel, sourceNick, sourceLogin, sourceHostname, recipient);
        }
    }

    @Override
    public void onDeVoice(String channel, String sourceNick, String sourceLogin, String sourceHostname, String recipient) {
        super.onDeVoice(channel, sourceNick, sourceLogin, sourceHostname, recipient);
        for (IrcEventListener listener : listeners) {
            listener.onDeVoice(channel, sourceNick, sourceLogin, sourceHostname, recipient);
        }
    }

    @Override
    public void onSetChannelKey(String channel, String sourceNick, String sourceLogin, String sourceHostname, String key) {
        super.onSetChannelKey(channel, sourceNick, sourceLogin, sourceHostname, key);
        for (IrcEventListener listener : listeners) {
            listener.onSetChannelKey(channel, sourceNick, sourceLogin, sourceHostname, key);
        }
    }

    @Override
    public void onRemoveChannelKey(String channel, String sourceNick, String sourceLogin, String sourceHostname, String key) {
        super.onRemoveChannelKey(channel, sourceNick, sourceLogin, sourceHostname, key);
        for (IrcEventListener listener : listeners) {
            listener.onRemoveChannelKey(channel, sourceNick, sourceLogin, sourceHostname, key);
        }
    }

    @Override
    public void onSetChannelLimit(String channel, String sourceNick, String sourceLogin, String sourceHostname, int limit) {
        super.onSetChannelLimit(channel, sourceNick, sourceLogin, sourceHostname, limit);
        for (IrcEventListener listener : listeners) {
            listener.onSetChannelLimit(channel, sourceNick, sourceLogin, sourceHostname, limit);
        }
    }

    @Override
    public void onRemoveChannelLimit(String channel, String sourceNick, String sourceLogin, String sourceHostname) {
        super.onRemoveChannelLimit(channel, sourceNick, sourceLogin, sourceHostname);
        for (IrcEventListener listener : listeners) {
            listener.onRemoveChannelLimit(channel, sourceNick, sourceLogin, sourceHostname);
        }
    }

    @Override
    public void onSetChannelBan(String channel, String sourceNick, String sourceLogin, String sourceHostname, String hostmask) {
        super.onSetChannelBan(channel, sourceNick, sourceLogin, sourceHostname, hostmask);
        for (IrcEventListener listener : listeners) {
            listener.onSetChannelBan(channel, sourceNick, sourceLogin, sourceHostname, hostmask);
        }
    }

    @Override
    public void onRemoveChannelBan(String channel, String sourceNick, String sourceLogin, String sourceHostname, String hostmask) {
        super.onRemoveChannelBan(channel, sourceNick, sourceLogin, sourceHostname, hostmask);
        for (IrcEventListener listener : listeners) {
            listener.onRemoveChannelBan(channel, sourceNick, sourceLogin, sourceHostname, hostmask);
        }
    }

    @Override
    public void onSetTopicProtection(String channel, String sourceNick, String sourceLogin, String sourceHostname) {
        super.onSetTopicProtection(channel, sourceNick, sourceLogin, sourceHostname);
        for (IrcEventListener listener : listeners) {
            listener.onSetTopicProtection(channel, sourceNick, sourceLogin, sourceHostname);
        }
    }

    @Override
    public void onRemoveTopicProtection(String channel, String sourceNick, String sourceLogin, String sourceHostname) {
        super.onRemoveTopicProtection(channel, sourceNick, sourceLogin, sourceHostname);
        for (IrcEventListener listener : listeners) {
            listener.onRemoveTopicProtection(channel, sourceNick, sourceLogin, sourceHostname);
        }
    }

    @Override
    public void onSetNoExternalMessages(String channel, String sourceNick, String sourceLogin, String sourceHostname) {
        super.onSetNoExternalMessages(channel, sourceNick, sourceLogin, sourceHostname);
        for (IrcEventListener listener : listeners) {
            listener.onSetNoExternalMessages(channel, sourceNick, sourceLogin, sourceHostname);
        }
    }

    @Override
    public void onRemoveNoExternalMessages(String channel, String sourceNick, String sourceLogin, String sourceHostname) {
        super.onRemoveNoExternalMessages(channel, sourceNick, sourceLogin, sourceHostname);
        for (IrcEventListener listener : listeners) {
            listener.onRemoveNoExternalMessages(channel, sourceNick, sourceLogin, sourceHostname);
        }
    }

    @Override
    public void onSetInviteOnly(String channel, String sourceNick, String sourceLogin, String sourceHostname) {
        super.onSetInviteOnly(channel, sourceNick, sourceLogin, sourceHostname);
        for (IrcEventListener listener : listeners) {
            listener.onSetInviteOnly(channel, sourceNick, sourceLogin, sourceHostname);
        }
    }

    @Override
    public void onRemoveInviteOnly(String channel, String sourceNick, String sourceLogin, String sourceHostname) {
        super.onRemoveInviteOnly(channel, sourceNick, sourceLogin, sourceHostname);
        for (IrcEventListener listener : listeners) {
            listener.onRemoveInviteOnly(channel, sourceNick, sourceLogin, sourceHostname);
        }
    }

    @Override
    public void onSetModerated(String channel, String sourceNick, String sourceLogin, String sourceHostname) {
        super.onSetModerated(channel, sourceNick, sourceLogin, sourceHostname);
        for (IrcEventListener listener : listeners) {
            listener.onSetModerated(channel, sourceNick, sourceLogin, sourceHostname);
        }
    }

    @Override
    public void onRemoveModerated(String channel, String sourceNick, String sourceLogin, String sourceHostname) {
        super.onRemoveModerated(channel, sourceNick, sourceLogin, sourceHostname);
        for (IrcEventListener listener : listeners) {
            listener.onRemoveModerated(channel, sourceNick, sourceLogin, sourceHostname);
        }
    }

    @Override
    public void onSetPrivate(String channel, String sourceNick, String sourceLogin, String sourceHostname) {
        super.onSetPrivate(channel, sourceNick, sourceLogin, sourceHostname);
        for (IrcEventListener listener : listeners) {
            listener.onSetPrivate(channel, sourceNick, sourceLogin, sourceHostname);
        }
    }

    @Override
    public void onRemovePrivate(String channel, String sourceNick, String sourceLogin, String sourceHostname) {
        super.onRemovePrivate(channel, sourceNick, sourceLogin, sourceHostname);
        for (IrcEventListener listener : listeners) {
            listener.onRemovePrivate(channel, sourceNick, sourceLogin, sourceHostname);
        }
    }

    @Override
    public void onSetSecret(String channel, String sourceNick, String sourceLogin, String sourceHostname) {
        super.onSetSecret(channel, sourceNick, sourceLogin, sourceHostname);
        for (IrcEventListener listener : listeners) {
            listener.onSetSecret(channel, sourceNick, sourceLogin, sourceHostname);
        }
    }

    @Override
    public void onRemoveSecret(String channel, String sourceNick, String sourceLogin, String sourceHostname) {
        super.onRemoveSecret(channel, sourceNick, sourceLogin, sourceHostname);
        for (IrcEventListener listener : listeners) {
            listener.onRemoveSecret(channel, sourceNick, sourceLogin, sourceHostname);
        }
    }

    @Override
    public void onInvite(String targetNick, String sourceNick, String sourceLogin, String sourceHostname, String channel) {
        super.onInvite(targetNick, sourceNick, sourceLogin, sourceHostname, channel);
        // MEMO: Here, listeners should be invoked always because "invite" is used when bot isn't in the channel.
        for (IrcEventListener listener : listeners) {
            listener.onInvite(targetNick, sourceNick, sourceLogin, sourceHostname, channel);
        }
    }

    @Override
    public void onIncomingFileTransfer(DccFileTransfer transfer) {
        super.onIncomingFileTransfer(transfer);
        for (IrcEventListener listener : listeners) {
            listener.onIncomingFileTransfer(transfer);
        }
    }

    @Override
    public void onFileTransferFinished(DccFileTransfer transfer, Exception e) {
        super.onFileTransferFinished(transfer, e);
        for (IrcEventListener listener : listeners) {
            listener.onFileTransferFinished(transfer, e);
        }
    }

    @Override
    public void onIncomingChatRequest(DccChat chat) {
        super.onIncomingChatRequest(chat);
        for (IrcEventListener listener : listeners) {
            listener.onIncomingChatRequest(chat);
        }
    }

    @Override
    public void onVersion(String sourceNick, String sourceLogin, String sourceHostname, String target) {
        super.onVersion(sourceNick, sourceLogin, sourceHostname, target);
        for (IrcEventListener listener : listeners) {
            listener.onVersion(sourceNick, sourceLogin, sourceHostname, target);
        }
    }

    @Override
    public void onPing(String sourceNick, String sourceLogin, String sourceHostname, String target, String pingValue) {
        super.onPing(sourceNick, sourceLogin, sourceHostname, target, pingValue);
        for (IrcEventListener listener : listeners) {
            listener.onPing(sourceNick, sourceLogin, sourceHostname, target, pingValue);
        }
    }

    @Override
    public void onServerPing(String response) {
        super.onServerPing(response);
        for (IrcEventListener listener : listeners) {
            listener.onServerPing(response);
        }
    }

    @Override
    public void onTime(String sourceNick, String sourceLogin, String sourceHostname, String target) {
        super.onTime(sourceNick, sourceLogin, sourceHostname, target);
        for (IrcEventListener listener : listeners) {
            listener.onTime(sourceNick, sourceLogin, sourceHostname, target);
        }
    }

    @Override
    public void onFinger(String sourceNick, String sourceLogin, String sourceHostname, String target) {
        super.onFinger(sourceNick, sourceLogin, sourceHostname, target);
        for (IrcEventListener listener : listeners) {
            listener.onFinger(sourceNick, sourceLogin, sourceHostname, target);
        }
    }

    @Override
    public void onUnknown(String line) {
        super.onUnknown(line);
        for (IrcEventListener listener : listeners) {
            listener.onUnknown(line);
        }
    }

    // ---------------------------------------
    // Helper methods
    // ---------------------------------------

    @Override
    public User getUser(String channel) {
        String nick = getNick();
        for (User user : getUsers(channel)) {
            if (user.getNick().equals(nick)) {
                return user;
            }
        }
        return null;
    }
}
