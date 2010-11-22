package org.jggug.kobo.gircbot.core;

import java.util.ArrayList;
import java.util.List;
import org.jibble.pircbot.DccChat;
import org.jibble.pircbot.DccFileTransfer;
import org.jibble.pircbot.PircBot;
import org.jibble.pircbot.User;

public class GircBot extends PircBot implements IrcControl, IrcEventSource {

    private PrimaryMonitor primaryChecker;

    private GircBot(PrimaryMonitor primaryChecker) {
        this.primaryChecker = primaryChecker;
    }

    // ----------------------------------------
    // for IRC Event Source
    // ----------------------------------------

    private final List<IrcEventListener> listeners = new ArrayList<IrcEventListener>();

    void addIrcEventListener(IrcEventListener listener) {
        listeners.add(listener);
    }

    @Override
    public void onConnect() {
        if (!primaryChecker.isPrimaryGlobally()) {
            return;
        }
        for (IrcEventListener listener : listeners) {
            listener.onConnect();
        }
    }

    @Override
    public void onDisconnect() {
        for (IrcEventListener listener : listeners) {
            listener.onDisconnect();
        }
    }

    @Override
    public void onServerResponse(int code, String response) {
        for (IrcEventListener listener : listeners) {
            listener.onServerResponse(code, response);
        }
    }

    @Override
    public void onUserList(String channel, User[] users) {
        for (IrcEventListener listener : listeners) {
            listener.onUserList(channel, users);
        }
    }

    @Override
    public void onMessage(String channel, String sender, String login, String hostname, String message) {
        for (IrcEventListener listener : listeners) {
            listener.onMessage(channel, sender, login, hostname, message);
        }
    }

    @Override
    public void onPrivateMessage(String sender, String login, String hostname, String message) {
        for (IrcEventListener listener : listeners) {
            listener.onPrivateMessage(sender, login, hostname, message);
        }
    }

    @Override
    public void onAction(String sender, String login, String hostname, String target, String action) {
        for (IrcEventListener listener : listeners) {
            listener.onAction(sender, login, hostname, target, action);
        }
    }

    @Override
    public void onNotice(String sourceNick, String sourceLogin, String sourceHostname, String target, String notice) {
        for (IrcEventListener listener : listeners) {
            listener.onNotice(sourceNick, sourceLogin, sourceHostname, target, notice);
        }
    }

    @Override
    public void onJoin(String channel, String sender, String login, String hostname) {
        for (IrcEventListener listener : listeners) {
            listener.onJoin(channel, sender, login, hostname);
        }
    }

    @Override
    public void onPart(String channel, String sender, String login, String hostname) {
        for (IrcEventListener listener : listeners) {
            listener.onPart(channel, sender, login, hostname);
        }
    }

    @Override
    public void onNickChange(String oldNick, String login, String hostname, String newNick) {
        for (IrcEventListener listener : listeners) {
            listener.onNickChange(oldNick, login, hostname, newNick);
        }
    }

    @Override
    public void onKick(String channel, String kickerNick, String kickerLogin, String kickerHostname, String recipientNick, String reason) {
        for (IrcEventListener listener : listeners) {
            listener.onKick(channel, kickerNick, kickerLogin, kickerHostname, recipientNick, reason);
        }
    }

    @Override
    public void onQuit(String sourceNick, String sourceLogin, String sourceHostname, String reason) {
        for (IrcEventListener listener : listeners) {
            listener.onQuit(sourceNick, sourceLogin, sourceHostname, reason);
        }
    }

    @Override
    public void onTopic(String channel, String topic) {
        for (IrcEventListener listener : listeners) {
            listener.onTopic(channel, topic);
        }
    }

    @Override
    public void onTopic(String channel, String topic, String setBy, long date, boolean changed) {
        for (IrcEventListener listener : listeners) {
            listener.onTopic(channel, topic, setBy, date, changed);
        }
    }

    @Override
    public void onChannelInfo(String channel, int userCount, String topic) {
        for (IrcEventListener listener : listeners) {
            listener.onChannelInfo(channel, userCount, topic);
        }
    }

    @Override
    public void onMode(String channel, String sourceNick, String sourceLogin, String sourceHostname, String mode) {
        for (IrcEventListener listener : listeners) {
            listener.onMode(channel, sourceNick, sourceLogin, sourceHostname, mode);
        }
    }

    @Override
    public void onUserMode(String targetNick, String sourceNick, String sourceLogin, String sourceHostname, String mode) {
        for (IrcEventListener listener : listeners) {
            listener.onUserMode(targetNick, sourceNick, sourceLogin, sourceHostname, mode);
        }
    }

    @Override
    public void onOp(String channel, String sourceNick, String sourceLogin, String sourceHostname, String recipient) {
        for (IrcEventListener listener : listeners) {
            listener.onOp(channel, sourceNick, sourceLogin, sourceHostname, recipient);
        }
    }

    @Override
    public void onDeop(String channel, String sourceNick, String sourceLogin, String sourceHostname, String recipient) {
        for (IrcEventListener listener : listeners) {
            listener.onDeop(channel, sourceNick, sourceLogin, sourceHostname, recipient);
        }
    }

    @Override
    public void onVoice(String channel, String sourceNick, String sourceLogin, String sourceHostname, String recipient) {
        for (IrcEventListener listener : listeners) {
            listener.onVoice(channel, sourceNick, sourceLogin, sourceHostname, recipient);
        }
    }

    @Override
    public void onDeVoice(String channel, String sourceNick, String sourceLogin, String sourceHostname, String recipient) {
        for (IrcEventListener listener : listeners) {
            listener.onDeVoice(channel, sourceNick, sourceLogin, sourceHostname, recipient);
        }
    }

    @Override
    public void onSetChannelKey(String channel, String sourceNick, String sourceLogin, String sourceHostname, String key) {
        for (IrcEventListener listener : listeners) {
            listener.onSetChannelKey(channel, sourceNick, sourceLogin, sourceHostname, key);
        }
    }

    @Override
    public void onRemoveChannelKey(String channel, String sourceNick, String sourceLogin, String sourceHostname, String key) {
        for (IrcEventListener listener : listeners) {
            listener.onRemoveChannelKey(channel, sourceNick, sourceLogin, sourceHostname, key);
        }
    }

    @Override
    public void onSetChannelLimit(String channel, String sourceNick, String sourceLogin, String sourceHostname, int limit) {
        for (IrcEventListener listener : listeners) {
            listener.onSetChannelLimit(channel, sourceNick, sourceLogin, sourceHostname, limit);
        }
    }

    @Override
    public void onRemoveChannelLimit(String channel, String sourceNick, String sourceLogin, String sourceHostname) {
        for (IrcEventListener listener : listeners) {
            listener.onRemoveChannelLimit(channel, sourceNick, sourceLogin, sourceHostname);
        }
    }

    @Override
    public void onSetChannelBan(String channel, String sourceNick, String sourceLogin, String sourceHostname, String hostmask) {
        for (IrcEventListener listener : listeners) {
            listener.onSetChannelBan(channel, sourceNick, sourceLogin, sourceHostname, hostmask);
        }
    }

    @Override
    public void onRemoveChannelBan(String channel, String sourceNick, String sourceLogin, String sourceHostname, String hostmask) {
        for (IrcEventListener listener : listeners) {
            listener.onRemoveChannelBan(channel, sourceNick, sourceLogin, sourceHostname, hostmask);
        }
    }

    @Override
    public void onSetTopicProtection(String channel, String sourceNick, String sourceLogin, String sourceHostname) {
        for (IrcEventListener listener : listeners) {
            listener.onSetTopicProtection(channel, sourceNick, sourceLogin, sourceHostname);
        }
    }

    @Override
    public void onRemoveTopicProtection(String channel, String sourceNick, String sourceLogin, String sourceHostname) {
        for (IrcEventListener listener : listeners) {
            listener.onRemoveTopicProtection(channel, sourceNick, sourceLogin, sourceHostname);
        }
    }

    @Override
    public void onSetNoExternalMessages(String channel, String sourceNick, String sourceLogin, String sourceHostname) {
        for (IrcEventListener listener : listeners) {
            listener.onSetNoExternalMessages(channel, sourceNick, sourceLogin, sourceHostname);
        }
    }

    @Override
    public void onRemoveNoExternalMessages(String channel, String sourceNick, String sourceLogin, String sourceHostname) {
        for (IrcEventListener listener : listeners) {
            listener.onRemoveNoExternalMessages(channel, sourceNick, sourceLogin, sourceHostname);
        }
    }

    @Override
    public void onSetInviteOnly(String channel, String sourceNick, String sourceLogin, String sourceHostname) {
        for (IrcEventListener listener : listeners) {
            listener.onSetInviteOnly(channel, sourceNick, sourceLogin, sourceHostname);
        }
    }

    @Override
    public void onRemoveInviteOnly(String channel, String sourceNick, String sourceLogin, String sourceHostname) {
        for (IrcEventListener listener : listeners) {
            listener.onRemoveInviteOnly(channel, sourceNick, sourceLogin, sourceHostname);
        }
    }

    @Override
    public void onSetModerated(String channel, String sourceNick, String sourceLogin, String sourceHostname) {
        for (IrcEventListener listener : listeners) {
            listener.onSetModerated(channel, sourceNick, sourceLogin, sourceHostname);
        }
    }

    @Override
    public void onRemoveModerated(String channel, String sourceNick, String sourceLogin, String sourceHostname) {
        for (IrcEventListener listener : listeners) {
            listener.onRemoveModerated(channel, sourceNick, sourceLogin, sourceHostname);
        }
    }

    @Override
    public void onSetPrivate(String channel, String sourceNick, String sourceLogin, String sourceHostname) {
        for (IrcEventListener listener : listeners) {
            listener.onSetPrivate(channel, sourceNick, sourceLogin, sourceHostname);
        }
    }

    @Override
    public void onRemovePrivate(String channel, String sourceNick, String sourceLogin, String sourceHostname) {
        for (IrcEventListener listener : listeners) {
            listener.onRemovePrivate(channel, sourceNick, sourceLogin, sourceHostname);
        }
    }

    @Override
    public void onSetSecret(String channel, String sourceNick, String sourceLogin, String sourceHostname) {
        for (IrcEventListener listener : listeners) {
            listener.onSetSecret(channel, sourceNick, sourceLogin, sourceHostname);
        }
    }

    @Override
    public void onRemoveSecret(String channel, String sourceNick, String sourceLogin, String sourceHostname) {
        for (IrcEventListener listener : listeners) {
            listener.onRemoveSecret(channel, sourceNick, sourceLogin, sourceHostname);
        }
    }

    @Override
    public void onInvite(String targetNick, String sourceNick, String sourceLogin, String sourceHostname, String channel) {
        for (IrcEventListener listener : listeners) {
            listener.onInvite(targetNick, sourceNick, sourceLogin, sourceHostname, channel);
        }
    }

    @Override
    public void onDccSendRequest(String sourceNick, String sourceLogin, String sourceHostname, String filename, long address, int port, int size) {
        for (IrcEventListener listener : listeners) {
            listener.onDccSendRequest(sourceNick, sourceLogin, sourceHostname, filename, address, port, size);
        }
    }

    @Override
    public void onDccChatRequest(String sourceNick, String sourceLogin, String sourceHostname, long address, int port) {
        for (IrcEventListener listener : listeners) {
            listener.onDccChatRequest(sourceNick, sourceLogin, sourceHostname, address, port);
        }
    }

    @Override
    public void onIncomingFileTransfer(DccFileTransfer transfer) {
        for (IrcEventListener listener : listeners) {
            listener.onIncomingFileTransfer(transfer);
        }
    }

    @Override
    public void onFileTransferFinished(DccFileTransfer transfer, Exception e) {
        for (IrcEventListener listener : listeners) {
            listener.onFileTransferFinished(transfer, e);
        }
    }

    @Override
    public void onIncomingChatRequest(DccChat chat) {
        for (IrcEventListener listener : listeners) {
            listener.onIncomingChatRequest(chat);
        }
    }

    @Override
    public void onVersion(String sourceNick, String sourceLogin, String sourceHostname, String target) {
        for (IrcEventListener listener : listeners) {
            listener.onVersion(sourceNick, sourceLogin, sourceHostname, target);
        }
    }

    @Override
    public void onPing(String sourceNick, String sourceLogin, String sourceHostname, String target, String pingValue) {
        for (IrcEventListener listener : listeners) {
            listener.onPing(sourceNick, sourceLogin, sourceHostname, target, pingValue);
        }
    }

    @Override
    public void onServerPing(String response) {
        for (IrcEventListener listener : listeners) {
            listener.onServerPing(response);
        }
    }

    @Override
    public void onTime(String sourceNick, String sourceLogin, String sourceHostname, String target) {
        for (IrcEventListener listener : listeners) {
            listener.onTime(sourceNick, sourceLogin, sourceHostname, target);
        }
    }

    @Override
    public void onFinger(String sourceNick, String sourceLogin, String sourceHostname, String target) {
        for (IrcEventListener listener : listeners) {
            listener.onFinger(sourceNick, sourceLogin, sourceHostname, target);
        }
    }

    @Override
    public void onUnknown(String line) {
        for (IrcEventListener listener : listeners) {
            listener.onUnknown(line);
        }
    }

    // ----------------------------------------
    // for IRC Control
    // ----------------------------------------

}
