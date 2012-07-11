import sbt._
import Keys._

object BuildSettings
{
  val buildOrganization = "edu.berkeley.cs"
  val buildVersion = "1.1"
  val buildScalaVersion = "2.9.2"

  def apply(projectdir: String) = {
    Defaults.defaultSettings ++ Seq (
      organization := buildOrganization,
      version      := buildVersion,
      scalaVersion := buildScalaVersion,
      scalaSource in Compile := Path.absolute(file(projectdir + "/src"))
    )
  }
}

object ChiselBuild extends Build
{
  import BuildSettings._
  val chiselEnv = java.lang.System.getenv("CHISEL")
  println(chiselEnv)
  lazy val chisel = Project("chisel", file("chisel"), settings = BuildSettings(chiselEnv))
  lazy val tutorial = Project("tutorial", file("tutorial"), settings = BuildSettings("..")) dependsOn(chisel)
}
