package com.example.SendMailThroughExcel.service;

import com.example.SendMailThroughExcel.entity.User;
import org.apache.commons.io.IOUtils;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

@Service
public class FileService {

    // Extract user data from Excel file
    public List<User> extractUsersFromExcel(MultipartFile excelFile) throws IOException {
        List<User> users = new ArrayList<>();
        XSSFWorkbook workbook = new XSSFWorkbook(excelFile.getInputStream());
        Sheet sheet = workbook.getSheetAt(0); // Assuming the data is in the first sheet

        for (Row row : sheet) {
            if (row.getRowNum() == 0) continue; // Skip header

            User user = new User();
            user.setUserId((int) row.getCell(0).getNumericCellValue());
            user.setName(row.getCell(1).getStringCellValue());
            user.setEmail(row.getCell(2).getStringCellValue());
            user.setPhone(row.getCell(3).getStringCellValue());
            user.setCity(row.getCell(4).getStringCellValue());


            String fileNames = row.getCell(5).getStringCellValue();
            List<String> fileNameList = Arrays.asList(fileNames.split(","));
            user.setFileNames(fileNameList);

            users.add(user);
        }

        workbook.close();
        return users;
    }

    // Extract files from zip
    public Map<String, File> extractFilesFromZip(MultipartFile zipFile) throws IOException {
        Map<String, File> extractedFiles = new HashMap<>();
        File tempZip = File.createTempFile("temp", ".zip");
        zipFile.transferTo(tempZip);

        try (ZipFile zip = new ZipFile(tempZip)) {
            Enumeration<? extends ZipEntry> entries = zip.entries();

            while (entries.hasMoreElements()) {
                ZipEntry entry = entries.nextElement();
                File file = new File(System.getProperty("java.io.tmpdir"), entry.getName());

                try (InputStream is = zip.getInputStream(entry);
                     FileOutputStream fos = new FileOutputStream(file)) {
                    IOUtils.copy(is, fos);
                }

                extractedFiles.put(entry.getName(), file);
            }
        }

        return extractedFiles;
    }
}

