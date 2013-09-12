package pl.brosbit.model

import _root_.net.liftweb.mapper._
import _root_.net.liftweb.util._
import _root_.net.liftweb.common._
import _root_.net.liftweb.sitemap._
import _root_.net.liftweb.sitemap.Loc._
import Helpers._
import net.liftweb.http.S
import scala.xml.Elem

/**
 * The singleton that has methods for accessing the database
 */
object User extends User with MetaMegaProtoUser[User] {
  override def dbTableName = "users" // define the DB table name
  override def screenWrap = Full(<lift:surround with="default" at="content">
			       <lift:bind /></lift:surround>)
  // define the order fields will appear in forms and output
  override def fieldOrder = List(id, firstName, lastName, email,
  locale, timezone, password)
  // disables automatic sign up form
  override def createUserMenuLoc = Empty
  // comment this line out to require email validations
  override def skipEmailValidation = true
		  	
  override def loginMenuLocParams = Hidden::super.loginMenuLocParams
  
  override def lostPasswordMenuLocParams =Hidden::super.lostPasswordMenuLocParams
  
  override def signupXhtml(user:User)={super.signupXhtml(user)}
	
}

class User extends MegaProtoUser[User] {
  def getSingleton = User // what's the "meta" server 
  }
  
  

