/*
 * Copyright (C) 2013   Mikołaj Sochacki, mikolajsochacki  gmail.com
 *   This file is part of Apt-fallower
 *   LICENCE: Apache 2.0
 */

package bootstrap.liftweb

import _root_.net.liftweb.util._
import _root_.net.liftweb.common._
import _root_.net.liftweb.http._
import _root_.net.liftweb.http.provider._
import _root_.net.liftweb.sitemap._
import _root_.net.liftweb.sitemap.Loc._
import Helpers._
import _root_.net.liftweb.mapper.{DB, ConnectionManager, ConnectionIdentifier, Schemifier, DefaultConnectionIdentifier, StandardDBVendor}
import _root_.java.sql.{Connection, DriverManager}
import pl.brosbit.model._
import pl.brosbit.api._

object DBVendor extends ConnectionManager {
  def newConnection(name: ConnectionIdentifier): Box[Connection] = {
    try {
       Class.forName("org.h2.Driver")
      val dm = DriverManager.getConnection("jdbc:h2:apt-fallower")
      Full(dm)
    } catch {
      case e: Exception => e.printStackTrace; Empty
    }
  }
  def releaseConnection(conn: Connection) { conn.close }
}

class Boot {
  def boot {

    DB.defineConnectionManager(DefaultConnectionIdentifier, DBVendor)
  
    LiftRules.addToPackages("pl.brosbit")

    Schemifier.schemify(true, Schemifier.infoF _, User, Groups, Hosts)
       


    if (DB.runQuery("select * from users where lastname = 'Administrator'")._2.isEmpty) {
      val u = User.create
      u.lastName("Administrator").password("123qwerty").email("mail@mail.org").validated(true).save
    }

    val loggedIn = If(() => User.loggedIn_? , () => RedirectResponse("/user_mgt/login"))
      

    def sitemap() = SiteMap(
      List(
        Menu("Strona główna") / "index" >> LocGroup("public") >> loggedIn) :::
        User.sitemap: _*)

    LiftRules.setSiteMapFunc(sitemap)
    

    LiftRules.htmlProperties.default.set((r: Req) =>
      new Html5Properties(r.userAgent))

    LiftRules.early.append(makeUtf8)

    LiftRules.loggedInTest = Full(() => User.loggedIn_?)

    LiftRules.passNotFoundToChain = true
    LiftRules.maxMimeSize = 16 * 1024 * 1024
    LiftRules.maxMimeFileSize = 16 * 1024 * 1024
    

    LiftRules.dispatch.append(RestApi) 
    
    LiftRules.liftRequest.append {
      case Req("extra" :: _, _, _) => false
    }

    S.addAround(DB.buildLoanWrapper)
  }

  /**
   * Force the request to be UTF-8
   */
  private def makeUtf8(req: HTTPRequest) {
    req.setCharacterEncoding("UTF-8")
  }
}
