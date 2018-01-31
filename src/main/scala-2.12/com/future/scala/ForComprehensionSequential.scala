package com.future.scala

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

/**
  * Created by diegozarcogomez on 31/1/18.
  */
object ForComprehensionSequential extends App{

  def runAlgorithm1(i: Int): Future[Int] = Future{
    println("runAlgorithm1")
    sleep(500)
    i + 1
  }

  def runAlgorithm2(i: Int): Future[Int] = Future{
    println("runAlgorithm2")
    sleep(400)
    i + 1
  }

  def runAlgorithm3(i: Int): Future[Int] = Future{
    println("runAlgorithm3")
    sleep(200)
    i + 1
  }

  def sleep(duration: Long) { Thread.sleep(duration)}

  //As we are launching the futures inside the for-comprehension all the futures are going to be launched in sequential
  println("before for-comprehension")
  val result = for {
    r1 <- runAlgorithm1(10)
    r2 <- runAlgorithm2(20)
    r3 <- runAlgorithm3(30)
  } yield(r1+r2+r3)

  println("before onSucess")
  result onSuccess{
    case result => println(s"total = $result")
  }

  println("before sleep at the end")
  sleep(2000)
}
