package jp.saisse.smt

class ExpectParser[E](val sheet: Sheet){

  def parse(row: Int, column: Int)( f:(Sheet, Int, Int) => E): E = {f(sheet, row, column)}
}