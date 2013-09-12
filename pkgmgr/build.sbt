name := "webcfengine-pkgmgr"

version := "0.0.1"

organization := "pl.brosbit"

scalaVersion := "2.10.0"

resolvers ++= Seq("snapshots" at "http://oss.sonatype.org/content/repositories/snapshots",
                  "staging" at "http://oss.sonatype.org/content/repositories/staging",
                  "releases" at "http://oss.sonatype.org/content/repositories/releases"
                 )


scalacOptions ++= Seq("-deprecation", "-unchecked")

libraryDependencies ++= {
  Seq(
    "com.h2database" % "h2" % "1.3.167",
    "org.specs2" %% "specs2" % "1.14" % "test")
}
