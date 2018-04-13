package com.mfarag.core

import java.io.{ByteArrayInputStream, ByteArrayOutputStream, InputStream}

import org.scalatest.{FunSuite, Matchers}

class MainTest extends FunSuite with Matchers {

  test("Process returns the upper case of the message it receives") {
    val input: InputStream = new ByteArrayInputStream("""{"s":"hello world"}""".getBytes("UTF-8"))
    val output = new ByteArrayOutputStream()

    new Main().process(input, output)

    output.toString("UTF-8") shouldBe """{"s":"HELLO WORLD"}"""
  }

}
