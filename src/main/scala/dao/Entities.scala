package dao

import slick.jdbc.H2Profile.api._

case class User(id: Long,
                isBot: Boolean,
                firstName: String,
                lastName: Option[String],
                userName: Option[String],
                languageCode: Option[String],
                roleId: Int = 6,
                active: Boolean = true,
                comment: String = "")

class Users(tag: Tag) extends Table[User](tag, "USERS") {

  def id = column[Long]("USER_ID", O.PrimaryKey)

  def isBot = column[Boolean]("IS_BOT")

  def firstName = column[String]("FIRST_NAME")

  def lastName = column[Option[String]]("LAST_NAME")

  def userName = column[Option[String]]("USERNAME")

  def languageCode = column[Option[String]]("LANG_CODE")

  def role_id = column[Int]("ROLE_ID")

  def active = column[Boolean]("ACTIVE")

  def comment = column[String]("COMMENT")

  override def * = (id, isBot, firstName, lastName, userName, languageCode, role_id, active, comment) <> (User.tupled, User.unapply)

  def role = foreignKey("ROLE_FK", role_id, TableQuery[Roles])(_.id)
}

case class Role(id: Option[Int], role: String)

class Roles(tag: Tag) extends Table[Role](tag, "ROLES") {
  def id = column[Int]("ROLE_ID")

  def role = column[String]("ROLE")

  override def * = (id.?, role) <> (Role.tupled, Role.unapply)
}

case class Address(id: Option[Long], city: String, street: String, house: String, block: String = "",
                   building: String = "", region: String = "", doorsCount: Option[Int])

class Addresses(tag: Tag) extends Table[Address](tag, "ADDRESSES") {
  def id = column[Long]("ADDRESS_ID", O.AutoInc, O.Unique)

  def city = column[String]("CITY")

  def street = column[String]("STREET")

  def house = column[String]("HOUSE")

  def block = column[String]("BLOCK")

  def building = column[String]("BUILDING")

  def region = column[String]("REGION")

  def doorsCount = column[Option[Int]]("DOORS_COUNT")

  def pk = primaryKey("ADDRESSES_PK", (city, street, house, block, building))

  override def * = (id.?, city, street, house, block, building, region, doorsCount) <> (Address.tupled, Address.unapply)
}
