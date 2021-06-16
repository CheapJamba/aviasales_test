package com.bagandov.aviasales.controller;

import com.bagandov.aviasales.properties.SourcesProperties;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;


import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class FlightControllerTest { //интеграционный тест

    @Autowired
    private MockMvc mvc;

    @Autowired
    SourcesProperties props;



    @Test
    void getFlightById_WhenGivenExistingId_ReturnsFlight() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders.get("/flights/4");
        mvc.perform(request).andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(4))
                .andExpect(jsonPath("$.number").value("SU-275"))
                .andExpect(jsonPath("$.departure").value("04.07.2021 20:10"))
                .andExpect(jsonPath("$.arrival").value("05.07.2021 11:15"));
    }

    @Test
    void getFlightById_WhenGivenNotExistingId_ReturnsNotFound() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders.get("/flights/5");
        mvc.perform(request).andExpect(status().isNotFound());
    }

    @Test
    void getFlightById_WhenFileDoesntExist_ReturnsInternalError() throws Exception {
        props.setFlightsSource("no");
        RequestBuilder request = MockMvcRequestBuilders.get("/flights/5");
        mvc.perform(request).andExpect(status().isNotFound());
    }
}