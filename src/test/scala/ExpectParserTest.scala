package jp.saisse.smt

import java.io.File
import org.scalatest.FlatSpec

class ExpectParserTest extends FlatSpec {

  case class MockContext(string: String, begin: Int, end: Int);

  "期待値のパース" should "エクセルのシートからから期待値をパースできる" in {
  	val book = Book.open(new File("./src/test/resources/StringTestCase.xlsx"))
  	val s = book.sheet("substring")

  	val result = new ExpectParser[String](s).parse(1, 3)((s: Sheet, row: Int, column: Int) => {
      s.getString(row, column)
    })
  	assert(result == "a")
  }
}