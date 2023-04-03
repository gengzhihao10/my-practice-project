package com.c.mashibing.suanfa.xinshouke;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Code1_1打印二进制 {


    public static void main(String[] args) {
        Integer num = Integer.MIN_VALUE;
        printIntByBinary(num.intValue());
        printIntByBinary(0);
    }


    /**
     * @author gengzhihao
     * @date 2023/1/14 16:59
     * @description 计算机中存储的二进制数为补码。
     * 正数的补码和原码是一样的。
     * 负数的补码和原码之间的换算都是取反＋1。
     * 所以如果展现计算机中的二进制（补码），下面的方法即可。
     * 如果展现原码或者说真值，那么需要进行正负的判断，并对负数进行原码的计算
     * 注意，求补码的时候，符号位不变，数值位取反＋1.如果有溢出的位，不管它
    **/
    private static void printIntByBinary(int num) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 31; i >= 0 ; i--){
            stringBuilder.append((num & 1<<i) == 0 ? "0" : "1");
        }
        log.info(stringBuilder.toString());
    }
}
