package com.example

import akka.actor.Actor
import spray.routing._
import spray.http._
import spray.json._
import DefaultJsonProtocol._
import MediaTypes._
import spray.httpx.SprayJsonSupport

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


case class Language(language: String)
case class Request(url: String)

object MyServiceJsonSupport extends DefaultJsonProtocol with SprayJsonSupport {
   implicit val LanguageFormats = jsonFormat1(Language)
   implicit val RequestFormats = jsonFormat1(Request)
}

import MyServiceJsonSupport._

// this trait defines our service behavior independently from the service actor
trait MyService extends HttpService {

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
          var u = Language("Scala")
          complete {
            u
          }
        }
      }
    }
}
