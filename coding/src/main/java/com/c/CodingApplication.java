package com.c;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class CodingApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder(CodingApplication.class)
                .web(WebApplicationType.SERVLET)
                .run(args);
    }
}
