package com.c.mashibing.suanfa.xinshouke;

import com.common.utils.JsonUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;


/**
 * @author gengzhihao
 * @date 2023/2/11 19:55
 * @description 提供一个等概率返回从整数1-5的函数f1，只使用这个函数f，写出一个等概率返回整数1-7的函数
**/
@Slf4j
public class Code2_4从一到五随机到一到七随机 {


    public static int f1(){
        return (int)(Math.random()*5)+1;
    }



    /**
     * @author gengzhihao
     * @date 2023/2/11 20:38
     * @description 0和1等概率返回
     * @param null
     * @return
     **/
    public static int f2(){
        int result = 0;
        do {
            result = f1();
        }while (result == 3);
        return result < 3 ? 0 :1;
    }


    /**
     * @author gengzhihao
     * @date 2023/2/11 20:38
     * @description 等概率返回整数0-7
     * @param null
     * @return
     **/
    public static int f3(){
        return (f2()<<2) + (f2() << 1) + (f2() <<0);
    }


    /**
     * @author gengzhihao
     * @date 2023/2/11 20:40
     * @description 等概率返回1-7整数
     * @param null
     * @return
     **/
    public static int f4(){
        int result = 0;
        do {
            result = f3();
        }while (result == 7);
        return result + 1;
    }



    public static void main(String[] args) {
        int times = 100000;
        int result = 0;
        Map<Integer,Integer> map = new HashMap<>();
        for (int i =0; i< times; i++){
            result = f4();
            if (map.containsKey(result)){
                Integer count = map.get(result);
                count = count+ 1;
                map.put(result,count);
            }
            else {
                map.put(result,1);
            }
        }
        log.info("结果为" + JsonUtil.objectToJson(map));
    }

}
