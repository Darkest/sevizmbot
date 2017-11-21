package dao

import org.squeryl.dsl.CompositeKey4
import org.squeryl.{KeyedEntityDef, PrimitiveTypeMode}

object SquerylEntrypointForMyApp extends PrimitiveTypeMode {

  implicit object UserKED extends KeyedEntityDef[User, Long] {
    override def getId(a: User): Long = a.id

    override def isPersisted(a: User): Boolean = a.id > 0

    override def idPropertyName: String = "id"
  }

  implicit object AddressKED extends KeyedEntityDef[Address, CompositeKey4[String, String, String, String]] {
    override def getId(a: Address): CompositeKey4[String, String, String, String] =
      compositeKey(a.city, a.street, a.house, a.building)

    override def isPersisted(a: Address): Boolean = a.addressId > 0

    override def idPropertyName: String = "id"
  }



}