package com.urlshortkaro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class UrlShortKaroApplication {

    public static void main(String[] args) {
        SpringApplication.run(UrlShortKaroApplication.class, args);
    }
}