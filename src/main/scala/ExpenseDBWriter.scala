import org.joda.time.DateTime
import scala.slick.jdbc.{StaticQuery => Q, JdbcBackend, GetResult}

trait BaseDBWriter {

  import scala.language.implicitConversions

  def dataSource: JdbcBackend.DatabaseDef

  // How can I get Slick to use this conversion?
  implicit def jodaDateTimeToSqlDate(date: DateTime) = if (date != null) new java.sql.Date(date.toDate.getTime) else null
}

trait ExpenseDBWriter extends BaseDBWriter {

  implicit val getExpenseResult = GetResult(r ⇒ ExpenseLineItem(r.nextInt(), new DateTime(r.nextDate().getTime), r.nextString(), r.nextString(), r.nextDouble()))

  def insertItems(items: List[ExpenseLineItem], refresh: Boolean = false) {
    dataSource withSession {
      implicit session =>

        def insert(li: ExpenseLineItem) = (Q.u + "insert into expenses(exp_date, exp_category, exp_description, exp_amount) " +
          "values (" +? jodaDateTimeToSqlDate(li.date) + "," +? li.category + "," +? li.description + "," +? li.amount + ")").execute

        if (refresh) {
          Q.updateNA("drop table expenses").execute
          Q.updateNA( """CREATE TABLE expenses(
                             exp_id INTEGER NOT NULL AUTO_INCREMENT,
                             exp_date DATE,
                             exp_category VARCHAR(100),
                             exp_description VARCHAR(100),
                             exp_amount DECIMAL(13,2),
                             primary key (exp_id)
                     )""").execute
        }

        items foreach insert
    }
  }
}


trait InvoiceDBWriter extends BaseDBWriter {

  def insertItems(items: List[InvoiceLineItem], refresh: Boolean = false) {
    dataSource withSession {
      implicit session =>

        def insert(li: InvoiceLineItem) = (Q.u + "insert into invoices(inv_date, inv_paid_date, inv_id, inv_client, inv_description, inv_amount) " +
          "values (" +? jodaDateTimeToSqlDate(li.invoiceDate) + "," +? jodaDateTimeToSqlDate(li.paidDate) +
          "," +? li.id + "," +? li.client + "," +? li.description + "," +? li.amount + ")").execute

        if (refresh) {
          Q.updateNA("drop table invoices").execute
          Q.updateNA( """CREATE TABLE invoices(
                             inv_date DATE,
                             inv_paid_date DATE,
                             inv_id INTEGER,
                             inv_client VARCHAR(100),
                             inv_description VARCHAR(100),
                             inv_amount DECIMAL(13,2),
                             primary key (inv_id)
                     )""").execute
        }

        items foreach insert
    }
  }
}