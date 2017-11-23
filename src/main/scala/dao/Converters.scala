package dao

object Converters {
  implicit def toDaoUser(tuser: info.mukel.telegrambot4s.models.User): dao.User = {
    import tuser._
    User(id, isBot, firstName, lastName, username, languageCode)
  }
}
