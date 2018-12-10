package com.github.nightgecko.primefactorizer

import java.util.Scanner

import scala.concurrent.Future
import scala.util.{Failure, Success, Try}

object PrimeFactorizer {
  private val scanner: Scanner = new Scanner(System.in)

  def main(args: Array[String]): Unit = {
    //This program is interactive only. No arguments will be accepted
    println("This program is designed to calculate the prime factors of a number provided")
    runCommandLineInterface()
    println("Done!")
  }

  def runCommandLineInterface(): Unit = {
    print(s"Please enter a whole number between 2 and ${java.lang.Integer.MAX_VALUE}:")
    scanner.next() match {
      case "quit" | "\"quit\"" =>
        println("Exiting program...")
      case input: String =>
        parseCommand(input) match {
          case Failure(_) =>
            println("Invalid input. Try again or type \"quit\" to exit.")
          case Success(value) => processPrimeFactors(value)
        }
        runCommandLineInterface()
    }
  }

  def processPrimeFactors(input: Int): List[Int] = ???

  def parseCommand(command: String): Try[Int] = Try(command.toInt).filter(_ > 1)

  def calculatePrimeFactors(input: Int): Future[List[Int]] = ???

  def findExistingPrimeFactors(input: Int): Future[Option[List[Int]]] = ???
}
