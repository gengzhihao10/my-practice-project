package com.common.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;

public class JsonUtils {

    private static ObjectMapper objectMapper = new ObjectMapper();


    /**
     * @author gengzhihao
     * @date 2022/7/25 16:24
     * @description 将Object转换为json
     * @param null
     * @return
     **/
    public static String objectToJson(Object object) throws JsonProcessingException {
        return objectMapper.writeValueAsString(object);
    }


    /**
     * @author gengzhihao
     * @date 2022/7/25 16:27
     * @description 将json转换为Object
     * @param null
     * @return
     **/
    public static Object jsonToObjcet(String json, Class type) throws IOException {
        return objectMapper.readValue(json,type);
    }

}
