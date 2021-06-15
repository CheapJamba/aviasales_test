package com.bagandov.aviasales.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("sources")
@Getter
@Setter
public class SourcesProperties {

    private String flightsSource = "flights.csv";

    private String flightsPattern = "\\d+,[A-Z]+,[A-Z]+,20\\d{2}[0-1]\\d[0-3]\\d,[0-2]\\d[0-5]\\d," +
            "20\\d{2}[0-1]\\d[0-3]\\d,[0-2]\\d[0-5]\\d,[^,]+";
}
