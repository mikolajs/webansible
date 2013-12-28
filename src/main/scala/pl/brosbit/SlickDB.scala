package pl.brosbit


import scala.slick.driver.JdbcProfile
import scala.slick.driver.H2Driver
import scala.slick.driver.SQLiteDriver
import java.sql.Date

trait Profile {
  val profile: JdbcProfile
}

case class Group(name:String, id: Option[Int] = None)

trait GroupComponent { this: Profile => //requires a Profile to be mixed in...
  import profile.simple._ //...to be able import profile.simple._

  class  GroupDB(tag:Tag) extends Table[Group] (tag, "GROUPS_T"){
    def id = column[Option[Int]]("GR_ID", O.PrimaryKey, O.AutoInc) // This is the primary key column
    def name = column[String]("NAME", O.NotNull)
    // Every table needs a * projection with the same type as the table's type parameter
    def * = (name, id) <> (Group.tupled, Group.unapply )
}

  val groups = TableQuery[GroupDB]

  private val groupAutoInc = groups returning groups.map(_.id) into { case (p, id) => p.copy(id = id) }
  def insert(group: Group)(implicit session: Session): Group = groupAutoInc.insert(group)
}

case class Host(host:String, ip:String, firm:Boolean,  lastping:Date,  group:Int, id: Option[Int] = None)


trait HostComponent { this: Profile =>
  import profile.simple._

  class HostDB(tag:Tag) extends Table[Host](tag, "HOST_T") {
    def id = column[Option[Int]]("HOST_ID", O.AutoInc, O.PrimaryKey)
    def host = column[String]("NAME")
    def ip = column[String]("ip")
    def firm = column[Boolean]("firm")
    def lastping = column[Date]("lastping")
    def group = column[Int]("group_id")
    def * = (host, ip, firm, lastping,  group, id) <> (Host.tupled, Host.unapply)
}
   val hosts = TableQuery[HostDB]
  private val hostAutoInc = hosts returning hosts.map(_.id) into { case (p, id) => p.copy(id = id) }
  def insert(host: Host)(implicit session: Session): Host = hostAutoInc.insert(host)
}

case class Command(name:String, command:String, descript:String, id:Option[Int] = None)

trait CommandComponent { this: Profile =>
  import profile.simple._

class CommandDB(tag:Tag) extends Table[Command](tag, "COMMAND_T") {
    def id = column[Option[Int]]("COMM_ID", O.AutoInc, O.PrimaryKey)
    def name = column[String]("NAME")
    def command = column[String]("COMMAND")
    def descript = column[String]("DESCRIPT")
    def * =( name, command, descript, id) <> (Command.tupled, Command.unapply)
}
  val comm = TableQuery[CommandDB]
   private val commAutoInc =comm returning comm.map(_.id) into { case (p, id) => p.copy(id = id) }
  def insert(c: Command)(implicit session: Session): Command = commAutoInc.insert(c)
}

case class CommHist(commandID:Int, name:String, success:Int, id:Option[Int] = None )

trait CommHistComponent { this: Profile =>
  import profile.simple._
  
class  CommandHistDB(tag:Tag) extends Table[CommHist](tag, "COMMAND_HIST") {
    def id = column[Option[Int]]("HIST_ID", O.AutoInc, O.PrimaryKey)
    def commandID = column[Int]("COMMAND_ID")
    def name = column[String]("INFO")
    def success = column[Int]("SUCCESS")
    def * = (commandID, name, success, id) <> (CommHist.tupled, CommHist.unapply)
}
  val commHist = TableQuery[CommandHistDB]
   private val commAutoInc =commHist returning commHist.map(_.id) into { case (p, id) => p.copy(id = id) }
  def insert(c: CommHist)(implicit session: Session): CommHist = commAutoInc.insert(c)
}
/**
* The Data Access Layer contains all components and a profile
*/
class DAL(override val profile: JdbcProfile) extends GroupComponent with HostComponent 
with CommandComponent with CommHistComponent with Profile {
  import profile.simple._
  def create(implicit session: Session): Unit = {
    (groups.ddl ++ hosts.ddl ++ comm.ddl ++ commHist.ddl).create 
  }
}


object SlickDB {
    
    import scala.slick.jdbc.JdbcBackend.Database
   val db =  Database.forURL("jdbc:h2:webansible", driver = "org.h2.Driver")
   val dal = new DAL(H2Driver)
    import dal._
        import dal.profile.simple._
    def create = {
        db.withSession { implicit session: Session => dal.create }
        }

    def setGroup(gr: Group):Group = {
        db withSession {
            implicit session => {
                if (gr.id.isEmpty) {
                val g = dal.insert(gr)
                g
            } else {
                val q = for { g <- groups if g.id === gr.id } yield g.name
                q.update(gr.name)
                val statement = q.updateStatement
                val invoker = q.updateInvoker
                gr
            }
            }  
        }
    }

    def delGroup(id: Int): Boolean = {
        db withSession {
            implicit session => 
            val q = for { g <- groups if g.id === id } yield g
            val affectedRowsCount = q.delete
            val invoker = q.deleteInvoker
            val statement = q.deleteStatement
            affectedRowsCount > 0
        }
    }
    
    def getGroups() = db withSession {
        implicit session =>
       var l:List[Group] = Nil
        groups foreach{case (g:Group ) => 
          l = g::l
          }
       l
    }
   

} 
    
	
