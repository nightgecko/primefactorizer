package com.github.nightgecko.primefactorizer

import java.io.{File, FileInputStream, FileNotFoundException}
import java.util.Scanner
import java.util.concurrent.TimeUnit

import org.apache.commons.io.FileUtils
import org.apache.commons.math3.primes.Primes
import play.api.libs.json.{JsArray, JsDefined, Json}

import scala.collection.JavaConverters._
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.Duration
import scala.concurrent.{Await, Future}
import scala.util.{Failure, Success, Try}

object PrimeFactorizer {
  private val scanner: Scanner = new Scanner(System.in)
  private lazy val defaultStorageDir: File = new File(System.getProperty("user.dir") + "/calcStorage")

  def main(args: Array[String]): Unit = {
    //This program is interactive only. No arguments will be accepted
    println("This program is designed to calculate the prime factors of a number provided")
    runCommandLineInterface(defaultStorageDir)
    println("Done!")
  }

  private def runCommandLineInterface(storageDir: File): Unit = {
    print(s"Please enter a whole number between 2 and ${java.lang.Integer.MAX_VALUE}:")
    scanner.next() match {
      case "quit" | "\"quit\"" =>
        println("Exiting program...")
      case input: String =>
        parseCommand(input) match {
          case Failure(_) =>
            println("Invalid input. Try again or type \"quit\" to exit.")
          case Success(value) =>
            val primeFactors = Await.result(processPrimeFactors(value, storageDir), Duration(30, TimeUnit.SECONDS))
            println(s"$input's prime factors are: ${primeFactors.mkString(", ")}")
        }
        runCommandLineInterface(storageDir)
    }
  }

  def processPrimeFactors(input: Int, storageDir: File): Future[Iterable[Int]] = {
    findExistingPrimeFactors(input, storageDir) recoverWith {
      case e: FileNotFoundException =>
        calculatePrimeFactors(input) andThen {
          case Success(factors) =>
            val outputFile = FileUtils.getFile(storageDir, s"$input.json")
            val json = Json.obj("primeFactors" -> factors)
            FileUtils.writeStringToFile(outputFile, json.toString(), "UTF-8")
        }
    }
  }

  def parseCommand(command: String): Try[Int] = Try(command.toInt).filter(_ > 1)

  def calculatePrimeFactors(input: Int): Future[Iterable[Int]] = Future {
    Primes.primeFactors(input).asScala.map(_.toInt)
  }

  def findExistingPrimeFactors(input: Int, storageDir: File):Future[Iterable[Int]] = {
    Future {
      val targetFile = new File(storageDir, s"$input.json")
      val json = Json.parse(new FileInputStream(targetFile))
      json \ "primeFactors" match {
        case JsDefined(JsArray(list)) => list.map(_.as[Int])
        case _ => throw new Exception(s"Could not parse file at ${targetFile.getPath}")
      }
    }
  }
}
