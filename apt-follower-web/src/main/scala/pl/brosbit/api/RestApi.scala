package pl.brosbit.api

import net.liftweb.http._
import net.liftweb.http.rest._
import pl.brosbit.model._
import net.liftweb.mapper._
import net.liftweb.common._
import pl.brosbit.lib.XmlSendExtractor
import java.util.Date
import scala.xml.Node


object RestApi extends RestHelper {
  
	val separator = "|"
	  
	serve {
	  case "api" :: "packages" :: host :: _ Post _ => 
       RestContinuation.async(
           reply => { 
        	   reply{ 
        		   <root>{getPackages(host)}</root>
        	   }
           })
	}
	serve {
     case "api" :: "orders" :: host :: _ Get _ => 
      RestContinuation.async(
           reply => {
        	   reply{ 
        		   sendPackages(host)
        	   }
           } )   
          
 
  }
	
	private def sendPackages(host:String) = {
		val hostName = host
		Hosts.findAll(By(Hosts.hostName, hostName)) match {
		  case host::list => {
		    <root>
			  <info>{hostName + " found"}</info><upgrade>{host.upgradeRequire.is}</upgrade>
			  <install>{host.installs.is.split(separator).map(pack => {
			    <package>{pack}</package>
			  })}</install>
			  <delete>{host.deletes.is.split(separator).map(pack => {
			    <package>{pack}</package>
			  })}</delete>
			  </root>
		  }
		  case _ => <root><info>{hostName + " not registred! GET"}</info>
		  				<install></install><delete></delete></root>
		}
	}
	  
	  
	private def getPackages(host:String) = {
	  val dataxml = S.param("dataxml").getOrElse("<root></root>")
	  
//	  S.request match {
//	    case Full(req) => print(req.toString + "\n") 
//	    case _ =>
//	  }
	 
	  //print("for host: " + host + "  getPackageData = " + dataxml)
	  val hostName = host
	  val xmlExtractor = new XmlSendExtractor(dataxml)
	  val password = xmlExtractor.passwordString
	  if(hostName  == "") "Error"
	  else {
	    Hosts.findAll(By(Hosts.hostName, hostName)) match {
	      case hostModel::list => {
	    	hostModel.packages(xmlExtractor.packageString).lastUpgrade(xmlExtractor.upgradeTime).
	    		lastConnect(new Date()).save
	        "OK"
	      }
	      case _ => {
	        val hostModel = Hosts.create
	        hostModel.hostName(hostName).packages(xmlExtractor.packageString).lastUpgrade(xmlExtractor.upgradeTime).
	    		lastConnect(new Date()).save
	    	"You are new Host"
	      }
	    }
	    
	  }
	}
	
}