version in ThisBuild := "0.1.0-SNAPSHOT"

scalaVersion in ThisBuild := "2.13.10"


// https://mvnrepository.com/artifact/com.lihaoyi/requests
libraryDependencies += "com.lihaoyi" %% "requests" % "0.7.1"

//// SBT
//libraryDependencies += "com.lihaoyi" %% "upickle" % "0.7.1"
libraryDependencies += "com.lihaoyi" %% "upickle" % "0.9.5"
//// SBT
libraryDependencies += "com.lihaoyi" %% "ujson" % "0.7.1"
//libraryDependencies += "com.lihaoyi" %% "requests" % "0.1.8"
// SBT
libraryDependencies +="com.lihaoyi" %% "os-lib" % "0.8.1"

lazy val root = (project in file("."))
  .settings(
    name := "z2_2"
  )
