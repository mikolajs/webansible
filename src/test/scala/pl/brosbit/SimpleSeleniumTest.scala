package pl.brosbit

import org.scalatest.{BeforeAndAfterAll, FlatSpec}
import org.scalatest.matchers.ShouldMatchers
import org.scalatest.selenium.{HtmlUnit, Chrome}

class SimpleSeleniumTest extends FlatSpec with WebApp with ShouldMatchers with BeforeAndAfterAll with HtmlUnit {

  behavior of "Spray Web Application"

  it should "display a home page containing 3 links" in {
    go to ("http://localhost:8989")
    val h1: Option[SimpleSeleniumTest.this.type#Element] = find(xpath("//h1"))

    h1 match {
      case Some(elem) => elem.text should be("Witaj Webansible")
      case None => fail("h1 not found")
    }
  }

  override protected def afterAll() {
    quit
    system.shutdown
  }
}
