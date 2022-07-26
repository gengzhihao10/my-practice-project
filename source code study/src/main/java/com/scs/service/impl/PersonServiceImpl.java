package com.scs.service.impl;

import com.common.utils.JsonUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.scs.input.PersonInput;
import com.scs.output.PersonOutput;
import com.scs.proxy.jdk.MyInvocationHandler;
import com.scs.service.PersonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.lang.reflect.Proxy;

@Slf4j
@RestController
@RequestMapping("/person")
public class PersonServiceImpl implements PersonService {


    @PostMapping("/eat")
    @Override
    public PersonOutput eat(@RequestBody PersonInput personInput) throws JsonProcessingException {
        log.info("开始 吃东西");
        PersonOutput personOutput = new PersonOutput();
        personOutput.setDesc(personInput.getFood() + "很好吃");
        log.info("结束 吃东西，{}", JsonUtils.objectToJson(personOutput));

        return personOutput;
    }

    public static void main(String[] args) throws JsonProcessingException {
        PersonService personService = new PersonServiceImpl();
        MyInvocationHandler handler = new MyInvocationHandler(personService);
        PersonService proxyService = (PersonService) Proxy.newProxyInstance(personService.getClass().getClassLoader(),personService.getClass().getInterfaces(),handler);

        PersonInput personInput = PersonInput.builder().food("叉烧").build();

        proxyService.eat(personInput);
    }


}
