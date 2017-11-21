import com.typesafe.scalalogging.LazyLogging
import dao.Accessor

object Main extends App with LazyLogging {

    Accessor.init()

    logger.info("bot starting")
    SevIzmBot.run()
    logger.info("bot started")

}
