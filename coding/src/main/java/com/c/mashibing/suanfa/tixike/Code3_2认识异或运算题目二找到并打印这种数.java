package com.c.mashibing.suanfa.tixike;


/**
 * @author gengzhihao
 * @date 2023/5/11 10:32
 * @description
 * 题目1，一个数组中有一种数出现了奇数次，其他数都出现了偶数次，怎么找到并打印这种数
**/

public class Code3_2认识异或运算题目二找到并打印这种数 {


    /*
     * @author gengzhihao
     * @date 2023/5/11 10:19
     * @description 题目1
     * @param arr
     **/
    public static void printValue(int[] arr){
        int result = 0;
        if (arr == null || arr.length == 0){
            System.out.println("数组为空");
        }
        for (int i = 0; i < arr.length; i++){
            result ^= arr[i];
        }
        System.out.println(result);
    }

    public static void main(String[] args) {
        int[] arr = {1,1,1,2,2,3,3,3,3,4,4};
        printValue(arr);
    }
}
