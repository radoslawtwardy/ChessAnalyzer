name := "ChessAnalyzer"

version := "0.1"

sbtVersion := "0.13.5"

scalaVersion := "2.11.2"

libraryDependencies ++= Seq(
  "org.scalatest" % "scalatest_2.11" % "2.2.1" % "test",
  "com.typesafe.scala-logging" %% "scala-logging" % "3.0.0",
  "ch.qos.logback" % "logback-classic" % "1.1.2"
)

