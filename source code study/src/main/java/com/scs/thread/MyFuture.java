package com.scs.thread;


import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

/**
 * @author gengzhihao
 * @date 2022/7/16 16:54
 * @description 实现线程的第三种方式,实现Callable
**/
@Slf4j
public class MyFuture {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        log.info("线程运行前");
        FutureTask futureTask = new FutureTask(new MyCallable());
        new Thread(futureTask).start();
        Object result = futureTask.get();
        log.info("线程运行后,返回结果 {}", result);
    }
}
