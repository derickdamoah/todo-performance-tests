import Dependencies._

enablePlugins(GatlingPlugin)

lazy val root = (project in file("."))
  .settings(
    inThisBuild(List(
      organization := "todo-performance-test",
      scalaVersion := "2.13.6",
      version := "0.1.0-SNAPSHOT"
    )),
    name := "My Gatling Test Suite",
    libraryDependencies ++= gatling
  )
//libraryDependencies += guice
libraryDependencies += "org.mongodb.scala" %% "mongo-scala-driver" % "4.10.2"
