package com.scs.thread;


import lombok.extern.slf4j.Slf4j;

/**
 * @author gengzhihao
 * @date 2022/7/16 14:42
 * @description 实现线程的第二种方式
**/
@Slf4j
public class MyRunnable implements Runnable{

    @Override
    public void run() {
      log.info("TestRunnable run");
    }

    public static void main(String[] args) {
        log.info("线程启动前");
        MyRunnable testThread = new MyRunnable();
        new Thread(testThread).start();
        log.info("线程启动后");
    }
}
