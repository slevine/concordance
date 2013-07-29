import scala.annotation.tailrec
import scala.io.Source

/**
 * Created by IntelliJ IDEA.
 * Author: Steve Levine
 * Date: 7/19/13
 */
object Concordance extends App {

  val file = if (args.length == 1) args(0) else "doc.txt"

  val isWhiteSpace = (s: String) ⇒ s.startsWith(" ") || s == ""

  def filterPunctuation(s: String): String = {

    @tailrec
    def innerFilter(s: String, p: List[Char]): String =
      if (p.isEmpty) s
      else if (s.endsWith(p.head.toString)) innerFilter(s.takeWhile(_ != p.head), p.tail)
      else if (s.startsWith(p.head.toString)) innerFilter(s.substring(1, s.length), p.tail)
      else innerFilter(s, p.tail)

    innerFilter(s, List('!', '.', ',', ';', ')', '(', ':'))
  }

  val lines = (Source fromFile file getLines () map (l ⇒ l.split(" "))).toList

  val wordCounts = lines.flatten.filterNot(isWhiteSpace).map(filterPunctuation).groupBy(x ⇒ x.toLowerCase)

  for (key <- wordCounts.keys.toList.sortWith(_ < _)) {
    val pad = " " * (20 - key.length)
    println(s"$key $pad {${wordCounts(key).size}}")
  }
}