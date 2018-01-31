package com.future.scala

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.util.Success

/**
  * Created by diegozarcogomez on 29/1/18.
  */
object AndThenFuture extends App{

  def longRunningComputation(i: Int): Future[Int] = Future{
    sleep(100)
    i + 1
  }

  def sleep(duration: Long) { Thread.sleep(duration)}


  //And Then sirve para hacer varias operaciones con el mismo resultado del futuro
  //como podemos ver en este ejemplo siempre es el mismo resultado que es el resultado del futuro


  longRunningComputation(10).andThen{
    case Success(result) => println(result); result * 2
  }.andThen{
    case Success(result1) => println(result1); result1 * 3
  }.andThen{
    case Success(result2) => println(result2)
  }

  sleep(1500)

}
