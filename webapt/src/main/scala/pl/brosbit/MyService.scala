package pl.brosbit

import java.util.Date
import akka.actor.Actor
import akka.actor.Props
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
  val dbActor = context.actorOf(props = Props[DBInsertActor], name = "dbActor")

  def receive = runRoute(myRoute)

  override def saveData() = dbActor ! HostData.getAllHosts.toList 
  override def createFile() {dbActor ! "save"}
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
                <div>
                  <a href="/createfile">Zapisz plik /etc/ansible/host</a>
                </div>
                <div>
                  <a href="/savedata"> Zapisz dane do bazy</a>
                </div>
                <div>
                <h2>Lista wykrytych hostów</h2>
                    <ul>
                    {HostData.getAllHosts.sortWith((hi1, hi2) => hi1.host > hi2.host).
                            map(h => <li>{h.host} | {h.ip} | {h.date.toString}</li>)}
                    </ul>
                </div>
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
              
              respondWithMediaType(`text/html`) {
                complete {
                  HostData.addHost(HostInfo(hostName, ip.toString, new Date))
                  <html>
                    <body>
                      <h1>PONG { "HostName: " + hostName + " -  IP: " + ip.toString }</h1>
                    </body>
                  </html>
                }
              }
            }
          } ~
            get {
              respondWithMediaType(`text/html`) {
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
      } ~
      path("createfile") {
        get {
          respondWithMediaType(`text/html`) {
            complete {
              createFile
              <html><body><h1>Plik gotowy</h1></body></html>
            }
          }
        }
      } ~
      path("savedata") {
        get {
          respondWithMediaType(`text/html`) {
            complete {
              saveData
              <html><body><h1>Plik gotowy</h1></body></html>
            }
          }
        }
      }

  def saveData() {
    println("---------------- HOSTNAME ===== ")
  }
  
  def createFile() {}
}
