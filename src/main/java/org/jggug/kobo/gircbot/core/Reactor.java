package org.jggug.kobo.gircbot.core;

import org.jibble.pircbot.DccChat;
import org.jibble.pircbot.DccFileTransfer;
import org.jibble.pircbot.User;

public abstract class Reactor implements IrcEventListener {

    protected final IrcControl ircControl;

    protected Reactor(IrcControl ircControl) {
        this.ircControl = ircControl;
    }

    @Override
    public void onConnect() {
    }

    @Override
    public void onDisconnect() {
    }

    @Override
    public void onServerResponse(int code, String response) {
    }

    @Override
    public void onUserList(String channel, User[] users) {
    }

    @Override
    public void onMessage(String channel, String sender, String login, String hostname, String message) {
    }

    @Override
    public void onPrivateMessage(String sender, String login, String hostname, String message) {
    }

    @Override
    public void onAction(String sender, String login, String hostname, String target, String action) {
    }

    @Override
    public void onNotice(String sourceNick, String sourceLogin, String sourceHostname, String target, String notice) {
    }

    @Override
    public void onJoin(String channel, String sender, String login, String hostname) {
    }

    @Override
    public void onPart(String channel, String sender, String login, String hostname) {
    }

    @Override
    public void onNickChange(String oldNick, String login, String hostname, String newNick) {
    }

    @Override
    public void onKick(String channel, String kickerNick, String kickerLogin, String kickerHostname, String recipientNick, String reason) {
    }

    @Override
    public void onQuit(String sourceNick, String sourceLogin, String sourceHostname, String reason) {
    }

    @Override
    public void onTopic(String channel, String topic, String setBy, long date, boolean changed) {
    }

    @Override
    public void onChannelInfo(String channel, int userCount, String topic) {
    }

    @Override
    public void onMode(String channel, String sourceNick, String sourceLogin, String sourceHostname, String mode) {
    }

    @Override
    public void onUserMode(String targetNick, String sourceNick, String sourceLogin, String sourceHostname, String mode) {
    }

    @Override
    public void onOp(String channel, String sourceNick, String sourceLogin, String sourceHostname, String recipient) {
    }

    @Override
    public void onDeop(String channel, String sourceNick, String sourceLogin, String sourceHostname, String recipient) {
    }

    @Override
    public void onVoice(String channel, String sourceNick, String sourceLogin, String sourceHostname, String recipient) {
    }

    @Override
    public void onDeVoice(String channel, String sourceNick, String sourceLogin, String sourceHostname, String recipient) {
    }

    @Override
    public void onSetChannelKey(String channel, String sourceNick, String sourceLogin, String sourceHostname, String key) {
    }

    @Override
    public void onRemoveChannelKey(String channel, String sourceNick, String sourceLogin, String sourceHostname, String key) {
    }

    @Override
    public void onSetChannelLimit(String channel, String sourceNick, String sourceLogin, String sourceHostname, int limit) {
    }

    @Override
    public void onRemoveChannelLimit(String channel, String sourceNick, String sourceLogin, String sourceHostname) {
    }

    @Override
    public void onSetChannelBan(String channel, String sourceNick, String sourceLogin, String sourceHostname, String hostmask) {
    }

    @Override
    public void onRemoveChannelBan(String channel, String sourceNick, String sourceLogin, String sourceHostname, String hostmask) {
    }

    @Override
    public void onSetTopicProtection(String channel, String sourceNick, String sourceLogin, String sourceHostname) {
    }

    @Override
    public void onRemoveTopicProtection(String channel, String sourceNick, String sourceLogin, String sourceHostname) {
    }

    @Override
    public void onSetNoExternalMessages(String channel, String sourceNick, String sourceLogin, String sourceHostname) {
    }

    @Override
    public void onRemoveNoExternalMessages(String channel, String sourceNick, String sourceLogin, String sourceHostname) {
    }

    @Override
    public void onSetInviteOnly(String channel, String sourceNick, String sourceLogin, String sourceHostname) {
    }

    @Override
    public void onRemoveInviteOnly(String channel, String sourceNick, String sourceLogin, String sourceHostname) {
    }

    @Override
    public void onSetModerated(String channel, String sourceNick, String sourceLogin, String sourceHostname) {
    }

    @Override
    public void onRemoveModerated(String channel, String sourceNick, String sourceLogin, String sourceHostname) {
    }

    @Override
    public void onSetPrivate(String channel, String sourceNick, String sourceLogin, String sourceHostname) {
    }

    @Override
    public void onRemovePrivate(String channel, String sourceNick, String sourceLogin, String sourceHostname) {
    }

    @Override
    public void onSetSecret(String channel, String sourceNick, String sourceLogin, String sourceHostname) {
    }

    @Override
    public void onRemoveSecret(String channel, String sourceNick, String sourceLogin, String sourceHostname) {
    }

    @Override
    public void onInvite(String targetNick, String sourceNick, String sourceLogin, String sourceHostname, String channel) {
    }

    @Override
    public void onIncomingFileTransfer(DccFileTransfer transfer) {
    }

    @Override
    public void onFileTransferFinished(DccFileTransfer transfer, Exception e) {
    }

    @Override
    public void onIncomingChatRequest(DccChat chat) {
    }

    @Override
    public void onVersion(String sourceNick, String sourceLogin, String sourceHostname, String target) {
    }

    @Override
    public void onPing(String sourceNick, String sourceLogin, String sourceHostname, String target, String pingValue) {
    }

    @Override
    public void onServerPing(String response) {
    }

    @Override
    public void onTime(String sourceNick, String sourceLogin, String sourceHostname, String target) {
    }

    @Override
    public void onFinger(String sourceNick, String sourceLogin, String sourceHostname, String target) {
    }

    @Override
    public void onUnknown(String line) {
    }

}
