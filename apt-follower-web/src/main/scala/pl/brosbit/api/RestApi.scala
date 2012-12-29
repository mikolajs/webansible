package pl.brosbit.api

import net.liftweb.http._
import net.liftweb.http.rest._

object RestApi extends RestHelper {
  
	 
	serve {
	  case "api" :: "packages" :: _ Post _ => 
      RestContinuation.async(
           reply => {
   
        	   reply{ 
        		   getPackages
        	   }
           })
     case "api" :: "orders" :: _ Get _ => 
      RestContinuation.async(
           reply => {
   
        	   reply{ 
        		   sendPackages
        	   }
           } )   
          
 
  }
	
	def sendPackages = <root></root>
	  
	  
	def getPackages = {
	  val data = S.param("data").getOrElse("<root></root>")
	  
	  <i></i>
	}
}