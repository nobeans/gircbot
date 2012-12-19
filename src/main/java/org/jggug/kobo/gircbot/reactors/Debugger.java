package org.jggug.kobo.gircbot.reactors;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jggug.kobo.gircbot.core.IrcControl;
import org.jggug.kobo.gircbot.core.Reactor;
import org.jibble.pircbot.DccChat;
import org.jibble.pircbot.DccFileTransfer;
import org.jibble.pircbot.User;

public class Debugger extends Reactor {

    private static final Log LOG = LogFactory.getLog(Debugger.class);

    @Override
    public void onConnect() {
        LOG.debug("onConnect()");
    }

    @Override
    public void onDisconnect() {
        LOG.debug("onDisconnect()");
    }

    @Override
    public void onServerResponse(int code, String response) {
        LOG.debug(String.format("onServerResponse(%d, %s)", code, response));
    }

    @Override
    public void onUserList(String channel, User[] users) {
        LOG.debug(String.format("onUserList(%s, %s)", channel, users));
    }

    @Override
    public void onMessage(String channel, String sender, String login, String hostname, String message) {
        LOG.debug(String.format("onMessage(%s, %s, %s, %s, %s)", channel, sender, login, hostname, message));
    }

    @Override
    public void onPrivateMessage(String sender, String login, String hostname, String message) {
        LOG.debug(String.format("onPrivateMessage(%s, %s, %s, %s)", sender, login, hostname, message));
    }

    @Override
    public void onAction(String sender, String login, String hostname, String target, String action) {
        LOG.debug(String.format("onAction(%s, %s, %s, %s, %s)", sender, login, hostname, target, action));
    }

    @Override
    public void onNotice(String sourceNick, String sourceLogin, String sourceHostname, String target, String notice) {
        LOG.debug(String.format("onNotice(%s, %s, %s, %s, %s)", sourceNick, sourceLogin, sourceHostname, target, notice));
    }

    @Override
    public void onJoin(String channel, String sender, String login, String hostname) {
        LOG.debug(String.format("onJoin(%s, %s, %s, %s)", channel, sender, login, hostname));
    }

    @Override
    public void onPart(String channel, String sender, String login, String hostname) {
        LOG.debug(String.format("onPart(%s, %s, %s, %s)", channel, sender, login, hostname));
    }

    @Override
    public void onNickChange(String oldNick, String login, String hostname, String newNick) {
        LOG.debug(String.format("onNickChange(%s, %s, %s, %s)", oldNick, login, hostname, newNick));
    }

    @Override
    public void onKick(String channel, String kickerNick, String kickerLogin, String kickerHostname, String recipientNick, String reason) {
        LOG.debug(String.format("onKick(%s, %s, %s, %s, %s)", channel, kickerNick, kickerLogin, kickerHostname, recipientNick, reason));
    }

    @Override
    public void onQuit(String sourceNick, String sourceLogin, String sourceHostname, String reason) {
        LOG.debug(String.format("onQuit(%s, %s, %s, %s)", sourceNick, sourceLogin, sourceHostname, reason));
    }

    @Override
    public void onTopic(String channel, String topic, String setBy, long date, boolean changed) {
        LOG.debug(String.format("onTopic(%s, %s, %s, %d, %s)", channel, topic, setBy, date, changed));
    }

    @Override
    public void onChannelInfo(String channel, int userCount, String topic) {
        LOG.debug(String.format("onChannelInfo(%s, %d, %s)", channel, userCount, topic));
    }

    @Override
    public void onMode(String channel, String sourceNick, String sourceLogin, String sourceHostname, String mode) {
        LOG.debug(String.format("onMode(%s, %s, %s, %s, %s)", channel, sourceNick, sourceLogin, sourceHostname, mode));
    }

    @Override
    public void onUserMode(String targetNick, String sourceNick, String sourceLogin, String sourceHostname, String mode) {
        LOG.debug(String.format("onUserMode(%s, %s, %s, %s, %s)", targetNick, sourceNick, sourceLogin, sourceHostname, mode));
    }

    @Override
    public void onOp(String channel, String sourceNick, String sourceLogin, String sourceHostname, String recipient) {
        LOG.debug(String.format("onOp(%s, %s, %s, %s, %s)", channel, sourceNick, sourceLogin, sourceHostname, recipient));
    }

    @Override
    public void onDeop(String channel, String sourceNick, String sourceLogin, String sourceHostname, String recipient) {
        LOG.debug(String.format("onDeop(%s, %s, %s, %s, %s)", channel, sourceNick, sourceLogin, sourceHostname, recipient));
    }

    @Override
    public void onVoice(String channel, String sourceNick, String sourceLogin, String sourceHostname, String recipient) {
        LOG.debug(String.format("onVoice(%s, %s, %s, %s, %s)", channel, sourceNick, sourceLogin, sourceHostname, recipient));
    }

    @Override
    public void onDeVoice(String channel, String sourceNick, String sourceLogin, String sourceHostname, String recipient) {
        LOG.debug(String.format("onDeVoice(%s, %s, %s, %s, %s)", channel, sourceNick, sourceLogin, sourceHostname, recipient));
    }

    @Override
    public void onSetChannelKey(String channel, String sourceNick, String sourceLogin, String sourceHostname, String key) {
        LOG.debug(String.format("onSetChannelKey(%s, %s, %s, %s, %s)", channel, sourceNick, sourceLogin, sourceHostname, key));
    }

    @Override
    public void onRemoveChannelKey(String channel, String sourceNick, String sourceLogin, String sourceHostname, String key) {
        LOG.debug(String.format("onRemoveChannelKey(%s, %s, %s, %s, %s)", channel, sourceNick, sourceLogin, sourceHostname, key));
    }

    @Override
    public void onSetChannelLimit(String channel, String sourceNick, String sourceLogin, String sourceHostname, int limit) {
        LOG.debug(String.format("onSetChannelLimit(%s, %s, %s, %s, %s)", channel, sourceNick, sourceLogin, sourceHostname, limit));
    }

    @Override
    public void onRemoveChannelLimit(String channel, String sourceNick, String sourceLogin, String sourceHostname) {
        LOG.debug(String.format("onRemoveChannelLimit(%s, %s, %s, %s)", channel, sourceNick, sourceLogin, sourceHostname));
    }

    @Override
    public void onSetChannelBan(String channel, String sourceNick, String sourceLogin, String sourceHostname, String hostmask) {
        LOG.debug(String.format("onSetChannelBan(%s, %s, %s, %s, %s)", channel, sourceNick, sourceLogin, sourceHostname, hostmask));
    }

    @Override
    public void onRemoveChannelBan(String channel, String sourceNick, String sourceLogin, String sourceHostname, String hostmask) {
        LOG.debug(String.format("onRemoveChannelBan(%s, %s, %s, %s, %s)", channel, sourceNick, sourceLogin, sourceHostname, hostmask));
    }

    @Override
    public void onSetTopicProtection(String channel, String sourceNick, String sourceLogin, String sourceHostname) {
        LOG.debug(String.format("onSetTopicProtection(%s, %s, %s, %s)", channel, sourceNick, sourceLogin, sourceHostname));
    }

    @Override
    public void onRemoveTopicProtection(String channel, String sourceNick, String sourceLogin, String sourceHostname) {
        LOG.debug(String.format("onRemoveTopicProtection(%s, %s, %s, %s)", channel, sourceNick, sourceLogin, sourceHostname));
    }

    @Override
    public void onSetNoExternalMessages(String channel, String sourceNick, String sourceLogin, String sourceHostname) {
        LOG.debug(String.format("onSetNoExternalMessages(%s, %s, %s, %s)", channel, sourceNick, sourceLogin, sourceHostname));
    }

    @Override
    public void onRemoveNoExternalMessages(String channel, String sourceNick, String sourceLogin, String sourceHostname) {
        LOG.debug(String.format("onRemoveNoExternalMessages(%s, %s, %s, %s)", channel, sourceNick, sourceLogin, sourceHostname));
    }

    @Override
    public void onSetInviteOnly(String channel, String sourceNick, String sourceLogin, String sourceHostname) {
        LOG.debug(String.format("onSetInviteOnly(%s, %s, %s, %s)", channel, sourceNick, sourceLogin, sourceHostname));
    }

    @Override
    public void onRemoveInviteOnly(String channel, String sourceNick, String sourceLogin, String sourceHostname) {
        LOG.debug(String.format("onRemoveInviteOnly(%s, %s, %s, %s)", channel, sourceNick, sourceLogin, sourceHostname));
    }

    @Override
    public void onSetModerated(String channel, String sourceNick, String sourceLogin, String sourceHostname) {
        LOG.debug(String.format("onSetModerated(%s, %s, %s, %s)", channel, sourceNick, sourceLogin, sourceHostname));
    }

    @Override
    public void onRemoveModerated(String channel, String sourceNick, String sourceLogin, String sourceHostname) {
        LOG.debug(String.format("onRemoveModerated(%s, %s, %s, %s)", channel, sourceNick, sourceLogin, sourceHostname));
    }

    @Override
    public void onSetPrivate(String channel, String sourceNick, String sourceLogin, String sourceHostname) {
        LOG.debug(String.format("onSetPrivate(%s, %s, %s, %s)", channel, sourceNick, sourceLogin, sourceHostname));
    }

    @Override
    public void onRemovePrivate(String channel, String sourceNick, String sourceLogin, String sourceHostname) {
        LOG.debug(String.format("onRemovePrivate(%s, %s, %s, %s)", channel, sourceNick, sourceLogin, sourceHostname));
    }

    @Override
    public void onSetSecret(String channel, String sourceNick, String sourceLogin, String sourceHostname) {
        LOG.debug(String.format("onSetSecret(%s, %s, %s, %s)", channel, sourceNick, sourceLogin, sourceHostname));
    }

    @Override
    public void onRemoveSecret(String channel, String sourceNick, String sourceLogin, String sourceHostname) {
        LOG.debug(String.format("onRemoveSecret(%s, %s, %s, %s)", channel, sourceNick, sourceLogin, sourceHostname));
    }

    @Override
    public void onInvite(String targetNick, String sourceNick, String sourceLogin, String sourceHostname, String channel) {
        LOG.debug(String.format("onInvite(%s, %s, %s, %s, %s)", targetNick, sourceNick, sourceLogin, sourceHostname, channel));
    }

    @Override
    public void onIncomingFileTransfer(DccFileTransfer transfer) {
        LOG.debug(String.format("onIncomingFileTransfer(%s)", transfer));
    }

    @Override
    public void onFileTransferFinished(DccFileTransfer transfer, Exception e) {
        LOG.debug(String.format("onFileTransferFinished(%s, %s)", transfer, e));
    }

    @Override
    public void onIncomingChatRequest(DccChat chat) {
        LOG.debug(String.format("onIncomingChatRequest(%s)", chat));
    }

    @Override
    public void onVersion(String sourceNick, String sourceLogin, String sourceHostname, String target) {
        LOG.debug(String.format("onVersion(%s, %s, %s, %s)", sourceNick, sourceLogin, sourceHostname, target));
    }

    @Override
    public void onPing(String sourceNick, String sourceLogin, String sourceHostname, String target, String pingValue) {
        LOG.debug(String.format("onPing(%s, %s, %s, %s, %s)", sourceNick, sourceLogin, sourceHostname, target, pingValue));
    }

    @Override
    public void onServerPing(String response) {
        LOG.debug(String.format("onServerPing(%s)", response));
    }

    @Override
    public void onTime(String sourceNick, String sourceLogin, String sourceHostname, String target) {
        LOG.debug(String.format("onTime(%s, %s, %S, %s)", sourceNick, sourceLogin, sourceHostname, target));
    }

    @Override
    public void onFinger(String sourceNick, String sourceLogin, String sourceHostname, String target) {
        LOG.debug(String.format("onFinger(%s, %s, %s, %s)", sourceNick, sourceLogin, sourceHostname, target));
    }

    @Override
    public void onUnknown(String line) {
        LOG.debug(String.format("onUnknown(%s)", line));
    }

}
