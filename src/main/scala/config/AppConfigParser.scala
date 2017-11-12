package config

import java.io.File

import com.typesafe.config.ConfigFactory
import com.typesafe.scalalogging.LazyLogging
import config.db.{DBConfig, DBConfigParser}

import scala.util.{Failure, Success, Try}

object AppConfigParser extends LazyLogging {
  def parseConfig(confFile: File): AppConfig = {
    val conf = Try(ConfigFactory.parseFile(confFile))
    conf match {
      case Success(parsedConfig) => new AppConfig {
        override val dbConfig: DBConfig = DBConfigParser.parse(parsedConfig)
      }
      case Failure(exception) =>
        logger.info("Error reading config from file")
        logger.info(exception.getMessage, exception.getCause)
        logger.info("Now using default configuration")
        DefaultConfig
    }
  }

  def parseConfig(path: String): AppConfig = {
    parseConfig(new File(path))
  }


}
