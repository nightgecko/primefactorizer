package com.github.nightgecko.primefactorizer

import org.scalatest.AsyncFlatSpec

import scala.util.{Failure, Success}

class PrimeFactorizerSpec extends AsyncFlatSpec {

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

  it should "return a Failure when invoked with a 0" in {
    assert {
      parseCommand("0") match {
        case Failure(_) => true
        case _ => false
      }
    }
  }

  it should "return a Failure when invoked with a 1" in {
    assert {
      parseCommand("1") match {
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

  "PrimeFactorizer.calculatePrimeFactors" should "eventually return a iterable of 2 when invoked with a 2" in {
    calculatePrimeFactors(2) map {
      factors =>
        assert {
          factors == Iterable(2)
        }
    }
  }

  it should "eventually return a iterable of (2, 2, 7, 23) when invoked with a 644" in {
    calculatePrimeFactors(644) map {
      factors =>
        assert {
          factors == Iterable(2, 2, 7, 23)
        }
    }
  }

}
