package config

import com.typesafe.config.Config
import com.typesafe.scalalogging.LazyLogging

import scala.util.{Failure, Success, Try}

object Utils extends LazyLogging {

  implicit class UberConfig(c: Config) {
    def getStringOption(path: String) = {
      Try(c.getString(path)) match {
        case Success(value) => Some(value)
        case Failure(_) =>
          logger.debug(s"Could not get value by path $path, returning None")
          None
      }
    }
  }

}
