package com.bagandov.aviasales.repository;

import com.bagandov.aviasales.exception.EntryNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.event.annotation.BeforeTestClass;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.File;
import java.net.URI;
import java.net.URL;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class CSVScannerTest {

    @Autowired
    private FlightsCSVScanner scanner;

    @Test
    void getRecordById_WhenGivenExistingId_ThenReturnsStringArray() {
        String[] result = scanner.getRecordById(1);
        assertEquals("1", result[0]);
        assertEquals("SVO", result[1]);
        assertEquals("BKK", result[2]);
        assertEquals("20210701", result[3]);
        assertEquals("2010", result[4]);
        assertEquals("20210702", result[5]);
        assertEquals("1115", result[6]);
        assertEquals("SU-275", result[7]);
    }

    @Test
    void getRecordById_WhenGivenNotExistingId_ThenTrowsEntryNotFound() {
        assertThrows(EntryNotFoundException.class, () -> scanner.getRecordById(5));
    }
}