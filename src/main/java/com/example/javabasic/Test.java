package com.example.javabasic;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Test {

    public static void main(String[] args) {
        String excelFilePath = "C:\\Users\\soonyoong.chia\\task\\trading contest issue\\patch 137 non DP.xlsx";
        try (FileInputStream fis = new FileInputStream(excelFilePath);
             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheetAt(0); // Assuming data is in the first sheet
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            DecimalFormat decimalFormat = new DecimalFormat("0");
            // Iterate through rows
            for (int rowIndex = 1; rowIndex < 100; rowIndex++) {
                Row row = sheet.getRow(rowIndex);
                String pid = "";
                String acc = "";
                String countryCode = "";
                String realName = "";
                String currency = "";
                String createTime = "";
                String updateTime = "";
                String initialBalance = "";
                String floating = "";
                // Iterate through columns
                for (int colIndex = 0; colIndex < 15; colIndex++) {
                    Cell cell = row.getCell(colIndex);
                    //System.out.print(cell.getStringCellValue() + "\t");
                    String value = "";
                    switch (cell.getCellType()) {
                        case STRING:
                            value = cell.getStringCellValue();
                            //System.out.print(cell.getStringCellValue() + "\t");
                            break;
                        case NUMERIC:
                            if (DateUtil.isCellDateFormatted(cell)) {
                                Date date = cell.getDateCellValue();
                                value = dateFormat.format(date);
                            } else {
                                value =  decimalFormat.format(cell.getNumericCellValue());
                                //value = String.valueOf(cell.getNumericCellValue());
                            }
                            //System.out.print(cell.getNumericCellValue() + "\t");
                            break;
                        case BOOLEAN:
                            value = String.valueOf(cell.getBooleanCellValue());
                            //System.out.print(cell.getBooleanCellValue() + "\t");
                            break;
                        case FORMULA:
                            value = String.valueOf(cell.getCellFormula());
                            //System.out.print(cell.getCellFormula() + "\t");
                            break;
                        default:
                            //System.out.print("NULL\t");
                            break;
                    }
                    String[] parts;
                    switch (colIndex) {
                        case 4:
                             parts = value.split("\\.");
                            pid =  parts[0];
                            break;
                        case 5:
                            parts = value.split("\\.");
                            acc = parts[0];
                            break;
                        case 1:
                            parts = value.split("\\.");
                            //Integer i = Integer.parseInt(value);
                            countryCode = parts[0];
                            break;
                        case 2:
                            realName = value;
                            break;
                        case 3:
                            currency = value;
                            break;
                        case 14:
                            updateTime = value;
                            break;
                        case 13:
                            createTime = value;
                            break;
                        case 9:
                            initialBalance = value;
                            break;
                        case 10:
                            floating = value;
                            break;
                    }

                }
                // for with DP
               /* String template = "(%s, 5033, 42, %s, %s, '%s', '%s', 0.00, 0.00, " +
                        "0.00, NULL, NULL, 1, NULL, 0, '%s', '2024-07-17 00:09:00', '%s', 0, " +
                        "0.00000000, 0.00000000, 0.00000000, '{\"validDate\":\"%s\",\"initialBalance\":%s," +
                        "\"floatingPLTotal\":%s}', NULL),";
                        String result = String.format(template, pid, acc,countryCode, realName, currency,
                        updateTime,  createTime, updateTime,initialBalance, floating);*/

                //for non DP
                String template = "(%s, 5033, 42, %s, %s, '%s', '%s', 0.00, 0.00, " +
                        "0.00, NULL, NULL, 1, NULL, 0, '%s', '2024-07-17 00:09:00', '%s', 0, " +
                        "0.00000000, 0.00000000, 0.00000000, NULL, NULL),";
                String result = String.format(template, pid, acc,countryCode, realName, currency,
                        updateTime,  createTime);
                System.out.println(result); // Move to the next line
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
