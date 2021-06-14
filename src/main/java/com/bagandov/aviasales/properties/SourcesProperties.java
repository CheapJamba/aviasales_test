package com.bagandov.aviasales.properties;

import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("sources")
public class SourcesProperties {

    @Getter
    private String flightsSource = "flights.csv";

    @Getter
    private String flightsPattern = "[0-9]+,[A-Z]+,[A-Z]+,[0-9]{8},[0-9]{4},[0-9]{8},[0-9]{4},[^,]+";
}
