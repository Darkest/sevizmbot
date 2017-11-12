package dao

import org.squeryl.{KeyedEntityDef, PrimitiveTypeMode}

object SquerylEntrypointForMyApp extends PrimitiveTypeMode {

  implicit object UserKED extends KeyedEntityDef[User, Long] {
    override def getId(a: User): Long = a.id

    override def isPersisted(a: User): Boolean = a.id > 0

    override def idPropertyName: String = "id"
  }

}