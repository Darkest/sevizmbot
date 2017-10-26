import info.mukel.telegrambot4s.api.declarative._
import info.mukel.telegrambot4s.api.{Polling, TelegramBot}
import info.mukel.telegrambot4s.models._

import scala.io.Source

object SevIzmBot extends TelegramBot with Polling with Commands{
  lazy val token: String = scala.util.Properties
    .envOrNone("BOT_TOKEN")
    .getOrElse(Source.fromFile("bot.token").getLines().mkString)

  def menu = Option{ReplyKeyboardMarkup(keyboard = Seq(Seq(
      KeyboardButton("Hi") ,
      KeyboardButton("Bye"))),
    resizeKeyboard = Option(true)
  )
  }

  onCommand("/start") { implicit msg =>
    reply("Hi!", replyMarkup = menu)
    menu
  }
}
