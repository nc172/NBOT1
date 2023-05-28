package commands;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;


public class Expense {
    public static void expense(double money, String purpose) {
        String excelPath = "finance.xlsx";
        try {
            FileInputStream finance = new FileInputStream(excelPath);
            Workbook workbook = WorkbookFactory.create(finance);

            // Get the sheet in the workbook
            Sheet sheet = workbook.getSheetAt(0);

            // Create a new row in the sheet
            int rowCount = sheet.getLastRowNum() + 1;
            Row row = sheet.createRow(rowCount);

            // Create cells in the row and write data to them
            Cell cell1 = row.createCell(0);
            cell1.setCellValue(java.time.LocalDate.now().toString()); //time

            Cell cell2 = row.createCell(1);
            cell2.setCellValue(money); //money

            Cell cell3 = row.createCell(2);
            cell3.setCellValue(purpose); //purposes

            // Write the workbook to the same file
            FileOutputStream outputStream = new FileOutputStream(excelPath);
            workbook.write(outputStream);
            workbook.close();
            System.out.println("Money added");
        } catch (Exception e) {
            System.out.println("File not found");
        }
    }

}
