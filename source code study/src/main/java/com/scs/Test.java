package com.scs;

import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class Test {

    public static void main(String[] args) {
        ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap();
        HashMap hashMap = new HashMap();
//        Set<String> set = EnumSet.of("1,2,3");
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = RuntimeException.class, isolation = Isolation.DEFAULT)
    public void testTransactional(){

    }
}
