package jp.saisse.smt

import java.io.File
import org.scalatest.FlatSpec

class AxisParserTest extends FlatSpec {

  "コンテキストのパース" should "エクセルからコンテキストをパースできる" in {
  	val book = Book.open(new File("./src/test/resources/StringTestCase.xlsx"))
  	val s = book.sheet("substring")

  	val result = new RowAxisParser[MockContext](s).parse(1, 0)((s: Sheet, row: Int, column: Int) => {
      (s.isValid(row, column)) match {
        case true => {
          Some(MockContext(
            s.getString(row, column)
          , s.getInt(row, column + 1)
          , s.getInt(row, column + 2)
        ))
        }
        case false => None
      }
    })
  	assert(result == MockContext("abc", 0, 1) #:: Stream.empty)
  }

  case class MockContext(string: String, begin: Int, end: Int);
}