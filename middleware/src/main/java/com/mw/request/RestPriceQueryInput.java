package com.mw.request;

import com.mw.exception.CheckInpustException;
import com.mw.param.BaseInput;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "价格查询入参")
@Slf4j
public class RestPriceQueryInput extends BaseInput implements Serializable {

    private static final long serialVersionUID = -2236442408375042884L;

    @ApiModelProperty(value = "价格id",required = true)
    private String id;

    @Override
    public void check() {
        log.info("测试入参校验");
        //这些校验方法可以进一步提取成Util
        if (StringUtils.isEmpty(id)){
            throw new CheckInpustException("入参id不能为空");
        }
    }
}
