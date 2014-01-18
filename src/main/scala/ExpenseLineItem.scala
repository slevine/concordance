import org.joda.time.DateTime

/**
 * Created by IntelliJ IDEA.
 * Author: Steve Levine
 * Date: 1/14/14
 */
case class ExpenseLineItem(id: Int, date: DateTime, category: String, description: String, amount: Double)

case class InvoiceLineItem(invoiceDate: DateTime, paidDate: DateTime, id: Double, client: String, description: String, amount: Double)

object ExpenseLineItem {
  // Need an apply that defaults id to 0, since id is DB generated
  def apply(date: DateTime, cat: String, desc: String, amt: Double): ExpenseLineItem =
    ExpenseLineItem(0, date, cat, desc, amt)
}