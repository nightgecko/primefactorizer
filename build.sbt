name := "primefactorizer"

version := "0.1"

scalaVersion := "2.12.8"

libraryDependencies += "org.apache.commons" % "commons-math3" % "3.6"
libraryDependencies += "com.typesafe.play" %% "play-json" % "2.6.11"
libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.5" % Test

mainClass in (Compile, run) := Some("com.github.nightgecko.primefactorizer.PrimeFactorizer")
