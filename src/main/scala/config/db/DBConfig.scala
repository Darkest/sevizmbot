package config.db

trait DBConfig {
  val dbConnString: String
  val dbLogin: String
  val dbPassword: String
  val dbWebServer: String
}
