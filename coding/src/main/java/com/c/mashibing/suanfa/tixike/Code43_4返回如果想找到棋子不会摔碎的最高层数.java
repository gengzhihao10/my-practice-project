package com.c.mashibing.suanfa.tixike;

/*
 题目1，
 leetcode测试链接：https://leetcode.com/problems/super-egg-drop
 中文版：https://leetcode.cn/problems/super-egg-drop/description/

 */
public class Code43_4返回如果想找到棋子不会摔碎的最高层数 {


    /*
     * @author gengzhihao
     * @date 2024/3/12 11:56
     * @description 动态规划，这道题类似于方案一旦定下就一次执行完，而不是试一次看行或不行再进行下一步
     * （因为在本题中也无法获知在某层楼摔下去是否会碎，只能将碎或不碎都考虑）。
     * 因此要从保守或者悲观的角度，制定出能够确保试探出f楼层数的最少试探次数。
     * 可变参数有，
     * a.还剩下几层楼要试
     * b.还剩下多少鸡蛋
     * c.试了多少次
     * 这个解法的试法为a b做变量，c做返回值
     * @param k 鸡蛋数，共有k枚鸡蛋
     * @param n 楼层数，共有从1~n层楼
     * @return int 最少要多少次能试出来鸡蛋会碎的确切层数（返回的是尝试次数，不是鸡蛋会碎的层数）。
     **/
    public static int superEggDrop2(int k, int n){
        if (k < 1 || n < 1){
            return 0;
        }

        int[][] dp = new int[k+1][n+1];
        //有i个鸡蛋，只有1层楼，所以只用试一次。
        for (int i = 1; i <= k; i++){
            dp[i][1] = 1;
        }
        //只有1个鸡蛋，有j层楼，所以要试j层楼
        for (int j = 1; j <= n; j++){
            dp[1][j] = j;
        }

        //i对应鸡蛋数，j对应楼层数
        for (int i = 2; i <= k; i++){
            for (int j = 2; j <= n; j++){
                int next = Integer.MAX_VALUE;
                for (int choice = 1; choice <= j; choice++){
                    next = Math.min(next, Math.max(dp[i - 1][choice - 1], dp[i][j - choice]));
                }
                //next为后续的选择需要的操作数，加上本次扔了一次的次数，是dp[i][j]的操作数
                dp[i][j] = next + 1;
            }
        }

        return dp[k][n];
    }


    /*
     * @author gengzhihao
     * @date 2024/3/12 11:56
     * @description 基于四边形不等式优化的动态规划
     * @param k
     * @param n
     * @return int
     **/
    public static int superEggDrop3(int k, int n){
        if (k < 1 || n < 1){
            return 0;
        }

        int[][] dp = new int[k+1][n+1];
        int[][] best = new int[k+1][n+1];
        //有i个鸡蛋，只有1层楼，所以只用试一次。
        for (int i = 1; i <= k; i++){
            dp[i][1] = 1;
            best[i][1] = 1;
        }
        //只有1个鸡蛋，有j层楼，所以要试j层楼
        for (int j = 1; j <= n; j++){
            dp[1][j] = j;
            best[1][j] = 1;
        }

        for (int j = 2; j <= n; j++){
            for (int i = k; i >= 2; i--){
                int ans = Integer.MAX_VALUE;
                int up = i == k ? j : best[i+1][j];
                int down = best[i][j-1];
                for (int choice = down; choice <= up; choice++){
                    int val = Math.max(dp[i - 1][choice - 1], dp[i][j - choice]);
                    if (val <= ans){
                        ans = val;
                        best[i][j] = choice;
                    }
                }
                dp[i][j] = ans + 1;
            }
        }

        return dp[k][n];

    }


    /*
     * @author gengzhihao
     * @date 2024/3/12 11:59
     * @description 换了试法的动态规划，使用空间压缩进行优化
     * 最优解。试法为：横坐标为剩余i+1个鸡蛋数，纵坐标为做j次选择，dp[i,j]表示在剩余i+1个鸡蛋做出j次选择的情况下，能够搞定几层楼。
     * 再此基础上，发现一般项计算只依赖左侧和左上角两项相加再加一，且尝试次数j无法确定。于是对其进行空间压缩，只保留鸡蛋数作为数组，ans作为当前列数
     * 当计算得到超过楼层数n时，说明此时i+1个鸡蛋(最多k个)，做出当前这么多次选择，能够探索清楚n层楼，返回选择次数，也就是列数即可。
     * 这种试法的一般项计算，接近于斐波那契数列，很快会逼近int甚至Long的最大值
     * @param k
     * @param n
     * @return int
     **/
    public static int superEggDrop4(int k, int n){
        if (k < 1 || n < 1){
            return 0;
        }

        int[] dp = new int[k];

        int ans = 0;
        while (true){
            ans++;
            int pre = 0;
            for (int i = 0; i < dp.length; i++){
                int tmp = dp[i];
                dp[i] = dp[i] + pre + 1;
                pre = tmp;

                if (dp[i] >= n){
                    return ans;
                }
            }
        }
    }


    public static void main(String[] args) {
        int maxN = 500;
        int maxK = 30;
        int testTime = 1000;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int N = (int) (Math.random() * maxN) + 1;
            int K = (int) (Math.random() * maxK) + 1;
            int ans1 = superEggDrop1(K, N);
            int ans2 = superEggDrop2(K, N);
            int ans3 = superEggDrop3(K, N);
            int ans4 = superEggDrop4(K, N);
            if (ans1 != ans4 ) {
                System.out.println("出错了!");
                break;
            }
        }
        System.out.println("测试结束");
    }

    public static int superEggDrop1(int kChess, int nLevel) {
        if (nLevel < 1 || kChess < 1) {
            return 0;
        }
        if (kChess == 1) {
            return nLevel;
        }
        int[][] dp = new int[nLevel + 1][kChess + 1];
        for (int i = 1; i != dp.length; i++) {
            dp[i][1] = i;
        }
        //i为剩余楼层数，j为剩余鸡蛋数
        for (int i = 1; i != dp.length; i++) {
            for (int j = 2; j != dp[0].length; j++) {
                int min = Integer.MAX_VALUE;
                for (int k = 1; k != i + 1; k++) {
                    min = Math.min(min, Math.max(dp[k - 1][j - 1], dp[i - k][j]));
                }
                dp[i][j] = min + 1;
            }
        }
        return dp[nLevel][kChess];
    }

}
