package com.bagandov.aviasales.repository;

import com.bagandov.aviasales.exception.CSVFormatException;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.Pattern;

public class CSVScanner {

    private String filePath;

    private Pattern pattern;

    public CSVScanner(String filePath, String patternString) {
        this.filePath = filePath;
        this.pattern = Pattern.compile(patternString);
    }

    public String[] getRecordById(int id) {
        try {
            File file = new File(filePath);
            Scanner scanner = new Scanner(file);
            scanner.nextLine();
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] splits = line.split(",");
                if (Integer.parseInt(splits[0]) == id) {
                    if (!pattern.matcher(line).matches()) {
                        throw new CSVFormatException("Строки в файле " + filePath +
                                " должны соответствовать формату: (" + pattern + ")");
                    }
                    return splits;
                }
            }
        } catch (FileNotFoundException ex) {
            throw new RuntimeException("File not found", ex);
        } catch (NumberFormatException ex) {
            throw new CSVFormatException("Строки в файле " + filePath + " должны соответствовать формату: ("
                    + pattern + ")", ex);
        }
        throw new RuntimeException("File doesn't contain string with id: " + id);
    }
}
