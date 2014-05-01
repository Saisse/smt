package jp.saisse.smt

import java.io.{File, InputStream, FileInputStream, FileOutputStream}
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import org.apache.poi.ss.usermodel.{Workbook, WorkbookFactory, Sheet => PoiSheet, IndexedColors, CellStyle}

class Book(val workbook: Workbook) 
{
  def sheet(name: String): Sheet = {
  	new Sheet(workbook.getSheet(name))
  }

  def write(path: String) {
  	val out = new FileOutputStream(path)
    workbook.write(out)
    out.close()
  }
}

class Sheet(val sheet: PoiSheet) {
  def failed(row: Int , column: Int): Unit = {
    applyResult(row, column, IndexedColors.RED)
  }

  def succeed(row: Int , column: Int): Unit = {
    applyResult(row, column, IndexedColors.LIGHT_GREEN)
  }

  def applyResult(row: Int , column: Int, color: IndexedColors): Unit = {
    setupCell(row, column)
    val cell = sheet.getRow(row).getCell(column)
    val style = sheet.getWorkbook().createCellStyle()
    style.setFillPattern(CellStyle.SOLID_FOREGROUND)
    style.setFillForegroundColor(color.getIndex())
    cell.setCellStyle(style)
  }

  def getString(row: Int , column: Int): String = {
    val cell = sheet.getRow(row).getCell(column)
    cell.getRichStringCellValue().getString()
  }

  def getInt(row: Int , column: Int): Int = {
    val cell = sheet.getRow(row).getCell(column)
    cell.getNumericCellValue().toInt
  }

  def setupCell(row: Int , column: Int): Unit = {
    if(sheet.getRow(row) == null) {
      sheet.createRow(row)
    }
    val r = sheet.getRow(row)
    if(r.getCell(column) == null) {
      r.createCell(column)      
    }
  }

  def isValid(row: Int , column: Int): Boolean = {
    val r = sheet.getRow(row)
    if(r == null) {
      return false
    }
    if(r.getCell(column) == null) {
      return false
    }
    true
  }
}

class TestCaseSheet(val sheet: Sheet) {

  def test(axisParser: AxisParser[R]): Unit = {
  }
}

object Book {
  def open(file: File): Book = {
    new Book(WorkbookFactory.create(new FileInputStream(file)))
  }
}
