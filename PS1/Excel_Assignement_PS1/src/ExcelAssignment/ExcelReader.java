package ExcelAssignment;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.FileInputStream;
import java.io.IOException;

public class ExcelReader {
    public static void main(String[] args) {
        String excelFilePath = "Employee.xlsx";  
        try (FileInputStream fis = new FileInputStream(excelFilePath);
             Workbook workbook = new XSSFWorkbook(fis)) {

            // Get the first sheet
            Sheet sheet = workbook.getSheetAt(0);

            // Print the header row 
            Row headerRow = sheet.getRow(0);
            for (Cell cell : headerRow) {
                System.out.printf("%-15s", cell.getStringCellValue()); // Fixed-width formatting
            }
            System.out.println();  // Newline after header

            // Print separator
            System.out.println("------------------------------------------------------------");

            // Iterate over rows, starting from the second row (to skip the header)
            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                for (Cell cell : row) {
                    switch (cell.getCellType()) {
                        case STRING:
                            System.out.printf("%-15s", cell.getStringCellValue());  // String with fixed width
                            break;
                        case NUMERIC:
                            if (DateUtil.isCellDateFormatted(cell)) {
                                System.out.printf("%-15s", cell.getDateCellValue());  // Date values
                            } else {
                                double numericValue = cell.getNumericCellValue();
                                // If it's a whole number, cast it to int and format without decimals
                                if (numericValue == (int) numericValue) {
                                    System.out.printf("%-15d", (int) numericValue);
                                } else {
                                    System.out.printf("%-15.2f", numericValue);  // Decimal numbers
                                }
                            }
                            break;
                        default:
                            System.out.printf("%-15s", "Unknown");  // For unrecognized types
                            break;
                    }
                }
                System.out.println();  // Move to the next line after each row is processed
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
