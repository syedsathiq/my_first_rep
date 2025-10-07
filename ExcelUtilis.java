package utilis;

import org.apache.poi.ss.usermodel.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Utility to read test data (username, password, role) from Excel
 */
public class ExcelUtilis {

    /**
     * Reads Excel sheet and returns data as Object[][]
     *
     * @param filePath  full path of Excel file
     * @param sheetName sheet name
     * @return 2D Object array for TestNG @DataProvider
     */
    public static Object[][] getUserData(String filePath, String sheetName) {
        List<Object[]> data = new ArrayList<>();

        try (FileInputStream fis = new FileInputStream(filePath);
             Workbook workbook = WorkbookFactory.create(fis)) {

            Sheet sheet = workbook.getSheet(sheetName);
            if (sheet == null) {
                throw new RuntimeException("❌ Sheet not found: " + sheetName);
            }

            Iterator<Row> rowIterator = sheet.iterator();

            // Skip header row if present
            if (rowIterator.hasNext()) rowIterator.next();

            // Read each row
            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();

                String username = getCellValueAsString(row.getCell(0)).trim();
                String password = getCellValueAsString(row.getCell(1)).trim();
                String role     = getCellValueAsString(row.getCell(2)).trim();

                // skip empty rows
                if (!username.isEmpty() && !password.isEmpty() && !role.isEmpty()) {
                    data.add(new Object[]{username, password, role});
                }
            }

        } catch (IOException e) {
            System.err.println("❌ Error reading Excel file: " + e.getMessage());
            e.printStackTrace();
        }

        return data.toArray(new Object[0][0]);
    }

    /**
     * Converts cell to string safely
     */
    private static String getCellValueAsString(Cell cell) {
        if (cell == null) return "";

        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue();

            case NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    // return date as string if needed
                    return cell.getDateCellValue().toString();
                } else {
                    // remove .0 for integers
                    double num = cell.getNumericCellValue();
                    if (num == (long) num)
                        return String.valueOf((long) num);
                    else
                        return String.valueOf(num);
                }

            case BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());

            case FORMULA:
                try {
                    return cell.getStringCellValue();
                } catch (IllegalStateException e) {
                    return String.valueOf(cell.getNumericCellValue());
                }

            default:
                return "";
        }
    }
}
