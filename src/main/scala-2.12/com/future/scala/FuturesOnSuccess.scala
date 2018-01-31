package com.future.scala

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.util.Random

/**
  * Created by diegozarcogomez on 29/1/18.
  */
object FuturesOnSuccess extends App{

  val f = Future{
    sleep(Random.nextInt(500))
    if (Random.nextInt(500) > 250) throw new Exception("Yikes!")
  }

  f onSuccess{
    case result => println(s"Success: $result")
  }

  f onFailure{
    case t => println(s"Exception: ${t.getMessage}")
  }

  println("A ..."); sleep(100)
  println("B ..."); sleep(100)
  println("C ..."); sleep(100)
  println("D ..."); sleep(100)
  println("E ..."); sleep(100)
  println("F ..."); sleep(100)

  def sleep(duration: Long) { Thread.sleep(duration)}

}
