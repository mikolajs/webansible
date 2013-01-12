package pl.brosbit.lib

import scala.xml._
import java.util.Date
import com.sun.xml.internal.txw2.Text

class XmlSendExtractor(dataxml:String) {
	var upgradeTime:Date = null
	var packageString = "Error"
	var passwordString = ""
	readXML
	
	private def readXML {
	   try {
	     val xml = XML.loadString(dataxml)
	    
	     val updateTime = new Date( (xml \ "upgrade").text.toLong)
	     passwordString = (xml \ "password").text
	     val packageListNode = xml \ "packageList"
	     val packageList = (packageListNode \ "package").map(_.text)
	     packageString = packageList.mkString("|")	          
	   }
	   catch {
	     case e => print(e.toString)
	   }
	}
}