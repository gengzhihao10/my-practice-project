package com.c.mashibing.suanfa.tixike;


/**
 * @author gengzhihao
 * @date 2023/5/11 10:40
 * @description
 * 题目1，一个数组中有两种数出现了奇数次，其他数都出现了偶数次，怎么找到并打印这两种数
**/

public class Code3_4怎么找到并打印这两种数 {

    /*
     * @author gengzhihao
     * @date 2023/5/12 10:21
     * @description 题目1
     * @param arr
     **/
    public static void process1(int[] arr){
        int num1 = 0;
        //得到num=a^b
        for (int i = 0; i < arr.length; i++){
            num1 ^= arr[i];
        }
        //拿到a或b中的某一个数的最右一个1
        int rightnum1 = num1 & (-num1);
        int a = 0;
        for (int i = 0; i < arr.length; i++){
            if ((arr[i] & (-arr[i])) == rightnum1){
                a ^= arr[i];
            }
        }
        int b = num1 ^ a;
        System.out.println("a:"+a+" ,b:"+b);
    }

    public static void main(String[] args) {
        int[] arr = {2,2,2,5,5,5,5,5,1,1,7,7,7,7,9,9};
        process1(arr);
    }
}
