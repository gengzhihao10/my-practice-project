package com.c.mashibing.suanfa.tixike;

/*
 题目1，
 定义一种数：可以表示成若干（数量>1）连续正数和的数
 比如:
 5 = 2+3，5就是这样的数
 12 = 3+4+5，12就是这样的数
 1不是这样的数，因为要求数量大于1个、连续正数和
 2 = 1 + 1，2也不是，因为等号右边不是连续正数
 给定一个参数N，返回是不是可以表示成若干连续正数和的数


 */
public class Code39_3给定一个数返回是不是连续正数和 {


    /*
     * @author gengzhihao
     * @date 2024/1/26 11:20
     * @description 暴力解
     * @param num
     * @return boolean
     **/
    public static boolean isMSum0(int num){
        for (int i = 1; i <= num/2; i++){
            int sum = i;
            for (int j = i + 1; sum < num; j++){
                sum += j;
                if (sum == num){
                    return true;
                }
            }
        }
        return false;
    }

    /*
     * @author gengzhihao
     * @date 2024/1/25 10:06
     * @description  题目1
     * @param num
     * @return boolean
     **/
    //规律为2的n次方返回false，否则返回true
    public static boolean isMSum1(int num){
        //-num等价于~num-1
        //num & (~num-1)，即将num最右侧的1提取出来
        //仅在num为2的某次幂时，num等于(num & -num)，根据对数器得到的规律，此时应该返回false。否则返回true
        return num != (num & -num);
    }


    public static boolean isMSum2(int num) {
//
//		return num == (num & (~num + 1));
//
//		return num == (num & (-num));
//
//
        return (num & (num - 1)) != 0;
    }

    public static void main(String[] args) {
//        for (int num = 1; num < 200; num++) {
//            System.out.println(num + " : " + isMSum0(num));
//        }
        for (int num = 1; num < 100; num++) {
            if (isMSum1(num) != isMSum2(num)) {
                System.out.println("Oops!");
                System.out.println("isMSum2: " + num + " : " + isMSum2(num));
                System.out.println("isMSum1: " + num + " : " + isMSum1(num));
                break;
            }
        }

    }
}
