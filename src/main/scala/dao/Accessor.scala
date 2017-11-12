package dao

import com.typesafe.scalalogging.LazyLogging
import config.BotConfig
import dao.SquerylEntrypointForMyApp._
import org.squeryl.adapters.H2Adapter
import org.squeryl.{Session, SessionFactory}


object Accessor extends LazyLogging {

  import config.BotConfig.config.dbConfig._

  Class.forName("org.h2.Driver")
  SessionFactory.concreteFactory = Some(() =>
    Session.create(
      java.sql.DriverManager.getConnection(dbConnString, "sa", ""),
      new H2Adapter)
  )

  if (BotConfig.config.dbConfig.dbWebServer.toLowerCase == "true") {
    import org.h2.tools.Server
    val webServer: Server = Server.createWebServer("-web", "-webPort", "8082").start
    inTransaction {
      //BotSchema.create
    }
  }

  def registerUser(user: User) = {
    logger.info(s"Registering new user ${user.name} with id=${user.id}")

    def existingUser = from(BotSchema.users)(u => where(u.id === user.id) select u)

    inTransaction(
      existingUser.headOption match {
        case Some(u) =>
          logger.info(s"User with id=${user.id} was already registered by the name ${u.name}, updating user info...")
          BotSchema.users.update(u)
        case None =>
          BotSchema.users.insert(user)
          logger.info(s"Successfully Registered new user ${user.name} with id=${user.id}")
      }
    )
  }
}
