package com.future.scala

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.{Future, future}

/**
  * Created by diegozarcogomez on 29/1/18.
  */
object ForComprehension extends App{

  def runAlgorithm(i: Int): Future[Int] = future{
    sleep(500)
    i + 1
  }

  def sleep(duration: Long) { Thread.sleep(duration)}

  println("starting futures")
  val result1 = runAlgorithm(10)
  val result2 = runAlgorithm(20)
  val result3 = runAlgorithm(30)

  println("before for-comprehension")
  val result = for {
    r1 <- result1
    r2 <- result2
    r3 <- result3
  } yield(r1+r2+r3)

  println("before onSucess")
  result onSuccess{
    case result => println(s"total = $result")
  }

  println("before sleep at the end")
  sleep(2000)



}
