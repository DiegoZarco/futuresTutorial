package com.future.scala

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.util.{Failure, Success}

/**
  * Created by diegozarcogomez on 29/1/18.
  */
object MapAndFlatMapFutures extends App{

  def longRunningComputation(i: Int): Future[Int] = Future{
    sleep(100)
    i + 1
  }

  def longRunningComputation2(i: Int): Future[String] = Future{
    sleep(100)
    "blablabla" + i
  }

  def sleep(duration: Long) { Thread.sleep(duration)}

  //FlatMap resolve the Futures letting us only one Future
  val flatMapcombination = longRunningComputation(10).flatMap{
    firstResult => longRunningComputation2(firstResult)
  }

  flatMapcombination onComplete{
    case Success(result) => println(s"The final result with flatMap $result")
    case Failure(e) => println(e)
  }

  //Map combine the Futures composing them in a Future[Future[T]]
  val mapCombination = longRunningComputation(10).map{
    firstResult => longRunningComputation2(firstResult)
  }

  mapCombination onComplete{
    case Success(result) => result onComplete{
      case Success(resultInsideFuture) => println(s"The final result with map is $resultInsideFuture")
    }
  }

  sleep(150000)

}
