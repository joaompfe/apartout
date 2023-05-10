ThisBuild / scalaVersion := "3.2.2"
ThisBuild / organization := "pt.jmpfe"

lazy val example = (project in file("."))
  .settings(
    name := "Apartout",
    libraryDependencies += "org.scalactic" %% "scalactic" % "3.2.15",
    libraryDependencies += "org.scalatest" %% "scalatest" % "3.2.15" % "test",
    libraryDependencies += "dev.zio" %% "zio" % "2.0.13",
    libraryDependencies += "dev.zio" %% "zio-http" % "3.0.0-RC1",
  )
