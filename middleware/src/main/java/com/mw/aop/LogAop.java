package com.mw.aop;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;


/**
 * @author gengzhihao
 * @date 2022/9/2 17:47
 * @description 打印日志切面
**/

@Aspect
@Slf4j
@Component
@Order(5)
public class LogAop {

//    @Autowired
//    private SimpleDateFormat simpleDateFormat;
//
//    @Autowired
//    private DateFormat dateFormat;
//
//    @Autowired
//    private DateFormat timeFormat;

    @Autowired
    private DateTimeFormatter dateTimeFormatter;

    //指定类下的所有方法
    @Pointcut("execution(* com.mw.web.PriceController.*(..))")
    public void logPointCut1(){

    }
    //指定包下的所有类的所有方法
    @Pointcut("execution(* com.mw.web.*.*(..))")
    public void logPointCut2(){

    }
    //任意public的方法
    @Pointcut("execution(public * *(..))")
    public void logPointCut3(){

    }

    @Pointcut("execution(* com.mw.web.*.*(..))")
    public void logPointCut4(){

    }


    /**
     * @author gengzhihao
     * @date 2022/9/6 14:27
     * @description 给web包下的controller打印出入参
     * @param
     * @return
     **/
    @Around(value = "logPointCut1()")
    public Object printLog(ProceedingJoinPoint joinPoint) throws Throwable {

        log.info(buildInputLog(joinPoint));
        Object object = joinPoint.proceed();
        log.info(buildOutputLog(joinPoint,object));
        return object;
    }

    public static void main(String[] args) {
        log.info(String.valueOf(System.currentTimeMillis()));
    }


    /**
     * @author gengzhihao
     * @date 2022/9/7 14:02
     * @description 构建出参日志
     * @param null
     * @return
     **/
    private String buildOutputLog(ProceedingJoinPoint joinPoint,Object object) {
        StringBuffer outputLog = new StringBuffer();
        outputLog.append(dateTimeFormatter.format(LocalDateTime.now()));
        outputLog.append("   ");
        outputLog.append(joinPoint.getTarget().getClass().getName());
        outputLog.append("#");
        outputLog.append((joinPoint.getSignature().getName()));
        outputLog.append("()");
        outputLog.append("   出参为   ");
        if (ObjectUtil.isNotNull(object)){
            outputLog.append(JSONUtil.toJsonStr(object));
        }
        return outputLog.toString();
    }


    /**
     * @author gengzhihao
     * @date 2022/9/7 14:03
     * @description 构建入参日志
     * @param null
     * @return
     **/
    private String buildInputLog(ProceedingJoinPoint joinPoint) {
        StringBuffer inputLog = new StringBuffer();
        inputLog.append(dateTimeFormatter.format(LocalDateTime.now()));
        inputLog.append("   ");
        inputLog.append(joinPoint.getTarget().getClass().getName());
        inputLog.append("#");
        inputLog.append((joinPoint.getSignature().getName()));
        inputLog.append("()");
        inputLog.append("   入参为   ");
        inputLog.append(JSONUtil.toJsonStr(joinPoint.getArgs()));
        return inputLog.toString();
    }


}
