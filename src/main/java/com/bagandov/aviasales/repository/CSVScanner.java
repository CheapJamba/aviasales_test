package com.bagandov.aviasales.repository;

import com.bagandov.aviasales.exception.CSVFormatException;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Scanner;
import java.util.regex.Pattern;

public class CSVScanner {

    private String resourcePath;

    private Pattern pattern;

    public CSVScanner(String resourcePath, String patternString) {
        this.resourcePath = resourcePath;
        this.pattern = Pattern.compile(patternString);
    }

    public String[] getRecordById(int id) {
        try {
            URL resource = getClass().getClassLoader().getResource(resourcePath);
            File file = new File(resource.toURI());
            Scanner scanner = new Scanner(file);
            scanner.nextLine();
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] splits = line.split(",");
                if (Integer.parseInt(splits[0]) == id) {
                    if (!pattern.matcher(line).matches()) {
                        throw new CSVFormatException("Строки в файле " + resourcePath +
                                " должны соответствовать формату: (" + pattern + ")");
                    }
                    return splits;
                }
            }
        } catch (URISyntaxException ex) {
            throw new RuntimeException("Error accessing resource", ex);
        } catch (FileNotFoundException ex) {
            throw new RuntimeException("File not found", ex);
        } catch (NumberFormatException ex) {
            throw new CSVFormatException("Строки в файле " + resourcePath + " должны соответствовать формату: ("
                    + pattern + ")", ex);
        }
        throw new RuntimeException("File doesn't contain string with id: " + id);
    }
}
