package com.c.mashibing.suanfa.tixike;

/*
todo
 题目1，
 N皇后问题是指在N*N的棋盘上要摆N个皇后，
 要求任何两个皇后不同行、不同列， 也不在同一条斜线上
 给定一个整数n，返回n皇后的摆法有多少种。n=1，返回1
 n=2或3，2皇后和3皇后问题无论怎么摆都不行，返回0
 n=8，返回92
 N皇后问题无法改成动态规划
 题目2，同题目1，使用位运算优化常数时间
 */
public class Code24_15N皇后问题1 {


    /*
     * @author gengzhihao
     * @date 2023/9/23 9:29
     * @description 暴力递归
     * @param n
     * @return int
     **/
    public static int num1(int n){
        if (n <= 0){
            return 0;
        }
        int[] pos = new int[n];
        return process1(n,pos,0);
    }

    /*
     * @author gengzhihao
     * @date 2023/9/25 8:38
     * @description 对于给定参数，N皇后问题有几种结果
     * @param n 棋盘是n×n的
     * @param pos pos长度为n，因为每一行最多有一个皇后，所以按行来记录皇后的位置。
     * 如n为3，那么[2,0,1]表示 皇后们的位置为第0行第2列，第1行第0列，第2行第1列。
     * @param i 表示应该选择皇后的当前行是哪一行
     * @return int N皇后问题的结果
     **/
    private static int process1(int n, int[] pos, int i) {
        if (i == pos.length){
            return 1;
        }

        int sum = 0;
        for (int j = 0; j < n; j++){
            pos[i] = j;
            if (isLegal(pos,i,j)){
                sum += process1(n,pos,i+1);
            }
        }
        return sum;
    }

    //判断i行j列的皇后位置是否合法
    //只处理当前i行和之前行的组合是否合法。对之前行互相之间的组合是否合法不处理，默认是已经判断过的合法位置。
    //对于位置是否数组越界也不判断，由上级函数约束
    private static boolean isLegal(int[] pos, int i, int j) {
        for (int rol = 0; rol < i; rol++){
            int col = pos[rol];
            //行必然不同，不用判断
            if (col == j || Math.abs(rol-i) == Math.abs(col-j)){
                return false;
            }
        }
        return true;
    }


    /*
     * @author gengzhihao
     * @date 2023/9/25 9:01
     * @description 题目2
     * @param n
     * @return int
     **/
    public static int num2(int n){
        if (n <= 0){
            return 0;
        }

        int limit = (n == 32) ? -1 : ((1 << n)-1);

        return process2(limit,0,0,0);
    }

    /*
     * @author gengzhihao
     * @date 2023/9/25 9:32
     * @description
     * @param limit 这个Pos和题目1的pos数组不一样
     * 这里的pos表示的是之前所有行，其禁止的范围(0表示可以选择，1表示禁止选择)，累加后，得到pos。从最低位到最高位依次表示从0到n-1列
     * 如，0001。如最高位为符号位，最低位为1，
     * @param down 下方的限制
     * @param leftAndDown 左下的限制
     * @param rightAndDown 右下的限制
     * @return int
     **/
    private static int process2(int limit, int down, int leftAndDown,int rightAndDown) {
        if (down == limit){
            return 1;
        }

        int pos = limit & ~(down | leftAndDown | rightAndDown);
        int mostRightOne = 0;
        int res = 0;

        while (pos != 0){
            mostRightOne = (~pos + 1) & pos;
            pos = pos - mostRightOne;
            res += process2(limit,
                    down | mostRightOne,
                    (leftAndDown | mostRightOne) << 1,
                    (rightAndDown | mostRightOne) >>> 1);
        }

        return res;
    }


    //**********************************************************************************************

    public static int num(int n) {
        if (n < 1) {
            return 0;
        }
        int[] record = new int[n];
        return process(0, record, n);
    }

    // 当前来到i行，一共是0~N-1行
    // 在i行上放皇后，所有列都尝试
    // 必须要保证跟之前所有的皇后不打架
    // int[] record record[x] = y 之前的第x行的皇后，放在了y列上
    // 返回：不关心i以上发生了什么，i.... 后续有多少合法的方法数
    public static int process(int i, int[] record, int n) {
        if (i == n) {
            return 1;
        }
        int res = 0;
        // i行的皇后，放哪一列呢？j列，
        for (int j = 0; j < n; j++) {
            if (isValid(record, i, j)) {
                record[i] = j;
                res += process(i + 1, record, n);
            }
        }
        return res;
    }

    public static boolean isValid(int[] record, int i, int j) {
        // 0..i-1
        for (int k = 0; k < i; k++) {
            if (j == record[k] || Math.abs(record[k] - j) == Math.abs(i - k)) {
                return false;
            }
        }
        return true;
    }


    public static void main(String[] args) {
        int n = 7;

        long start = System.currentTimeMillis();
        System.out.println(num(n));
        long end = System.currentTimeMillis();
        System.out.println("cost time: " + (end - start) + "ms");

        start = System.currentTimeMillis();
        System.out.println(num2(n));
        end = System.currentTimeMillis();
        System.out.println("cost time: " + (end - start) + "ms");

    }
}
