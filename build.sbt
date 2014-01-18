name := "interview-challenge"

organization := "steve"

version := "1.0"

scalaVersion := "2.10.3"

scalacOptions ++= Seq("-deprecation", "-feature")

libraryDependencies ++= List(
  "com.typesafe.slick" %% "slick" % "2.0.0-M3",
  "org.slf4j" % "slf4j-nop" % "1.6.4",
  "org.scalatest" % "scalatest_2.10" % "2.0" % "test",
  "joda-time" % "joda-time" % "2.3",
  "org.joda" % "joda-convert" % "1.2",
  "org.jumpmind.symmetric.jdbc" % "mariadb-java-client" % "1.1.1"
)

