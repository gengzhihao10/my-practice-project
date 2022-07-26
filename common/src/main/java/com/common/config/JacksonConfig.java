package com.common.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import sun.misc.Contended;

@Configuration
public class JacksonConfig {

    @Bean
    public ObjectMapper jackSon(){
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper;
    }
}
