package pl.brosbit

import java.util.Date
import akka.actor.Actor
import spray.routing._
import spray.http._
import MediaTypes._

// we don't implement our route structure directly in the service actor because
// we want to be able to test it independently, without having to spin up an actor
class MyServiceActor extends Actor with MyService {

    // the HttpService trait defines only one abstract member, which
    // connects the services environment to the enclosing actor or test
    def actorRefFactory = context

    // this actor only runs our route, but you could add
    // other things here, like request stream processing
    // or timeout handling
    def receive = runRoute(myRoute)
}

// this trait defines our service behavior independently from the service actor
trait MyService extends HttpService {

    val myRoute =
        path("") {
            get {
                respondWithMediaType(`text/html`) {
                    complete {
                        <html>
                            <body>
                                <h1>Witamy w Webapt -  { new Date().toString }</h1>
                            </body>
                        </html>
                    }
                }
            }
        } ~
            path("pings") {
                get {
                    parameters("h") { hostName =>
                        clientIP { ip =>
                            respondWithMediaType(`text/html`) { // XML is marshalled to `text/xml` by default, so we simply override here
                                complete {
                                    saveData(hostName)
                                    <html>
                                        <body>
                                            <h1>PONG { "HostName: " + hostName + " -  IP: " +  ip.toString }</h1>
                                        </body>
                                    </html>
                                }
                            }
                        }
                    } ~
                        get {
                            respondWithMediaType(`text/html`) { // XML is marshalled to `text/xml` by default, so we simply override here
                                complete {
                                    <html>
                                        <body>
                                            <h1>PONG - NO HOSTNAME </h1>
                                        </body>
                                    </html>
                                }
                            }
                        }
                }
            }

    def saveData(host: String) {
        println("---------------- HOSTNAME ===== " + host)
    }
}