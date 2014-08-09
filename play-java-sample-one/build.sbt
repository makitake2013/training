name := "play-java-sample"

version := "1.0-SNAPSHOT"

libraryDependencies ++= Seq(
  javaJdbc,
  javaEbean,
  cache,
  "commons-collections" % "commons-collections" % "3.2.1"
)     

play.Project.playJavaSettings
