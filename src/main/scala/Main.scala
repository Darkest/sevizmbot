import com.typesafe.scalalogging.LazyLogging

object Main extends App with LazyLogging {

  logger.info("bot starting")
  SevIzmBot.run()
  logger.info("bot started")
}
