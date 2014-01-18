/**
 * Created by IntelliJ IDEA.
 * Author: Steve Levine
 * Date: 1/18/14
 */

trait JDBCDataSource extends BaseDBWriter {

  import scala.slick.jdbc.JdbcBackend.Database

  def dataSource = Database.forURL("jdbc:someDatabase://someDatabaseUrl", user = "user", password = "pass", driver = "scala.slick.driver.SomeDriver")
}

object SomeDbExpenseDBWriter extends ExpenseDBWriter with JDBCDataSource

object SomeDbInvoiceDBWriter extends InvoiceDBWriter with JDBCDataSource

class Usage extends App {

  val expenseFiles = List("expense1.csv", "expense2.csv")

  val invoiceFile = "invoices.csv"

  // Display Totals by Category
  ExpenseCSVReader(expenseFiles(0)).totalsByCategory foreach {case (c, v) ⇒ println(f"$c $$$v%2.2f")}

  // Store Expenses in DB
  expenseFiles foreach {file ⇒ SomeDbExpenseDBWriter.insertItems(ExpenseCSVReader(file).items, refresh = true)}

  // Store Invoices in DB
  SomeDbInvoiceDBWriter.insertItems(InvoiceCSVReader(invoiceFile).items, refresh = true)

}