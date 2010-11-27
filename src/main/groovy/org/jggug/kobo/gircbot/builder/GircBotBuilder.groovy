package org.jggug.kobo.gircbot.builder

import java.util.List;
import org.jggug.kobo.gircbot.core.*

class GircBotBuilder {

    GircBot bot = new GircBot()
    boolean debug = false
    Map config = [:]

    GircBotBuilder() {
        bot = new GircBot()
    }

    def config(Closure clos) {
        expandClosure(clos)
        clos.call(bot)
        return this
    }

    def expandClosure(Closure c) {
        c.metaClass {
            def namePath = []
            methodMissing { String name, args ->
                debugLog "Method: $name($args)"
                namePath << name
                def paramName = namePath.join(".")

                def closureArgs = args.findAll{ it.class in Closure }
                if (closureArgs.size() > 1) {
                    throw new IllegalArgumentException("Allowed only one Closure argument per one method")
                }
                else if (closureArgs.size() == 1) {
                    debugLog "Found closure argument: $closureArgs in $paramName"
                    def clos = closureArgs[0]
                    expandClosure(clos)
                    clos.call(bot)
                    namePath.pop()
                    return clos
                }
                addConfig(paramName, args)
                namePath.pop()
            }
        }
    }

    def addConfig(name, args) {
        debugLog "Parameter: $name = $args (${args.class.name})"
        if (!args) return
        if (args.size() == 1) {
            config[name] = args[0]
        }
        else if (args.size() > 1) {
            config[name] = args
        }
    }

    void start() {
        debugLog "Starting bot..."
        config.each { name, args ->
            debugLog "Config: $name = $args"
        }
        bot.primaryMonitor = new PrimaryMonitor("#test", config["nick.primaryOrder"] as List, bot)
        config["reactors"].each { reactor ->
            bot.addIrcEventListener(reactor)
        }
        bot.name = config["nick.name"]
        bot.setVerbose(debug)
        bot.connect(config["server.host"], config["server.port"])
        config["channel.autoJoinTo"].each { channel ->
            bot.joinChannel(channel)
        }
        debugLog "Now bot is running as ${bot.name}."
    }

    void debugLog(message) {
        if (debug) println "[DEBUG] $message"
    }
}

