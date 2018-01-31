package com.future.scala

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.{Future, future}
import scala.util.{Failure, Success}

/**
  * Created by diegozarcogomez on 29/1/18.
  */
object ReturnAFuture extends App{

  implicit val baseTime = System.currentTimeMillis

  def longRunningComputation(i: Int): Future[Int] = future{
    sleep(100)
    i + 1
  }

  def sleep(duration: Long) { Thread.sleep(duration)}

  //Here comes the real code
  longRunningComputation(1000).onComplete{
    case Success(result) => println(s"result = $result")
    case Failure(e) => e.printStackTrace
  }

  sleep(1000)

}
