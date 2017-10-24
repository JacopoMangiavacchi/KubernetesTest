package scalatest

import akka.actor.{ActorSystem, Props}
import akka.io.IO
import spray.can.Http
import akka.pattern.ask
import akka.util.Timeout
import scala.concurrent.duration._
import akka.actor.Actor
import spray.routing._
import spray.http._
import spray.json._
import DefaultJsonProtocol._
import MediaTypes._
import spray.httpx.SprayJsonSupport
import spray.client.pipelining._
import scala.concurrent.Future
import spray.httpx.encoding.{Deflate}
import scala.util.{Failure, Success}


object Boot extends App {
  implicit val system = ActorSystem("on-spray-can")

  val service = system.actorOf(Props[MyServiceActor], "demo-service")

  implicit val timeout = Timeout(5.seconds)
  IO(Http) ? Http.Bind(service, interface = "localhost", port = 8050)
}

class MyServiceActor extends Actor with MyService {
  def actorRefFactory = context
  def receive = runRoute(myRoute)
}

case class Language(language: String)
case class Request(url: String)

object MyServiceJsonSupport extends DefaultJsonProtocol with SprayJsonSupport {
   implicit val languageFormat = jsonFormat1(Language)
   implicit val requestFormat = jsonFormat1(Request)
}

import MyServiceJsonSupport._

trait MyService extends HttpService {

  val system = akka.actor.ActorSystem()
  import system.dispatcher

  val myRoute =
    path("language") {
      get {
        var u = Language("Scala")
        complete {
          u
        }
      }
    }  ~ 
    path("request") {
      post {
        entity(as[Request]) { request =>
          val pipeline: HttpRequest => Future[Language] = (
            sendReceive
            ~> decode(Deflate)
            ~> unmarshal[Language]
          )

          val responseFuture: Future[Language] = pipeline(Get(request.url))

          onComplete(responseFuture) { responseLanguage =>
            complete {
              responseLanguage
            }
          }
        }
      }
    }
}