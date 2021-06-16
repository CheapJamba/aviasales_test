package com.bagandov.aviasales.repository;

import com.bagandov.aviasales.properties.SourcesProperties;
import com.bagandov.aviasales.model.Flight;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class FlightRepository {

    private FlightsCSVScanner scanner;

    @Autowired
    public FlightRepository(FlightsCSVScanner scanner) {
        this.scanner = scanner;
    }

    public Flight findFlightById(int targetId) {
        String[] rawFields = scanner.getRecordById(targetId);
        int id = Integer.parseInt(rawFields[0]);
        String departure = rawToDate(rawFields[3], rawFields[4]);
        String arrival = rawToDate(rawFields[5], rawFields[6]);
        return new Flight(id, rawFields[7], departure, arrival);
    }

    private static String rawToDate(String dateString, String timeString) {
        String year = dateString.substring(0, 4);
        String month = dateString.substring(4, 6);
        String day = dateString.substring(6, 8);
        String hour = timeString.substring(0, 2);
        String minute = timeString.substring(2, 4);
        return String.format("%s.%s.%s %s:%s", day, month, year, hour, minute);
    }
}
