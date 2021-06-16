package com.bagandov.aviasales.repository;

import com.bagandov.aviasales.model.Flight;
import com.bagandov.aviasales.properties.SourcesProperties;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class FlightRepositoryTest {

    @MockBean
    FlightsCSVScanner scanner;

    @Autowired
    SourcesProperties properties;

    @Autowired
    FlightRepository flightRepo;

    @Test
    void findFlightById_WhenHavingReceivedStringArray_InstantiatesFlightProperly() {
        MockitoAnnotations.openMocks(this);
        String[] scannerResult = {"1", "SVO", "BKK", "20210704", "2010", "20210705", "1115", "SU-275"};
        Mockito.when(scanner.getRecordById(1)).thenReturn(scannerResult);
        Flight flight = flightRepo.findFlightById(1);
        assertEquals("SU-275", flight.getNumber());
        assertEquals("05.07.2021 11:15", flight.getArrival());
        assertEquals("04.07.2021 20:10", flight.getDeparture());
    }
}