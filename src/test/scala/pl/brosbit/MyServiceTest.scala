package com.example

import spray.testkit.ScalatestRouteTest
import spray.http._
import StatusCodes._
import org.scalatest.FlatSpec
import org.scalatest.matchers.ShouldMatchers


class MyServiceTest extends FlatSpec with ScalatestRouteTest with ShouldMatchers with MyService {
  def actorRefFactory = system

  behavior of "MyService"

  it should "leave GET requests to other paths unhandled" in {
    Get("/unknown") ~> myRoute ~> check {
      handled should be(false)
    }
  }

  it should "return a MethodNotAllowed error for PUT requests to the root path" in {
    Put() ~> sealRoute(myRoute) ~> check {
      status should be(MethodNotAllowed)
      entityAs[String] should be("HTTP method not allowed, supported methods: GET")
    }
  }
}
