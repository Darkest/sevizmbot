package dao

import dao.Role.Role
import dao.SquerylEntrypointForMyApp._
import org.squeryl.Schema

class User(val id: Long,
           val name: String = "",
           val role: Option[Role] = Some(Role.user)) {
  def this() = this(0, "", Some(Role.user))

}

object User {
  def apply(id: Long, name: String = "", role: Option[Role] = Some(Role.user)): User = new User(id, name, role)
}

object BotSchema extends Schema {
  val users = table[User]

  on(users)(u => declare(
    u.id is indexed("userIdX"),
    u.role is indexed("userRoleX")
  ))

  val roles = table[Role]
}

object Role extends Enumeration {
  type Role = Value
  val admin = Value(1, "admin")
  val user = Value(2, "user")
  val coordinator = Value(3, "coordinator")
}

