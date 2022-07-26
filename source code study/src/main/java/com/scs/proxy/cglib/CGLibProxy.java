package com.scs.proxy.cglib;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.scs.input.PersonInput;
import com.scs.service.PersonService;
import com.scs.service.impl.PersonServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;


/**
 * @author gengzhihao
 * @date 2022/7/26 21:53
 * @description CGLib动态代理实现类
**/

@Slf4j
public class CGLibProxy implements MethodInterceptor {

    //被代理对象
    private Object target;


    /**
     * @author gengzhihao
     * @date 2022/7/26 21:54
     * @description 返回代理对象
     * @param null
     * @return
     **/
    public Object getInstance(Object target){
        this.target = target;
        //增强类
        Enhancer enhancer = new Enhancer();
        //设置生成的代理类的父类Class（也就是被代理对象的Class类型）
        enhancer.setSuperclass(this.target.getClass());
        //设置回调为这个实现的代理
        enhancer.setCallback(this);


        Long start = System.currentTimeMillis();
        //创建代理对象
        Object proxy = enhancer.create();
        Long end = System.currentTimeMillis();
        log.info("cglib创建代理对象耗时 {}毫秒", end - start);

        return proxy;
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        log.info("开始 cglib代理");
        Object result = methodProxy.invokeSuper(o,objects);
        log.info("结束 cglib代理");
        return result;
    }

    public static void main(String[] args) throws JsonProcessingException {
        CGLibProxy cgLibProxy = new CGLibProxy();
        PersonService personService = (PersonService)cgLibProxy.getInstance(new PersonServiceImpl());

        PersonInput personInput = PersonInput.builder().food("烧烤").build();
        personService.eat(personInput);
    }
}
