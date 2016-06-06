package com.kremor.utility;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public final class ExcelUtils {
    private ExcelUtils() {
    }
    private static XSSFWorkbook excelWBook;
    private static XSSFSheet excelWSheet;
    private static XSSFCell cell;
    private static XSSFRow row;
    private final Logger log = LogManager.getLogger(ExcelUtils.class);

    public static String[][] getTableArray(String filePath, String sheetName) throws Exception {
        String[][] tableArray = null;

        try {
            FileInputStream excelFile = new FileInputStream(filePath);
            // Access the required test data sheet
            excelWBook = new XSSFWorkbook(excelFile);
            excelWSheet = excelWBook.getSheet(sheetName);
            int startCol = 0;
            int startRow = 1;
            int ci, cj;
            int totalRows = excelWSheet.getLastRowNum();;
            int totalCols = excelWSheet.getRow(0).getLastCellNum();
            tableArray = new String[totalRows][totalCols];
            ci = 0;
            for (int i = startRow; i < totalRows; i++, ci++) {
                cj = 0;
                for (int j = startCol; j < totalCols; j++, cj++) {
                    String cellString = getCellData(i, j);
                    tableArray[ci][cj] = cellString;
                    System.out.print("\t \t " + tableArray[ci][cj]);
                }
                System.out.println("");
            }
        }
        catch (FileNotFoundException e) {
            System.out.println("Could not read the Excel sheet");
            e.printStackTrace();
        }

        catch (IOException e) {
            System.out.println("Could not read the Excel sheet");
            e.printStackTrace();
        }
        return tableArray;
    }

    //This method is to read the test data from the Excel cell, in this we are passing parameters as Row num and Col num
    public static String getCellData(int rowNum, int colNum) throws Exception {
        try {
            cell = excelWSheet.getRow(rowNum).getCell(colNum);
            cell.setCellType(Cell.CELL_TYPE_STRING);
            String cellData = cell.getStringCellValue();
            return cellData;

        } catch (Exception e){  return "";}
    }

    //This method is to write in the Excel cell, Row num and Col num are the parameters
    @SuppressWarnings("static-access")
    public static void setCellData(String result,  int rowNum, int colNum) throws Exception {
        try {
            row = excelWSheet.getRow(rowNum);
            cell = row.getCell(colNum, row.RETURN_BLANK_AS_NULL);
            if (cell == null) {
                cell = row.createCell(colNum);
                cell.setCellValue(result);
            } else {
                cell.setCellValue(result);
            }

            // Constant variables Test Data path and Test Data file name
            FileOutputStream fileOut = new FileOutputStream(Constants.PATH_TEST_DATA + Constants.FILE_TEST_DATA);
            excelWBook.write(fileOut);
            fileOut.flush();
            fileOut.close();

        } catch (Exception e) {
            throw (e);
        }

    }

    public int getRowUsed() throws Exception {

        try {
            int rowCount = excelWSheet.getLastRowNum();
            log.info("Total number of Row used return as &lt; " + rowCount + " &gt;.");
            return rowCount;
        }
        catch (Exception e) {
            log.error("Class ExcelUtil | Method getRowUsed | Exception desc : "+ e.getMessage());
            System.out.println(e.getMessage());
            throw (e);
        }
    }

    public static String getTestCaseName(String sTestCase)throws Exception {
        String value = sTestCase;
        try{
            int posi = value.indexOf("@");
            value = value.substring(0, posi);
            posi = value.lastIndexOf(".");
            value = value.substring(posi + 1);

            return value;

        } catch (Exception e){ throw (e);}
    }
}



