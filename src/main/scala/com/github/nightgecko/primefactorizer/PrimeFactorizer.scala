package com.github.nightgecko.primefactorizer

import scala.concurrent.Future

object PrimeFactorizer {

  def main(args: Array[String]): Unit = {
    //This program is interactive only. No arguments will be accepted
    runCommandLineInterface()
    println("Done!")
  }

  def runCommandLineInterface(): Unit = ???

  def parseCommand(command: String): Unit = ???

  def calculatePrimeFactors(input: Int): Future[List[Int]] = ???

  def findExistingPrimeFactors(input: Int): Future[Option[List[Int]]] = ???
}
