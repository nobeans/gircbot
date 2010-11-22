class LogAppender {

    void write(time, channel, nick, type, message) {
        println "$time $channel $nick $type $message"
    }

}

