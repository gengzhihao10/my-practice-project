package com.c;


import lombok.extern.slf4j.Slf4j;

import java.sql.Date;
import java.time.Clock;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;

/**
 * @author gengzhihao
 * @date 2022/7/13 9:21
 * @description 用于快速测试接口
**/
@Slf4j
public class CodingTest {

    public static void main(String[] args) {
        log.info("测试开始");

        log.info("返回值为 {}",test());

        log.info("测试结束");
    }

    private static String test() {
        try {
            log.info("方法执行中");
        } catch (Exception e) {
            log.info("方法异常捕获中");
            return "2";
        } finally {
            log.info("方法最终执行中");
            return "3";
        }


    }
}

class Person {
    String firstName;
    String lastName;

    Person() {
    }

    Person(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public static void main(String[] args) {
        System.out.println(Date.from(Clock.systemDefaultZone().instant()));
        ZoneId zoneId = ZoneId.of("GMT+8");
        System.out.println(LocalDateTime.now(zoneId));
    }
}


interface PersonFactory<P extends Person> {
    P create(String firstName, String lastName);
}