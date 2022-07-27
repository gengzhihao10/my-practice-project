package com.scs.service.impl;

import com.common.utils.JsonUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.scs.input.PersonInput;
import com.scs.output.PersonOutput;
import com.scs.service.PersonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
        log.info("结束 吃东西，{}", JsonUtil.objectToJson(personOutput));

        return personOutput;
    }

}
