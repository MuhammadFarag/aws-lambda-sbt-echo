package com.mfarag

import org.scalatest.{FunSuite, Matchers}

class LambdaTest extends FunSuite with Matchers {

  test("Lambda should change the message case"){
    Lambda.lambda(Request("hello world")) shouldBe Result("HELLO WORLD")
  }

}
