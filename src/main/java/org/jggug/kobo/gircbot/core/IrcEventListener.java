package org.jggug.kobo.gircbot.core;

import org.jibble.pircbot.DccChat;
import org.jibble.pircbot.DccFileTransfer;
import org.jibble.pircbot.User;

public interface IrcEventListener {

    void onConnect();

    void onDisconnect();

    void onServerResponse(int code, String response);

    void onUserList(String channel, User[] users);

    void onMessage(String channel, String sender, String login, String hostname, String message);

    void onPrivateMessage(String sender, String login, String hostname, String message);

    void onAction(String sender, String login, String hostname, String target, String action);

    void onNotice(String sourceNick, String sourceLogin, String sourceHostname, String target, String notice);

    void onJoin(String channel, String sender, String login, String hostname);

    void onPart(String channel, String sender, String login, String hostname);

    void onNickChange(String oldNick, String login, String hostname, String newNick);

    void onKick(String channel, String kickerNick, String kickerLogin, String kickerHostname, String recipientNick, String reason);

    void onQuit(String sourceNick, String sourceLogin, String sourceHostname, String reason);

    void onTopic(String channel, String topic);

    void onTopic(String channel, String topic, String setBy, long date, boolean changed);

    void onChannelInfo(String channel, int userCount, String topic);

    void onMode(String channel, String sourceNick, String sourceLogin, String sourceHostname, String mode);

    void onUserMode(String targetNick, String sourceNick, String sourceLogin, String sourceHostname, String mode);

    void onOp(String channel, String sourceNick, String sourceLogin, String sourceHostname, String recipient);

    void onDeop(String channel, String sourceNick, String sourceLogin, String sourceHostname, String recipient);

    void onVoice(String channel, String sourceNick, String sourceLogin, String sourceHostname, String recipient);

    void onDeVoice(String channel, String sourceNick, String sourceLogin, String sourceHostname, String recipient);

    void onSetChannelKey(String channel, String sourceNick, String sourceLogin, String sourceHostname, String key);

    void onRemoveChannelKey(String channel, String sourceNick, String sourceLogin, String sourceHostname, String key);

    void onSetChannelLimit(String channel, String sourceNick, String sourceLogin, String sourceHostname, int limit);

    void onRemoveChannelLimit(String channel, String sourceNick, String sourceLogin, String sourceHostname);

    void onSetChannelBan(String channel, String sourceNick, String sourceLogin, String sourceHostname, String hostmask);

    void onRemoveChannelBan(String channel, String sourceNick, String sourceLogin, String sourceHostname, String hostmask);

    void onSetTopicProtection(String channel, String sourceNick, String sourceLogin, String sourceHostname);

    void onRemoveTopicProtection(String channel, String sourceNick, String sourceLogin, String sourceHostname);

    void onSetNoExternalMessages(String channel, String sourceNick, String sourceLogin, String sourceHostname);

    void onRemoveNoExternalMessages(String channel, String sourceNick, String sourceLogin, String sourceHostname);

    void onSetInviteOnly(String channel, String sourceNick, String sourceLogin, String sourceHostname);

    void onRemoveInviteOnly(String channel, String sourceNick, String sourceLogin, String sourceHostname);

    void onSetModerated(String channel, String sourceNick, String sourceLogin, String sourceHostname);

    void onRemoveModerated(String channel, String sourceNick, String sourceLogin, String sourceHostname);

    void onSetPrivate(String channel, String sourceNick, String sourceLogin, String sourceHostname);

    void onRemovePrivate(String channel, String sourceNick, String sourceLogin, String sourceHostname);

    void onSetSecret(String channel, String sourceNick, String sourceLogin, String sourceHostname);

    void onRemoveSecret(String channel, String sourceNick, String sourceLogin, String sourceHostname);

    void onInvite(String targetNick, String sourceNick, String sourceLogin, String sourceHostname, String channel);

    void onDccSendRequest(String sourceNick, String sourceLogin, String sourceHostname, String filename, long address, int port, int size);

    void onDccChatRequest(String sourceNick, String sourceLogin, String sourceHostname, long address, int port);

    void onIncomingFileTransfer(DccFileTransfer transfer);

    void onFileTransferFinished(DccFileTransfer transfer, Exception e);

    void onIncomingChatRequest(DccChat chat);

    void onVersion(String sourceNick, String sourceLogin, String sourceHostname, String target);

    void onPing(String sourceNick, String sourceLogin, String sourceHostname, String target, String pingValue);

    void onServerPing(String response);

    void onTime(String sourceNick, String sourceLogin, String sourceHostname, String target);

    void onFinger(String sourceNick, String sourceLogin, String sourceHostname, String target);

    void onUnknown(String line);

}
