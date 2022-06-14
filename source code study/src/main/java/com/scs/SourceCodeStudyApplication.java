package com.scs;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class SourceCodeStudyApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder(SourceCodeStudyApplication.class)
                .web(WebApplicationType.SERVLET)
                .run(args);
    }

}
