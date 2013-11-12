package pl.brosbit

import java.util.Date
import scala.collection.mutable.{HashMap, StringBuilder}
import java.io.{File, BufferedWriter, FileWriter}

case class HostIpAndDate(ip:String, date:Date)

object HostData {
    val hosts = HashMap[String, HostIpAndDate]()

    def loadHosts(hostInfo:List[HostInfo]) {
    
    }

    def addHost(host:HostInfo) {
        hosts += ((host.host ,HostIpAndDate(host.ip , host.date))) 
    }
    
    def getAllHosts() = {
        hosts.map(h => HostInfo(h._1, h._2.ip, h._2.date)).toList
    }
    
    def createFile() {
        val contentStr = new StringBuilder
        this.getAllHosts.map(hi => {
            contentStr ++= "\n[%s]\n%s\n".format(hi.host, hi.ip)
        })
        
        val file = new File("/etc/ansible/hosts")
        val fileWriter = new FileWriter(file)
        fileWriter.write(contentStr.mkString(""))
        fileWriter.flush()
        fileWriter.close()
    }
}
