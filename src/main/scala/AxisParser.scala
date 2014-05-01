package jp.saisse.smt

abstract class AxisParser[A](val sheet: Sheet, axis: Axis) {
  def parse(row: Int, column: Int)( f: (Sheet, Int, Int) => Option[A]): Stream[A] = {
    f(sheet, row, column) match {
    case c: Some[A] => {
      val n = next(row, column)
      c.get #:: parse(n._1, n._2)(f)
    }
    case None => Stream.empty
    }
  }
  def next(row: Int, column: Int): (Int, Int)
}

class RowAxisParser[A](override val sheet: Sheet) extends AxisParser[A](sheet, VirticalAxis()) {
  def next(row: Int, column: Int): (Int, Int) = (row + 1, column)
}

class ColumnAxisParser[A](override val sheet: Sheet) extends AxisParser[A](sheet, HorizontalCotext()) {
  def next(row: Int, column: Int): (Int, Int) = (row, column + 1)
}

class Axis {}
case class VirticalAxis extends Axis
case class HorizontalCotext extends Axis
