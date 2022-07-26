package com.scs.service.impl;

import com.scs.input.PersonInput;
import com.scs.output.PersonOutput;
import com.scs.service.PersonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController("/person")
public class PersonServiceImpl implements PersonService {


    @PostMapping("/eat")
    @Override
    public PersonOutput eat(@RequestBody PersonInput personInput) {
        log.info("开始 吃东西");
        PersonOutput personOutput = new PersonOutput();
        personOutput.setDesc(personInput.getFood() + "很好吃");
        log.info("结束 吃东西");

        return personOutput;
    }


}
