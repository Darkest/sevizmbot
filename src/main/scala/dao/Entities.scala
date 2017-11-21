package dao

import dao.Role.Role
import dao.SquerylEntrypointForMyApp._
import org.squeryl.dsl.CompositeKey4
import org.squeryl.{KeyedEntity, Schema}

class User(val id: Long,
           val name: String = "",
           val role: Option[Role] = Some(Role.user)) {

}

object User {
  def apply(id: Long, name: String = "", role: Option[Role] = Some(Role.user)): User = new User(id, name, role)
}

object BotSchema extends Schema {
  val users = table[User]
  val addresses = table[Address]

  on(users)(u => declare(
    u.role is indexed("userRoleX")
  ))

  on(addresses)(adr => declare(
    columns(adr.building, adr.house, adr.region, adr.street) are indexed,
  ))

}

object Role extends Enumeration {
  type Role = Value
  val admin = Value(1, "admin")
  val user = Value(2, "user")
  val coordinator = Value(3, "coordinator")
}

class Address(val addressId: Long,
              val city: String = "Москва",
              val street: String,
              val house: String,
              val building: String = "",
              val region: Option[String] = Some(""),
              val doorsCount: Option[Int] = Some(0)) extends KeyedEntity[CompositeKey4[String, String, String, String]] {
  def id = compositeKey(city, street, house, building)
}
