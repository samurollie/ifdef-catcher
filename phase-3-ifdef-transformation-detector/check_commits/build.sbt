name := "ifdef-catcher"

version := "0.1"

scalaVersion := "2.13.6"

idePackagePrefix := Some("ic.ufal.ifdefcatcher")

// https://mvnrepository.com/artifact/org.repodriller/repodriller
libraryDependencies += "org.repodriller" % "repodriller" % "2.0.1"
// https://mvnrepository.com/artifact/commons-io/commons-io
libraryDependencies += "commons-io" % "commons-io" % "2.9.0"
// https://mvnrepository.com/artifact/com.fasterxml.jackson.core/jackson-databind
libraryDependencies += "com.fasterxml.jackson.core" % "jackson-databind" % "2.12.3"
// https://mvnrepository.com/artifact/com.fasterxml.jackson.module/jackson-module-scala
libraryDependencies += "com.fasterxml.jackson.module" %% "jackson-module-scala" % "2.12.3"

libraryDependencies += "org.apache.logging.log4j" % "log4j-core" % "2.20.0"
libraryDependencies += "org.apache.logging.log4j" % "log4j-api" % "2.20.0"
libraryDependencies += "org.apache.logging.log4j" % "log4j-slf4j-impl" % "2.20.0"

assemblyMergeStrategy in assembly := {
  case PathList("META-INF", "services", xs @ _*) => MergeStrategy.concat
  case PathList("META-INF", xs @ _*) => MergeStrategy.discard
  case x => MergeStrategy.first
}