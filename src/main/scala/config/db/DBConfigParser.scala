package config.db

import com.typesafe.config.Config
import com.typesafe.scalalogging.LazyLogging

import scala.util.{Failure, Success, Try}

object DBConfigParser extends LazyLogging {
  def parse(conf: Config): DBConfig = {
    import config.Utils.UberConfig
    Try {
      val dbConfigPart = conf.getConfig("db")
      new DBConfig {
        override val dbConnString: String = dbConfigPart.getStringOption("dbConnectionString").getOrElse(DefaultDBConfig.dbConnString)
        override val dbLogin: String = dbConfigPart.getStringOption("dbLogin").getOrElse(DefaultDBConfig.dbLogin)
        override val dbPassword: String = dbConfigPart.getStringOption("dbPassword").getOrElse(DefaultDBConfig.dbPassword)
        override val dbWebServer: String = dbConfigPart.getStringOption("dbWebServer").getOrElse(DefaultDBConfig.dbWebServer)
      }
    } match {
      case Success(dbConfig) => dbConfig

      case Failure(exception) =>
        logger.info("Error reading db config from file")
        logger.info(exception.getMessage, exception.getCause)
        logger.info("Now using default db configuration")
        DefaultDBConfig
    }
  }
}
