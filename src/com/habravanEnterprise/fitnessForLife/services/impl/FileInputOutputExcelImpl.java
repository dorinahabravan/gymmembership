package com.habravanEnterprise.fitnessForLife.services.impl;

import com.habravanEnterprise.fitnessForLife.models.Client;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import com.habravanEnterprise.fitnessForLife.services.FileInputOutputIntf;

public class FileInputOutputExcelImpl implements FileInputOutputIntf {
//Metoda SAVE face exportul in Excel.

    @Override
    public void save(String fileName, List<Client> listaClienti) {

        XSSFWorkbook workbook = new XSSFWorkbook();
        Sheet clientSheet = workbook.createSheet("List of clients");
        int rowIndex = 0;
        for (Client client : listaClienti) {
            Row row = clientSheet.createRow(rowIndex++);
            int cellIndex = 0;
            row.createCell(cellIndex++).setCellValue(client.getCustomerid());
            row.createCell(cellIndex++).setCellValue(client.getFullname());
            row.createCell(cellIndex++).setCellValue(client.getEmail_address());
            row.createCell(cellIndex++).setCellValue(client.getDate_of_birth().toString());
            row.createCell(cellIndex++).setCellValue(client.getLocation());
            row.createCell(cellIndex++).setCellValue(client.getPhone_number());

        }

        try {
            try (FileOutputStream fileOutputStream = new FileOutputStream(fileName)) {
                workbook.write(fileOutputStream);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FileInputOutputExcelImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FileInputOutputExcelImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
// Metoda READ face importul din Excel.

    @Override
    public List<Client> readFile(String fileName) {
        List<Client> lista = new ArrayList<>();

        try {
            FileInputStream fileInputStream = new FileInputStream(fileName);
            XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream);
            Sheet sheet = workbook.getSheetAt(0);
            Iterator rowIterator = sheet.iterator();

            while (rowIterator.hasNext()) {
                Client client = new Client();
                Row row = (Row) rowIterator.next();
                Iterator cellIterator = row.cellIterator();

                while (cellIterator.hasNext()) {
                    Cell cell = (Cell) cellIterator.next();

                    if (cell.getColumnIndex() == 0) {

                        client.setCustomerid((int) cell.getNumericCellValue());

                    } else if (cell.getColumnIndex() == 1) {
                        client.setFullname(cell.getStringCellValue());

                    } else if (cell.getColumnIndex() == 2) {
                        client.setEmail_address(cell.getStringCellValue());

                    } else if (cell.getColumnIndex() == 3) {
                        client.setDate_of_birth(LocalDate.parse(cell.getStringCellValue(),
                                DateTimeFormatter.BASIC_ISO_DATE));

                    } else if (cell.getColumnIndex() == 4) {
                        client.setLocation(cell.getStringCellValue());

                    } else if (cell.getColumnIndex() == 5) {
                        client.setPhone_number((int) cell.getNumericCellValue());

                    }

                }
                lista.add(client);
                fileInputStream.close();

            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(FileInputOutputExcelImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FileInputOutputExcelImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }

}
