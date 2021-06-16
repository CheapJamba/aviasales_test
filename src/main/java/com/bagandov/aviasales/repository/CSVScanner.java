package com.bagandov.aviasales.repository;

import com.bagandov.aviasales.exception.CSVFormatException;
import com.bagandov.aviasales.exception.EntryNotFoundException;
import com.bagandov.aviasales.exception.FileNotFoundUncheckedException;

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
        File file = new File(filePath);
        try {
            Scanner scanner = new Scanner(file);
            scanner.nextLine();
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] splits = line.split(",");
                if (Integer.parseInt(splits[0]) == id) {
                    if (!pattern.matcher(line).matches()) {
                        throw new CSVFormatException(filePath, pattern.pattern());
                    }
                    return splits;
                }
            }
        } catch (FileNotFoundException ex) {
            throw new FileNotFoundUncheckedException("File " + file.getAbsolutePath() + " not found", ex);
        } catch (NumberFormatException ex) {
            throw new CSVFormatException(filePath, pattern.pattern(), ex);
        }
        throw new EntryNotFoundException("File doesn't contain entry with id: " + id);
    }
}
