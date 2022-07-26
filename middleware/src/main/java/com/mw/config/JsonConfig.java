package com.mw.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JsonConfig {

    //ObjectMapper已经存在于spring的容器中，注掉
    @Bean
    public ObjectMapper objectMapperConfig(){
        return new ObjectMapper();
    }
}
