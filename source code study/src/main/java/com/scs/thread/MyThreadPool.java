package com.scs.thread;


import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

/**
 * @author gengzhihao
 * @date 2022/7/16 17:17
 * @description 实现线程的第四种方式
**/
@Slf4j
public class MyThreadPool {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        log.info("线程启动前");
        //阿里编程规约中强制要求不允许使用Execurots来创建线程池。
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        Future<Object> future = executorService.submit(new MyCallable());
        log.info("线程启动后 {}",future.get());
        executorService.shutdown();
    }
}
