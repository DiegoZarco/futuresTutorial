package com.future.scala

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.util.{Failure, Random, Success}

/**
  * Created by diegozarcogomez on 29/1/18.
  */
object FuturesOnComplete extends App{

  println("1 - starting calculation...")
  val f = Future {
    sleep(Random.nextInt(500))
    42
  }

  println("2 - before onComplete")
  f.onComplete{
    case Success(value) => println(s"Got the callbaack, meaning = $value")
    case Failure(e) => e.printStackTrace
  }

  println("A ..."); sleep(100)
  println("B ..."); sleep(100)
  println("C ..."); sleep(100)
  println("D ..."); sleep(100)
  println("E ..."); sleep(100)
  println("F ..."); sleep(100)

  def sleep(duration: Long) { Thread.sleep(duration)}
}
