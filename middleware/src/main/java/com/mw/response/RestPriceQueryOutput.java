package com.mw.response;

import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RestPriceQueryOutput implements Serializable {

    private static final long serialVersionUID = 2178263709056473707L;

    private String id;

    private BigDecimal price;

}
