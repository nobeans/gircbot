package org.jggug.kobo.gircbot.builder

import groovy.util.logging.Commons
import org.jggug.kobo.gircbot.core.GircBot
import org.jggug.kobo.gircbot.core.Job
import org.jggug.kobo.gircbot.core.MessageUtils
import org.jggug.kobo.gircbot.core.Reactor
import org.jggug.kobo.gircbot.core.TimeMonitor

@Commons
class GircBotBuilder {

    GircBot bot = new GircBot()
    TimeMonitor timeMonitor = new TimeMonitor()
    Map config = [:]

    GircBotBuilder() {
        bot = new GircBot()
    }

    GircBotBuilder config(Map config) {
        this.config = config
        return this
    }

    GircBotBuilder config(Closure clos) {
        expandClosure(clos)
        clos.call(bot)
        return this
    }

    private expandClosure(Closure c) {
        c.delegate = c
        c.metaClass {
            def namePath = []
            methodMissing { String name, args ->
                log.debug "Method: $name($args)"
                namePath << name
                def paramName = namePath.join(".")

                def closureArgs = args.findAll { it.class in Closure }
                if (closureArgs.size() > 1) {
                    throw new IllegalArgumentException("Allowed only one Closure argument per one method")
                } else if (closureArgs.size() == 1) {
                    log.debug "Found closure argument: $closureArgs in $paramName"
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

    private addConfig(String name, args) {
        log.debug "Parameter: $name = $args (${args.class.name})"
        if (!args) return
        if (args.size() == 1) {
            config[name] = args[0]
        } else if (args.size() > 1) {
            config[name] = args
        }
    }

    void start() {
        log.debug "Starting bot..."
        config.each { name, args ->
            log.debug "Config: $name = $args"
        }

        // Setup bot
        bot.name = config["nick"]
        bot.verbose = Boolean.valueOf(System.properties["gircbot.debug"])
        config["reactors"].each { Reactor reactor ->
            reactor.ircControl = bot
            bot.addIrcEventListener(reactor)
        }
        log.debug "Bot has the following reactors: ${config['reactors']}"

        // Connect to server
        bot.connect(config["server.host"], config["server.port"])
        config["channel.autoJoinTo"].each { String channelName ->
            bot.joinChannel(channelName)
            bot.sendNotice(channelName, MessageUtils.getMessage("autoJoined"))
        }
        log.debug "Bot is joined to channels: ${config['channel.autoJoinTo']}"

        // Setup timer jobs
        config["jobs"].each { Job job ->
            job.ircControl = bot
            timeMonitor.addTimeEventListener(job)
        }
        timeMonitor.start()
        log.debug "Timer jobs are started: ${config['jobs']}"

        log.debug "Now bot is running as ${bot.name}."
    }

    void stop() {
        bot.disconnect()
    }
}

