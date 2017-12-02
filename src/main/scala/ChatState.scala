import info.mukel.telegrambot4s.models.{Message, ReplyKeyboardMarkup}

trait ChatState {
  val stateAction: (Message => Unit)
  val result: Message
  val menuMarkup: Option[ReplyKeyboardMarkup]
}

case class ChoosingStreet(result: Message) extends ChatState {
  override val stateAction: Message => Unit = _
  override val menuMarkup: Option[ReplyKeyboardMarkup] = _
}

abstract class ChatsState {
  protected val states: scala.collection.concurrent.Map[Long, List[ChatState]]

  val stateAction: (Message => Unit)
}
