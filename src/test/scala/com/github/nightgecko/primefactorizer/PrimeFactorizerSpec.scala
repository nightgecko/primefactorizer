package com.github.nightgecko.primefactorizer

import org.scalatest.FlatSpec

import scala.util.{Failure, Success}

class PrimeFactorizerSpec extends FlatSpec {

  import PrimeFactorizer._

  "PrimeFactorizer.parseCommand" should "return a Failure when invoked with a non-number" in {
    assert {
      parseCommand("This is not a number") match {
        case Failure(_) => true
        case _ => false
      }
    }
  }

  it should "return a Failure when invoked with a non-whole number" in {
    assert {
      parseCommand("13.37") match {
        case Failure(_) => true
        case _ => false
      }
    }
  }

  it should "return a Failure when invoked with a negative number" in {
    assert {
      parseCommand("-1337") match {
        case Failure(_) => true
        case _ => false
      }
    }
  }

  it should "return a Failure when invoked with a number exceeding the Int max value" in {
    assert {
      parseCommand(s"1${java.lang.Integer.MAX_VALUE}") match {
        case Failure(_) => true
        case _ => false
      }
    }
  }

  it should "return a Success is invoked with a postive whole number within Int bounds" in {
    assert {
      parseCommand("1337") match {
        case Success(_) => true
        case _ => false
      }
    }
  }

}
