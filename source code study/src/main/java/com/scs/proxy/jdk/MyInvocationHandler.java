//package com.scs.proxy.jdk;
//
//import com.common.utils.JsonUtils;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.fasterxml.jackson.databind.util.JSONPObject;
//import lombok.extern.slf4j.Slf4j;
//
//import java.lang.reflect.InvocationHandler;
//import java.lang.reflect.Method;
//
//
///**
// * @author gengzhihao
// * @date 2022/7/25 11:10
// * @description 基于jdk的动态代理，需要实现InvocationHandler
//**/
//@Slf4j
//public class MyInvocationHandler implements InvocationHandler {
//
//
//    /**
//     * @author gengzhihao
//     * @date 2022/7/25 11:10
//     * @description
//     * @param null
//     * @return
//     **/
//    @Override
//    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
//        log.info("开始 jdk动态代理");
//        log.info(JsonUtils.objectToJson(proxy));
//        return null;
//    }
//
//
//}
