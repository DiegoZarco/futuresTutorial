package com.future.scala

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.{Future, future}
import scala.util.{Failure, Success}

/**
  * Created by diegozarcogomez on 29/1/18.
  */
object FallBack extends App{

  def longRunningComputation(i: Int): Future[Int] = future{
    throw new Exception("Boom!")
  }

  //If we use the fallback we can manage the exception, otherwise it would be the Failure
  val result = longRunningComputation(100).fallbackTo(Future{42})

  result.onComplete{
    case Success(result) => println(s"El resultado es: $result")
    case Failure(e) => { println("Failure"); e.printStackTrace}
  }

  //Stop the JVM
  sleep(1000)

  def sleep(duration: Long) { Thread.sleep(duration)}



}
