scalaVersion := "2.13.0"

name := "sbt-jmh-template"

val bench = project.enablePlugins(JmhPlugin)
