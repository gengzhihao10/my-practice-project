package com.mw.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RestPriceQueryInput implements Serializable {

    private static final long serialVersionUID = -2236442408375042884L;

    private String id;
}
