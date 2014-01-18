import io.Source
import org.joda.time.format._
import scala.language.implicitConversions


trait CSVTools {
  def csvFile: String

  def rows = (Source.fromFile(csvFile).getLines() map (_.split(","))).toList

  implicit def stringToDateTime(s: String) = if (!s.equals("")) DateTimeFormat.forPattern("yyyy-MM-dd").parseDateTime(s) else null

  implicit def stringToDouble(s: String) = if (!s.equals("")) s.toDouble else 0.0

  def removeComma(s: String) = if (s.contains(",")) s.split(",").mkString else s

}

object ExpenseCSVReader {
  def apply(csvFile: String) = new ExpenseCSVReader(csvFile)
}

class ExpenseCSVReader(val csvFile: String) extends CSVTools {

  lazy val items = rows map (li ⇒ ExpenseLineItem(li(0), li(1), li(2), li(3)))

  def totalsByCategory = items.groupBy(_.category).mapValues(_.map(_.amount).sum)

}

class InvoiceCSVReader(val csvFile: String) extends CSVTools {

  lazy val items = rows map (li ⇒ InvoiceLineItem(li(0), li(1), li(2), li(3), li(4), li(5)))

}

object InvoiceCSVReader {
  def apply(csvFile: String) = new InvoiceCSVReader(csvFile)
}


