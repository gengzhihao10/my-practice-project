package com.scs.thread;


import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionException;
import org.springframework.transaction.TransactionStatus;
import sun.misc.Unsafe;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

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
        executorService.wait(100);

        //线程池
//        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor();
        //原子整形，所有的操作都具备原子性
        AtomicInteger atomicInteger = new AtomicInteger();
        //
//        Unsafe unsafe = new Unsafe();
        //线程数据备份
        ThreadLocal threadLocal = new ThreadLocal();
        //spring事务管理器接口
        PlatformTransactionManager platformTransactionManager = new PlatformTransactionManager() {
            @Override
            public TransactionStatus getTransaction(TransactionDefinition transactionDefinition) throws TransactionException {
                return null;
            }

            @Override
            public void commit(TransactionStatus transactionStatus) throws TransactionException {

            }

            @Override
            public void rollback(TransactionStatus transactionStatus) throws TransactionException {

            }
        };
        //Lock锁
        Lock lock = new ReentrantLock();
        lock.tryLock();
    }

    public void test() throws InterruptedException {
        Object o = new Object();
        o.wait(10);
    }
}
