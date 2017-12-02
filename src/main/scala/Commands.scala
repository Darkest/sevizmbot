object Commands {

  lazy val changeRoleCommand = Command("Изменить права", List("admin"))
  lazy val getReportCommand = Command("Получить отчет", List("coordinator"))
  lazy val getMagazineCommand = Command("Газета совета", List("user"))
}

trait CommandTrait {
  val commandText: String
  val commandRights: List[String]
}

case class Command(commandText: String, commandRights: List[String]) extends CommandTrait