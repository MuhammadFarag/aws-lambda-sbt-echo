package com.mfarag

import java.io.{InputStream, OutputStream}

import play.api.libs.json._

import scala.io.Source

class Main {

  def process(input: InputStream, output: OutputStream): Unit = {
    output.write(LambdaImplementation.process(Source.fromInputStream(input).mkString).getBytes)
  }
}

trait Lambda[A, B] {
  def process(s: String): String = resultToJson(lambda(jsonToRequest(s)))

  implicit val x: Reads[A]
  implicit val y: Writes[B]

  def jsonToRequest(s: String): A = Json.parse(s).as[A]

  def resultToJson(r: B): String = Json.stringify(Json.toJson(r))

  def lambda(r: A): B
}

object LambdaImplementation extends Lambda[Request, Result] {

  implicit val x: Reads[Request] = Json.reads[Request]
  implicit val y: Writes[Result] = Json.writes[Result]

  def lambda(r: Request): Result = Result(r.s.toUpperCase)
}

case class Request(s: String)

case class Result(s: String)