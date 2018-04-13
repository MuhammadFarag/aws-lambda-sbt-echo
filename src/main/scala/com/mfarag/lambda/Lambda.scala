package com.mfarag.lambda

import java.io.{InputStream, OutputStream}

import play.api.libs.json.{Json, Reads, Writes}

import scala.io.Source

trait Lambda[A, B] {

  implicit protected val reads: Reads[A]
  implicit protected val writes: Writes[B]

  def process(input: InputStream, output: OutputStream): Unit =
    output.write(process(Source.fromInputStream(input).mkString).getBytes)

  private def process(s: String): String = resultToJson(lambda(jsonToRequest(s)))

  def jsonToRequest(s: String): A = Json.parse(s).as[A]

  def resultToJson(r: B): String = Json.stringify(Json.toJson(r))

  def lambda(r: A): B
}
