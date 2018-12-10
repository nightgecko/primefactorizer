package com.github.nightgecko.primefactorizer

import scala.concurrent.Future
import scala.util.Try

object PrimeFactorizer {

  def main(args: Array[String]): Unit = {
    //This program is interactive only. No arguments will be accepted
    runCommandLineInterface()
    println("Done!")
  }

  def runCommandLineInterface(): Unit = ???

  def parseCommand(command: String): Try[Int] = ???

  def calculatePrimeFactors(input: Int): Future[List[Int]] = ???

  def findExistingPrimeFactors(input: Int): Future[Option[List[Int]]] = ???
}
