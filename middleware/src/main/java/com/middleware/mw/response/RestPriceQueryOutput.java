package com.middleware.mw.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "价格查询出参")
public class RestPriceQueryOutput implements Serializable {

    private static final long serialVersionUID = 2178263709056473707L;

    @ApiModelProperty(value = "价格id")
    private String id;

    @ApiModelProperty(value = "价格")
    private BigDecimal price;

}
