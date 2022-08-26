package com.scs.proxy.jdk;

import com.common.utils.JsonUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.scs.input.PersonInput;
import com.scs.service.PersonService;
import com.scs.service.impl.PersonServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;


/**
 * @author gengzhihao
 * @date 2022/7/25 11:10
 * @description 基于jdk的动态代理，需要实现InvocationHandler
**/
@Slf4j
public class JdkInvocationHandler implements InvocationHandler, HandlerInterceptor {

    private Object target;

    JdkInvocationHandler() {
        super();
    }

    public JdkInvocationHandler(Object target) {
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
            log.info(JsonUtil.objectToJson(result));
        }else {
            result = method.invoke(target,args);
        }
        return result;
    }

    //测试jdk动态代理
    public static void main(String[] args) throws JsonProcessingException {
        PersonService personService = new PersonServiceImpl();
        //new一个InvocationHandler
        JdkInvocationHandler handler = new JdkInvocationHandler(personService);
        //jdk动态代理需要通过Proxy生成代理类，明显能感觉出来还是蛮耗时的。
        // 如果每次调用都new一个，性能就太差了。
        Long start = System.currentTimeMillis();
        PersonService proxyService = (PersonService) Proxy.newProxyInstance(personService.getClass().getClassLoader(),personService.getClass().getInterfaces(),handler);
        Long end = System.currentTimeMillis();
        log.info("生成jdk代理对象耗时 {}毫秒", end- start);
        //调用目标方法
        PersonInput personInput = PersonInput.builder().food("叉烧").build();
        proxyService.eat(personInput);
    }

}
