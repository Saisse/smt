organization := "jp.saisse"

name := "smt"

version := "dev"

libraryDependencies ++= Seq(
    "org.apache.poi" % "poi" % "3.10-FINAL"
  , "org.apache.poi" % "poi-ooxml" % "3.10-FINAL"
  , "org.scalatest" % "scalatest_2.10" % "2.1.0" % "test"
)

testOptions in Test += Tests.Argument(TestFrameworks.ScalaTest, "-oD")

crossPaths := false
