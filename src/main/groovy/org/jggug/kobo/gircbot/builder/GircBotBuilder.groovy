package org.jggug.kobo.gircbot.builder

import org.jggug.kobo.gircbot.core.*

class GircBotBuilder {
    
    GircBot bot = new GircBot()
    boolean debug = false
    Map config = [:]
    
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
                debugLog "Parameter: $name($args)"
                config[paramName] = args
                namePath.pop()
            }
        }
    }
    
    void start() {
        debugLog "Starting bot..."
        config.each { name, args ->
            println "bot.$name = $args"
        }
        debugLog "Running bot"
    }
    
    void debugLog(message) {
        if (debug) println "[DEBUG] $message"
    }
}

