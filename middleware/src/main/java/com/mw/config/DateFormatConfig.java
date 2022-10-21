package com.mw.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;


/**
 * @author gengzhihao
 * @date 2022/9/6 15:23
 * @description 日期打印格式配置
**/

@Configuration
public class DateFormatConfig {

    @Bean("simpleDateFormat")
    public SimpleDateFormat getSimpleDateFormat(){
        DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.LONG, Locale.CHINA);
//        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    }

    @Bean("dateFormat")
    public DateFormat getDateFormat(){
        return DateFormat.getDateInstance(DateFormat.LONG, Locale.CHINA);
    }

    @Bean("timeFormat")
    public DateFormat getTimeFormat(){
        return DateFormat.getTimeInstance(DateFormat.LONG, Locale.CHINA);
    }

    @Bean
    public DateTimeFormatter getDateTimeFormatter(){
        return DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    }
}
