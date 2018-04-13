package com.mfarag.lambda

import java.io.{InputStream, OutputStream}

import play.api.libs.json.{Json, Reads, Writes}

import scala.io.Source

trait Lambda[A, B] {

  implicit protected val reads: Reads[A]

  implicit protected val writes: Writes[B]

  def lambda(r: A): B

  def process(input: InputStream, output: OutputStream): Unit =
    output.write(process(Source.fromInputStream(input).mkString).getBytes)

  private def process(request: String): String = toJson(lambda(fromJson(request)))

  private def fromJson(request: String): A = Json.parse(request).as[A]

  private def toJson(result: B): String = Json.stringify(Json.toJson(result))

}
