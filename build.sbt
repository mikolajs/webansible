import sbtassembly.Plugin._
import AssemblyKeys._
import twirl.sbt.TwirlPlugin._

organization  := "pl.brosbit"

name := "webansible"

version       := "0.1.1"

scalaVersion  := "2.10.2"

scalacOptions := Seq("-unchecked", "-deprecation", "-encoding", "utf8")

seq(Twirl.settings: _*)

resolvers ++= Seq(
  "spray repo" at "http://repo.spray.io/"
)

assemblySettings

libraryDependencies ++= Seq(
  "io.spray"            %   "spray-can"     % "1.1-M8",
  "io.spray"            %   "spray-routing" % "1.1-M8",
  "io.spray"            %   "spray-testkit" % "1.1-M8",
  "com.typesafe.akka"   %%  "akka-actor"    % "2.1.4",
  "com.typesafe.akka"   %%  "akka-testkit"  % "2.1.4",
  "com.h2database" % "h2" % "1.3.167",
  "org.specs2"          %%  "specs2"        % "1.14" % "test",
  "org.seleniumhq.selenium" % "selenium-java" % "2.28.0" % "test"
)

seq(Revolver.settings: _*)
