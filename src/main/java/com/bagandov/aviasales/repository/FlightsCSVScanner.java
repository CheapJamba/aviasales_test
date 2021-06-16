package com.bagandov.aviasales.repository;

import com.bagandov.aviasales.properties.SourcesProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class FlightsCSVScanner extends CSVScanner {

    @Autowired
    public FlightsCSVScanner(SourcesProperties props) {
        super(props.getFlightsSource(), props.getFlightsPattern());
    }
}
