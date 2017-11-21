package config

object BotConfig {
  val config: AppConfig = AppConfigParser.parseConfig("configs/application.conf")

}
