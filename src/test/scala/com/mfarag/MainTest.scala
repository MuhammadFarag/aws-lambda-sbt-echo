package com.mfarag

import java.io.{ByteArrayInputStream, ByteArrayOutputStream, InputStream}

import org.scalatest.{FunSuite, Matchers}

class MainTest extends FunSuite with Matchers {

  test("Echo returns whatever it receives") {
    val input: InputStream = new ByteArrayInputStream("Text".getBytes("UTF-8"))
    val output = new ByteArrayOutputStream()

    new Main().echo(input,output)

    output.toString("UTF-8") shouldBe "Text"
  }

}
