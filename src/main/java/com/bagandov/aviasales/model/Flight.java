package com.bagandov.aviasales.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Flight {

    Integer id;

    String number;

    String departure;

    String arrival;
}
