package com.scs;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.scs.thread.MyRunnable;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public class SourceCodeTest {

    @Autowired
    private ObjectMapper objectMapper;

    public static void main( String[] args) throws JsonProcessingException {
        //jackson核心类
        ObjectMapper objectMapper = new ObjectMapper();

//        log.info("线程启动前");
//        TestThread testThread = new TestThread();
//        testThread.start();
//        log.info("线程启动后");

        log.info("线程启动前");
        MyRunnable testThread = new MyRunnable();
        new Thread(testThread).start();
        log.info("线程启动后");


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
