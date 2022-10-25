package com.mw.aop;

import cn.hutool.core.collection.CollectionUtil;
import com.mw.param.BaseInput;
import com.mw.request.RestPriceQueryInput;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.cglib.core.CollectionUtils;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Aspect
@Slf4j
@Component
//如果有多个 可以定义来控制顺序 数字越小执行顺序靠前
@Order(1)
public class CheckInputAop {

//    @Pointcut("args(com.mw.param.BaseInput)")
//    public void checkControllerInput(){
//    }

    @Pointcut("within(com.mw.web.*)")
    public void checkControllerInput2() {
    }

    @Before(value = "checkControllerInput2()")
    public void  checkInput(JoinPoint joinPoint) throws Throwable {
        log.info("开始aop校验入参非空");
        Object[] objects = joinPoint.getArgs();
//        log.info("signature为 {}",joinPoint.getSignature());
//        log.info("signature.name为 {}",joinPoint.getSignature().getName());
//        log.info("signature.DeclaringType为 {}",joinPoint.getSignature().getDeclaringType());
//        log.info("signature.DeclaringTypeName为 {}",joinPoint.getSignature().getDeclaringTypeName());
//        log.info("signature.Modifiers为 {}",joinPoint.getSignature().getModifiers());
//        log.info("args为 {}",joinPoint.getArgs());
//        log.info("kind为 {}",joinPoint.getKind());
//        log.info("sourceLocation为 {}",joinPoint.getSourceLocation());
//        log.info("staticPart为 {}",joinPoint.getStaticPart());
        List<Object> objectList = Arrays.asList(objects);
        Object input = null;
        if (CollectionUtil.isNotEmpty(objectList)){
            input = objectList.get(0);
        }
        BaseInput baseInput = null;
        if (input instanceof BaseInput){
            baseInput = (BaseInput)input;
            baseInput.check();
        }
//        Class inputClass = input.getClass();
//        log.info("inputclass为 {}",inputClass);
//        if (inputClass.isInstance(input)){
////            BaseInput baseInput = inputClass.cast(input);
//        }
//        BaseInput input1 = new RestPriceQueryInput();
//        log.info("class.DeclaringClass {}",inputClass.getDeclaringClass());
//        log.info("class.DeclaredClasses {}",inputClass.getDeclaredClasses());
//        log.info("class.Method {}",joinPoint.getClass().getMethod());
//        log.info("class.Name {}",inputClass.getName());
//        log.info("class. {}",inputClass.get());#
//        log.info("class. {}",inputClass.ge());
//        log.info("class. {}",inputClass.ge());
//        log.info("class. {}",inputClass.ge());
//        log.info("class. {}",inputClass.ge());
//        log.info("class. {}",inputClass.ge());
//        log.info("class. {}",inputClass.ge());
//        log.info("class. {}",inputClass.ge());
//        log.info("class. {}",inputClass.ge());

    }
}
