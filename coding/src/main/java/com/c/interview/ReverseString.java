package com.c.interview;


/**
 * @author gengzhihao
 * @date 2022/7/12 16:00
 * @description 反转字符串
**/

public class ReverseString {

    /**
     * @author gengzhihao
     * @date 2022/7/12 15:35
     * @description 通过String.toCharArray()返回的char数组反转字符串
     * @param
     * @return
     **/
    public static String reverse1(String str){
        StringBuilder builder = new StringBuilder();
        char[] chars = str.toCharArray();
        for (int i = chars.length - 1; i>= 0; i--){
            builder.append(chars[i]);
        }
        return builder.toString();
    }


    /**
     * @author gengzhihao
     * @date 2022/7/12 16:07
     * @description 通过StringBuilder的reverse方法反转字符串
     * @param null
     * @return
     **/
    public static String reverse2(String str){
        return new StringBuilder(str).reverse().toString();
    }


    /**
     * @author gengzhihao
     * @date 2022/7/12 16:09
     * @description 通过String.charAt()进行反转
     * @param null
     * @return
     **/
    public static String reverse3(String str){
        StringBuilder builder = new StringBuilder();
        for (int i = str.length() - 1; i>= 0; i--){
            builder.append(str.charAt(i));
        }
        return builder.toString();

    }

}
