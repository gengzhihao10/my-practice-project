package com.mw.web;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.json.JSONUtil;
import com.mw.anotation.CheckInput;
import com.mw.request.RestPriceQueryInput;
import com.mw.response.RestPriceQueryOutput;
import com.mw.service.PriceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Method;
import java.math.BigDecimal;

@Slf4j
@RestController
@RequestMapping("/price")
public class PriceController {

    @Autowired
    private PriceService priceService;

    /**
     * @param
     * @return
     * @author gengzhihao
     * @date 2022/9/2 17:23
     * @description 根据id查询单件商品价格（用户测试aop）
     **/
    @CheckInput
    @PostMapping("/query")
    public RestPriceQueryOutput queryPrice(@RequestBody RestPriceQueryInput restPriceQueryInput) throws NoSuchMethodException {
//        log.info("测试反射执行结果 {}", priceService.);
//        log.info();
//        for (Method method : priceService.getClass().getMethods()) {
//            log.info("方法名 {}",method.getName());
//        }
        return priceService.queryPrice(restPriceQueryInput);
    }

    public static void main(String[] args) {
        RestPriceQueryInput input = new RestPriceQueryInput();
        input.setId("24");
        log.info(BeanUtil.getFieldName(input.getId()));
    }
}
