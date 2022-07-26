package com.scs.thread;

public class MyThreadLocal extends ThreadLocal {

    @Override
    protected Integer initialValue() {
        return 0;
    }


}
