package com.bagandov.aviasales;

import com.bagandov.aviasales.properties.SourcesProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(SourcesProperties.class)
public class AviasalesApplication {

	public static void main(String[] args) {
		SpringApplication.run(AviasalesApplication.class, args);
	}

}
