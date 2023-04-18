package com.c.mashibing.suanfa.xinshouke;

import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;

public class Code5_4使用位运算实现加法 {

    /*
     * @author gengzhihao
     * @date 2023/4/14 10:57
     * @description 使用位运算实现加法，属于比较底层的实现
     * @param a
     * @param b
     * @return int
     **/
    public static  int add(int a, int b){
        int sum = 0;
        //进位信息不为零就继续。直到进位信息为零，得到最终结果
        while (b != 0){
            //异或为无进位相加，获得无进位相加的结果
            sum = a ^ b;
            //获得进位信息
            b = (a & b) << 1;
            a = sum;
        }
        return sum;
    }

    /*
     * @author gengzhihao
     * @date 2023/4/14 10:57
     * @description 得到相反数，在二进制上是取反+1
     * @param n
     * @return int
     **/
    public static int negNum(int n){
        return add(~n,1);
    }

    /*
     * @author gengzhihao
     * @date 2023/4/14 10:56
     * @description a减去b
     * @param a
     * @param b
     * @return int
     **/
    public static int minus(int a , int b){
        return add(a,negNum(b));
    }


    /*
     * @author gengzhihao
     * @date 2023/4/14 11:09
     * @description 使用位运算实现乘法，支持负数的乘法
     * @param a 整数
     * @param b 整数
     * @return int
     **/
    public static int multi(int a, int b){
        int res = 0;
        while (b != 0){
            //判断b二进制末位是否是1
            if ( (b & 1) != 0){
                //不为零则相加
                res = add(res,a);
            }
            //a左移一位
            a <<= 1;
            //b不带符号右移一位
            //>>为带符号右移，用符号位来补位。>>>为不带符号右移，用0来补位
            //如果b为负数，带符号右移，则while无法中止，因此要用不带符号右移
            b >>>= 1;
        }
        return res;
    }

    //判断是否为负数
    public static boolean isNeg(int n){
        return n < 0;
    }


    /*
     * @author gengzhihao
     * @date 2023/4/14 15:06
     * @description 除法。但是系统最小值无法转绝对值
     * @param a
     * @param b
     * @return int
     **/
    public static int div(int a, int b){
        //得到x,y的绝对值
        int x = isNeg(a) ? negNum(a) : a;
        int y = isNeg(b) ? negNum(b) : b;
        int res = 0;
        for (int i = 30; i >= 0; i = minus(i, 1)){
            //x右移和y左移是一样的效果，但是x(x为绝对值，保证是非负)带符号右移(符号为0)，可以防止x的1挨着符号位0，而y在左移时1出现在符号位发生正负翻转导致的错误
            if ((x >> i) >= y){
                //记录左移过多少位，每一次x右移i位后大于y时，i都会记录在res中
                res |= (1 << i);
                //x减去(y左移i位)
                x = minus(x, y << i);
            }
        }
        //a和b符号不一样(亦或^，等同于!=)，取负数返回；符号一样，直接返回
        return isNeg(a) ^ isNeg(b) ? negNum(res) :res;
    }


    /*
     * @author gengzhihao
     * @date 2023/4/14 11:39
     * @description 除法 a/b，包括整数最小的情况
     * @param a 被除数
     * @param b 除数
     * @return int
     **/
    public static int divide(int a, int b){
        //被除数和除数都是系统最小
        if (a == Integer.MIN_VALUE && b == Integer.MIN_VALUE){
            return 1;
        }
        //仅有除数为系统最小
        else if (b == Integer.MIN_VALUE){
            return 0;
        }
        //仅有被除数为系统最小
        else if (a == Integer.MIN_VALUE){
            //除数为-1，返回整数最大值（约定）
            if (b == negNum(1)){
                return Integer.MAX_VALUE;
            }
            else {
                //c = (a + 1)/b
                int c = div(add(a,1),b);
                //c + (a - (c * b))/b
                return add(c,div(minus(a,multi(c, b)),b));
            }
        }
        //a和b都和最小值没关系，直接除
        else {
            return div(a, b);
        }
    }

    public static void main(String[] args) {
        int a = Integer.MIN_VALUE;

        System.out.println(-a);
    }

}
