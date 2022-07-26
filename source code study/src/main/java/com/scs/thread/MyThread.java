package com.scs.thread;


import lombok.extern.slf4j.Slf4j;

/**
 * @author gengzhihao
 * @date 2022/7/16 10:51
 * @description 实现线程的第一种方式
**/
@Slf4j
public class MyThread extends Thread{

    @Override
    public void run() {
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("TestThread run");
    }

    public static void main(String[] args) throws InterruptedException {
        log.info("线程启动前");
        MyThread myThread = new MyThread();
        myThread.start();
        Thread.sleep(1000);
        log.info("线程启动后");
    }
}
