package com.common.consts.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorEnum {

    //枚举值
    OBJECT_TO_JSON_FAIL("100000", "json转换string失败", 0),
    JSON_TO_OBJECT_FAIL("100001", "json转换object失败", 0);

    private final String code;
    private final String message;
    private final int args;
}
