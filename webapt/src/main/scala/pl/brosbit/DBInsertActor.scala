package pl.brosbit

import akka.actor.{Actor, ActorLogging}
import java.io.{File, BufferedWriter, FileWriter}
import java.sql._
import java.util.Date
//import org.specs2.io.FileWriter

case class HostInfo(host:String, ip:String)


class DBInsertActor extends Actor with ActorLogging {
	Class.forName("org.h2.Driver") 
  val usr = "webansible"
  val pwd = "qwerty"
  val url = "jdbc:h2:webansible"
  var conn:Connection = _
  initDB
  createTables
  
  def receive = {
    case HostInfo(host, ip) => {
      println("recive connection form " + host)
      renewHostInDB(host, ip)
    }
    case i:String => {
      println("Create file order")
      createConfigFile();
    }
       
  }
 
	private def initDB() {
    try {
      println("Init connection ===================== !!!")
      conn =  DriverManager.getConnection(url,usr,pwd)
    }
    catch {
      case e:Exception => println("Error: " + e.toString)
        case _:Throwable => println("Nieznany błąd połączenia z bazą")
    }
    
    

  }
	
	def renewHostInDB(hostName:String, ip:String) = {
	  val metaData = conn.getMetaData
      val stat = conn.createStatement
      var result = stat.executeQuery("SELECT hostname, ip, lastping FROM pinger_host WHERE hostname = '%s'".format(hostName))
      val rMetaD = result.getMetaData
      val numberOfColumn = rMetaD.getColumnCount
      val timeStamp = (new Timestamp((new Date).getTime)).toLocaleString()
      if(result.first()) {
       val statUpdate = stat.executeUpdate("UPDATE pinger_host set ip = '%s', lastping = '%s' WHERE hostname = '%s'"
    	      .format(ip, timeStamp, hostName))
       println("UPDATE status  " + statUpdate)
      }   	  
      else {
        val insertSuccess = stat.executeUpdate("INSERT INTO pinger_host (hostname, ip, lastping) VALUES( '%s', '%s', '%s' )"
            .format(hostName, ip, timeStamp))
        println("INSERT success " +insertSuccess)
      }
	}
	
	def createTables() {
	  try {
      val stat = conn.createStatement
	  stat.execute("""Create table PINGER_HOST (
	      hostname varchar(60) primary key,
	      ip varchar(15),
	      lastping TIMESTAMP
	      )
	      """)
    } catch {
      case _  => println("Create table fail") 
    }
	  
	}
	
	def createConfigFile() {
	  val stat = conn.createStatement()
	  val result = stat.executeQuery("Select hostname, ip from pinger_host")
	  var contentStr = ""
	  while(result.next()){
	    contentStr += "\n[%s]\n%s\n".format(result.getString(1), result.getString(2))
	  }
	  val file = new File("/home/ms/Programy/scala/webansible/hosts")
	  val fileWriter = new FileWriter(file)
	  fileWriter.write(contentStr)
	  fileWriter.flush()
	  fileWriter.close()
	}
	
	def dispose() { dbClose }
	
	private def dbClose() {
    try {
      conn.close
    }
    catch {
      case e:Exception => println("Error: " + e.toString)
        case _:Throwable => println("Nieznany błąd zamykania bazy")
    }
  }
 }
