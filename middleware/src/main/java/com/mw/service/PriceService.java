package com.mw.service;

import com.mw.request.RestPriceQueryInput;
import com.mw.response.RestPriceQueryOutput;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.math.BigDecimal;

@Service
@Slf4j
public class PriceService {

    public RestPriceQueryOutput queryPrice(RestPriceQueryInput restPriceQueryInput) {
        log.info("执行service层 queryPrice方法");
        return RestPriceQueryOutput.builder()
                .id(restPriceQueryInput.getId())
                .price(new BigDecimal(1.00))
                .build();
    }
}
