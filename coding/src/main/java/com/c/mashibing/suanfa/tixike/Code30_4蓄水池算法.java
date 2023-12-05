package com.c.mashibing.suanfa.tixike;

/*
todo
 题目1，解决的问题：
 假设有一个源源吐出不同球的机器，
 只有装下10个球的袋子，每一个吐出的球，要么放入袋子，要么永远扔掉
 如何做到机器吐出每一个球之后，所有吐出的球都等概率被放进袋子里

 */
public class Code30_4蓄水池算法 {


    /*
     * @author gengzhihao
     * @date 2023/12/4 21:03
     * @description 题目1
     * @param ballNum
     * @param bag
     **/
    public static void qs1_process(int ballNum,int[] bag){
        int index = 0;
        for(int i = 1; i <= ballNum; i++){
            if (i <= 10){
                bag[index++] = i;
            }else {
                //每个数都有10/i的几率进入bag
                if (randNum(i) <= 10){
                    index = (int)(Math.random() * 10);
                    bag[index] = i;
                }
            }
        }
    }

    //产生范围为[1, i]的随机整数
    private static int randNum(int i) {
        return (int)(Math.random() * i) + 1;
    }
}
