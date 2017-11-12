package config

import config.db.DefaultDBConfig

object DefaultConfig extends AppConfig {
  override val dbConfig = DefaultDBConfig
}
