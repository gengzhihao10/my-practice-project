package com.mw.aop;

import com.mw.service.PriceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
public class Test {

    @Autowired
    private PriceService priceService;

    @PostMapping("/test")
    public void test(int testInput){
        log.info("测试反射执行结果 {}",priceService
                .getClass()
                .getSimpleName());
    }

}
