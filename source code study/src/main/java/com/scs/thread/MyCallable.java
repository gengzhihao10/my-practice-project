package com.scs.thread;


import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Callable;

/**
 * @author gengzhihao
 * @date 2022/7/16 16:59
 * @description 继承Callable，实现线程的第三种方式
**/
@Slf4j
public class MyCallable implements Callable {
    @Override
    public Object call() throws Exception {
        return "MyCallable call";
    }
}
