package pl.brosbit

import java.util.Date
import scala.collection.mutable.{HashMap, StringBuilder}
import java.io.{File, BufferedWriter, FileWriter}

//case class HostIpAndDate(ip:String, date:Date)

object HostData {
    var hosts = List[HostInfo]()

    def loadHosts(hostInfo:List[HostInfo]) {
    
    }

    def addHost(host:HostInfo) {
        hosts = host::hosts
    }
    
    def getAllHosts() = hosts
    
    def createFile() {
        val contentStr = new StringBuilder
        this.getAllHosts.map(h => {
            contentStr ++= "\n[%s]\n%s\n".format(h.host, h.ip)
        })
        
        val file = new File("/etc/ansible/hosts")
        val fileWriter = new FileWriter(file)
        fileWriter.write(contentStr.mkString(""))
        fileWriter.flush()
        fileWriter.close()
    }
}
