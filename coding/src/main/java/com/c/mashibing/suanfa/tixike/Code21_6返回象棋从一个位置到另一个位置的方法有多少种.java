package com.c.mashibing.suanfa.tixike;

/*
 题目1，
 请同学们自行搜索或者想象一个象棋的棋盘，
 然后把整个棋盘放入第一象限，棋盘的最左下角是(0,0)位置
 那么整个棋盘就是横坐标上9条线、纵坐标上10条线的区域
 给你三个 参数 x，y，k
 返回“马”从(0,0)位置出发，必须走k步
 最后落在(x,y)上的方法数有多少种?
 暴力递归
 题目2，基于严格位置依赖的动态规划
 */
public class Code21_6返回象棋从一个位置到另一个位置的方法有多少种 {


    /*
     * @author gengzhihao
     * @date 2023/9/7 11:14
     * @description 题目1
     * @param a
     * @param b
     * @param k
     * @return int
     **/
    public static int jump1(int a, int b, int k){
        if (a < 0 || a > 8 || b < 0 || b > 9 || k < 0){
            return -1;
        }
        return process1(0,0,k,a,b);
    }

    //(x,y)当前位置
    //rest，剩余可跳的次数
    //(a,b)目标位置
    private static int process1(int x, int y, int rest, int a, int b) {
        //越界
        if (x < 0 || x > 8 || y < 0 || y > 9){
            return 0;
        }

        //base case
        if (rest == 0){
            return  (x == a && y == b) ? 1 : 0;
        }

        int pos = process1(x+2,y+1,rest-1,a,b);
        pos += process1(x+2,y-1,rest-1,a,b);
        pos += process1(x+1,y+2,rest-1,a,b);
        pos += process1(x+1,y-2,rest-1,a,b);
        pos += process1(x-1,y+2,rest-1,a,b);
        pos += process1(x-1,y-2,rest-1,a,b);
        pos += process1(x-2,y+1,rest-1,a,b);
        pos += process1(x-2,y-1,rest-1,a,b);
        return pos;
    }

    //*********************************************************************************************************

    /*
     * @author gengzhihao
     * @date 2023/9/8 8:46
     * @description 题目2
     * @param a
     * @param b
     * @param k
     * @return int
     **/
    public static int jump2(int a, int b, int k) {
        if (a < 0 || a > 8 || b < 0 || b > 9 || k < 0) {
            return -1;
        }

        int[][][] ans = new int[9][10][k+1];
        ans[a][b][0] = 1;

        for (int rest = 1; rest < k+1; rest++){
            for (int x = 0; x < 9; x++){
                for (int y = 0; y < 10; y++){
                    int pos = getValue(x+2,y+1,rest-1,ans);
                    pos += getValue(x+2,y-1,rest-1,ans);
                    pos += getValue(x+1,y+2,rest-1,ans);
                    pos += getValue(x+1,y-2,rest-1,ans);
                    pos += getValue(x-1,y+2,rest-1,ans);
                    pos += getValue(x-1,y-2,rest-1,ans);
                    pos += getValue(x-2,y+1,rest-1,ans);
                    pos += getValue(x-2,y-1,rest-1,ans);
                    ans[x][y][rest] = pos;
                }
            }
        }

        return ans[0][0][k];
    }

    private static int getValue(int x, int y, int rest,int[][][] ans) {
        if (x < 0 || x > 8 || y < 0 || y > 9){
            return 0;
        }
        return ans[x][y][rest];
    }


    //****************************************************************************************************

    public static void main(String[] args) {
        int x = 7;
        int y = 7;
        int step = 10;
        System.out.println(jump1(x, y, step));
        System.out.println(jump2(x, y, step));

    }
}
