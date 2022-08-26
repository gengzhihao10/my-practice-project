package com.common.utils;

import com.common.consts.enums.ErrorEnum;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;

@Slf4j
public class JsonUtil {

    private static ObjectMapper objectMapper = new ObjectMapper();


    /**
     * @author gengzhihao
     * @date 2022/7/25 16:24
     * @description 将Object转换为json
     * @param null
     * @return
     **/
    public static String objectToJson(Object object){
        try {
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            log.error("JsonUtil异常: ",e);
            throw new RuntimeException(ErrorEnum.OBJECT_TO_JSON_FAIL.getMessage());
        }
    }


    /**
     * @author gengzhihao
     * @date 2022/7/25 16:27
     * @description 将json转换为Object
     * @param null
     * @return
     **/
    public static Object jsonToObjcet(String json, Class type){
        try {
            return objectMapper.readValue(json,type);
        } catch (IOException e) {
            log.error("JsonUtil异常 :",e);
            throw new RuntimeException(ErrorEnum.JSON_TO_OBJECT_FAIL.getMessage());
        }
    }

}
