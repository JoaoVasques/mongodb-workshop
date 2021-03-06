import sbt._
import Keys._
import play.Project._

object ApplicationBuild extends Build {

  val appName         = "workshop-startupscholarship"
  val appVersion      = "1.0-SNAPSHOT"

  val appDependencies = Seq(
    // Add your project dependencies here,
    jdbc,
    anorm,
    "org.mongodb" % "mongo-java-driver" % "2.11.2"
  )


  val main = play.Project(appName, appVersion, appDependencies).settings(
    // Add your own project settings here      
  )

}
