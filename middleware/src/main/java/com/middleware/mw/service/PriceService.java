package com.middleware.mw.service;

import com.middleware.mw.response.RestPriceQueryOutput;
import com.middleware.mw.request.RestPriceQueryInput;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

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
