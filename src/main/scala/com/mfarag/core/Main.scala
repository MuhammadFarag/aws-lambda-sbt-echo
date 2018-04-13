package com.mfarag.core

import java.io.{InputStream, OutputStream}

import com.mfarag.Lambda

import scala.io.Source

class Main {

  def process(input: InputStream, output: OutputStream): Unit = {
    output.write(Lambda.process(Source.fromInputStream(input).mkString).getBytes)
  }
}
