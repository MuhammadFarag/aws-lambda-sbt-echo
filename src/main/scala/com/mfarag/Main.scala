package com.mfarag

import java.io.{InputStream, OutputStream}

import scala.io.Source

class Main {

  def echo(input: InputStream, output: OutputStream): Unit = {
    output.write(Source.fromInputStream(input).mkString.getBytes)
  }
}

