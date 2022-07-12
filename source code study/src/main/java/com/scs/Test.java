package com.scs;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Test {

    public static void main(String[] args)  {
        StringBuilder builder = new StringBuilder("reverse");
        StringBuffer buffer = new StringBuffer("reverse");
        String revStr = "reverse";
        log.info(ReverseString.reverse3(revStr));
    }

    public static void test() throws InterruptedException {
        log.trace("trace level log");
        log.debug("debug level log");
        log.info("info level log");
        log.error("error level log");
        log.warn("fatal level log");
        // 设置休眠时间(单位ms)，控制日志打印速度
        Thread.sleep(3);
    }
}
