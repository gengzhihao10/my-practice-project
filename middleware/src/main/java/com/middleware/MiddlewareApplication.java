package com.middleware;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class MiddlewareApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder(MiddlewareApplication.class)
                .web(WebApplicationType.SERVLET)
                .run(args);
    }
}
