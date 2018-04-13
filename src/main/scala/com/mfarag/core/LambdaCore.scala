package com.mfarag.core

import play.api.libs.json.{Json, Reads, Writes}

trait LambdaCore[A, B] {
  def process(s: String): String = resultToJson(lambda(jsonToRequest(s)))

  implicit protected val reads: Reads[A]
  implicit protected val writes: Writes[B]

  def jsonToRequest(s: String): A = Json.parse(s).as[A]

  def resultToJson(r: B): String = Json.stringify(Json.toJson(r))

  def lambda(r: A): B
}
