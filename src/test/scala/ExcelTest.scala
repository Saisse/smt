package jp.saisse.smt

import java.io.File
import org.scalatest.FlatSpec

class BookTest extends FlatSpec {

  "open file " should "succeed" in {
  	Book.open(new File("./src/test/resources/test.xlsx"))
  }

  "test failed" should "succeed" in {
  	val book = Book.open(new File("./src/test/resources/test.xlsx"))
  	val s = book.sheet("Sheet1")
  	s.failed(0, 0)
  	s.succeed(0, 1)
  	book.write("target/out.xlsx")
  }

}