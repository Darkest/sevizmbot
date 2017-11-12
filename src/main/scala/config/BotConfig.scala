package config

object BotConfig {
  val config: AppConfig = AppConfigParser.parseConfig("application.conf")
}
