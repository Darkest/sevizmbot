import dao.Accessor
import dao.Converters._
import info.mukel.telegrambot4s.api.declarative._
import info.mukel.telegrambot4s.api.{Polling, TelegramBot}
import info.mukel.telegrambot4s.models._

import scala.io.Source

object SevIzmBot extends TelegramBot with Polling with Commands with RegexCommands {
  lazy val token: String = scala.util.Properties
    .envOrNone("BOT_TOKEN")
    .getOrElse(Source.fromFile("bot.token").getLines().mkString)


  def menuFromCommands(cmds: List[Command]) = {
    val lines = scala.collection.mutable.Buffer.empty[List[KeyboardButton]]
    for (i <- 0 to cmds.length / 3) {
      lines += cmds.slice(0 + i * 3, 2 + i * 3).map(x => KeyboardButton(x.commandText))
    }
    Option(Seq(lines))
  }

  /*def adminMenu = {
    val admin
  }*/


  /*Option {
    ReplyKeyboardMarkup(keyboard = Seq(Seq(
      KeyboardButton("Повысить пользователя"),
      KeyboardButton("Понизить пользователя"))),
      resizeKeyboard = Option(true)
    )
  }*/

  when(onCommand("/start"), (_: Message) => true) { implicit msg =>
    val user = msg.from.get
    val replyPhrase =
      Accessor.registerUser(user) match {
        case true => s"Добро пожаловть, ${user.firstName}!"
        case false => s"С возвращениемь, ${user.firstName}!"
      }
    reply(replyPhrase /*, replyMarkup = adminMenu*/)
  }

  when(onMessage, (_: Message) => true) { implicit msg =>
    reply("123")

  }

  when(onMessage, (_: Message) => true) { implicit msg =>
    reply("321")
  }
}
