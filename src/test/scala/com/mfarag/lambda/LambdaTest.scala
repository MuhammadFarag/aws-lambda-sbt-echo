package com.mfarag.lambda

import java.io.{ByteArrayInputStream, ByteArrayOutputStream, InputStream}

import org.scalatest.{FunSuite, Matchers}
import play.api.libs.json.{Json, Reads, Writes}

class LambdaTest extends FunSuite with Lambda[Request, Result] with Matchers {

  override implicit protected val reads: Reads[Request] = Json.reads[Request]
  override implicit protected val writes: Writes[Result] = Json.writes[Result]

  def lambda(r: Request): Result = Result(r.s.toUpperCase)

  test("Process returns the upper case of the message it receives") {
    val input: InputStream = new ByteArrayInputStream("""{"s":"hello world"}""".getBytes("UTF-8"))
    val output = new ByteArrayOutputStream()

    process(input, output)

    output.toString("UTF-8") shouldBe """{"s":"HELLO WORLD"}"""
  }

}