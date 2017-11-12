package config.db

object DefaultDBConfig extends DBConfig {
  override val dbConnString = "jdbc:h2:./data/dbfile"
  override val dbLogin: String = "sa"
  override val dbPassword: String = ""
  override val dbWebServer: String = "false"
}
