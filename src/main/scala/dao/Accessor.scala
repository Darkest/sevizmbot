package dao

import com.typesafe.scalalogging.LazyLogging
import slick.jdbc.H2Profile.api._

import scala.concurrent.Await
import scala.concurrent.duration._

object Accessor extends LazyLogging {
  import config.BotConfig.config.dbConfig._

  Class.forName("org.h2.Driver")
  val db = Database.forURL(url = dbConnString, user = "sa", password = "")

  if (dbWebServer.toLowerCase == "true") {
    import org.h2.tools.Server
    logger.info("Starting h2 web server")
    val webServer: Server = Server.createWebServer("-web", "-webPort", "8082").start
  }

  val users = TableQuery[Users]
  val roles = TableQuery[Roles]
  val addresses = TableQuery[Addresses]

  def init() = {
    logger.info("Starting DB Accessor")
    val ddl = roles.schema ++ users.schema ++ addresses.schema
    logger.debug(s"DDL for DB:\n${ddl.createStatements.mkString(";\n")}")
  }

  def getUserById(id: Long) = {
    val user = Await.result(db.run(users.filter {
      _.id === id
    }.result.headOption), Duration.Inf)
    user
  }

  def registerUser(user: User): Boolean = { //returns true if user is new
    logger.info(s"Registering new user ${user.firstName} with id=${user.id}")

    getUserById(user.id) match {
      case Some(u) =>
        logger.info(s"User with id=${user.id} was already registered by the name ${u.firstName}, updating user info...")
        val update = users.update(u)
        Await.result(db.run(update), Duration.Inf)
        false
      case None =>
        val insert = users += user
        Await.result(db.run(insert), Duration.Inf)
        logger.info(s"Successfully Registered new user ${user.firstName} with id=${user.id}")
        true
    }
  }


  def getUserRoleById(id: Long): Role = {
    def getUserRoleByIdQuery =
      for {
        (u, r) <- users.filter(_.id === id) join roles
      } yield r

    logger.debug(s"Getting role for user with id $id")
    val role = Await.result(db.run(getUserRoleByIdQuery.result.headOption), 5 second).get
    logger.debug(s"User with id=$id has the role of ${role.role}")
    role
  }
}
