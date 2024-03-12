package com.c.mashibing.suanfa.tixike;

/*
todo
 题目1，
 leetcode测试链接：https://leetcode.com/problems/super-egg-drop

 */
public class Code43_4返回如果想找到棋子不会摔碎的最高层数 {


    /*
     * @author gengzhihao
     * @date 2024/3/12 11:56
     * @description 动态规划
     * @param kChess
     * @param nLevel
     * @return int
     **/
    public static int superEggDrop2(int kChess, int nLevel){
        return 0;
    }


    /*
     * @author gengzhihao
     * @date 2024/3/12 11:56
     * @description 基于四边形不等式优化的动态规划
     * @param kChess
     * @param nLevel
     * @return int
     **/
    public static int superEggDrop3(int kChess, int nLevel){
        return 0;
    }


    /*
     * @author gengzhihao
     * @date 2024/3/12 11:59
     * @description 换了试法的动态规划，使用空间压缩进行优化
     * @param kChess
     * @param nLevel
     * @return int
     **/
    public static int superEggDrop4(int kChess, int nLevel){
        return 0;
    }


    public static void main(String[] args) {
        int maxN = 500;
        int maxK = 30;
        int testTime = 1000;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int N = (int) (Math.random() * maxN) + 1;
            int K = (int) (Math.random() * maxK) + 1;
            int ans2 = superEggDrop2(K, N);
            int ans3 = superEggDrop3(K, N);
            int ans4 = superEggDrop4(K, N);
            if (ans2 != ans3 || ans2 != ans4) {
                System.out.println("出错了!");
            }
        }
        System.out.println("测试结束");
    }
}
