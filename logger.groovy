def bot = new LogBot(
    name:"logbot",
    appender:new LogAppender(),
)
bot.missingChannel = "#ynak"
bot.verbose = true // if true, verbose messages output to console.

bot.connect "silver"
bot.joinChannel "#ynak"

