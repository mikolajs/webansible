import sbt._
import Keys._
import com.github.siasia._
import PluginKeys._
import WebPlugin._
import WebappPlugin._

object LiftProjectBuild extends Build {
  override lazy val settings = super.settings ++ buildSettings
  
  lazy val buildSettings = Seq(
    organization := "pl.brosbit",
    version      := "0.1",
    scalaVersion := "2.9.2")
    
  
  def yourWebSettings = webSettings ++ Seq(
    port in container.Configuration := 8080
    )
  
  lazy val liftQuickstart = Project(
    id = "apt-fallower",
    base = file("."),
    settings = defaultSettings ++ yourWebSettings)
    
  lazy val defaultSettings = Defaults.defaultSettings ++ Seq(
    name := "apt-fallower",
    resolvers ++= Seq(
      "Typesafe Repo" at "http://repo.typesafe.com/typesafe/releases", 
      "Java.net Maven2 Repository" at "http://download.java.net/maven/2/"
    ),
    libraryDependencies ++= {
	  val liftVersion = "2.5-M3"
	  Seq(
	    "net.liftweb" %% "lift-webkit" % liftVersion % "compile",
	    "net.liftweb" %% "lift-mapper" % liftVersion % "compile",
	    "org.eclipse.jetty.orbit" % "javax.servlet" % "3.0.0.v201112011016" % "container" artifacts (Artifact("javax.servlet", "jar", "jar")),	 
	    "org.eclipse.jetty" % "jetty-webapp" % "8.1.4.v20120524" % "container",
	    "ch.qos.logback" % "logback-classic" % "1.0.0" % "compile",
	    "com.h2database" % "h2" % "1.3.170" % "compile",
	    "org.scalatest" %% "scalatest" % "1.6.1" % "test" 
	   )
	},

    // compile options
    scalacOptions ++= Seq("-encoding", "UTF-8", "-deprecation", "-unchecked"),
    javacOptions  ++= Seq("-Xlint:unchecked", "-Xlint:deprecation"),

    // show full stack traces
    testOptions in Test += Tests.Argument("-oF")
  )
}


