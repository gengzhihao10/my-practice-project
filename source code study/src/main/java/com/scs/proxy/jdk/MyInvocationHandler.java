package com.scs.proxy.jdk;

import com.common.utils.JsonUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;


/**
 * @author gengzhihao
 * @date 2022/7/25 11:10
 * @description 基于jdk的动态代理，需要实现InvocationHandler
**/
@Slf4j
public class MyInvocationHandler implements InvocationHandler {

    private Object target;

    MyInvocationHandler() {
        super();
    }

    public MyInvocationHandler(Object target) {
        super();
        this.target = target;
    }

    /**
     * @author gengzhihao
     * @date 2022/7/25 11:10
     * @description
     * @param proxy 新生成的代理类，method 代理类要执行的方法，args 要执行的方法的入参
     * @return
     **/
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object result;
        String name = method.getName();
        if ("eat".equals(method.getName())){
            log.info("开始 jdk动态代理");
            result = method.invoke(target,args);
            log.info("结束 jdk动态代理");
            log.info(JsonUtils.objectToJson(result));
        }else {
            result = method.invoke(target,args);
        }
        return result;
    }


}
