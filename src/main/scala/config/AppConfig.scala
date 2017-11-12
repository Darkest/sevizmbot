package config

import config.db.DBConfig

trait AppConfig {
  val dbConfig: DBConfig
}
