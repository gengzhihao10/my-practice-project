package com.c.mashibing.suanfa.tixike;

/*
 题目1，
 规定1和A对应、2和B对应、3和C对应...26和Z对应
 那么一个数字字符串比如"111”就可以转化为:
 "AAA"、"KA"和"AK"
 给定一个只有数字字符组成的字符串str，返回有多少种转化结果
 使用暴力递归方法实现
 题目2，使用动态规划实现
 */
public class Code20_5规定1对应A2对应B3对应C依次类推 {



    /*
     * @author gengzhihao
     * @date 2023/9/2 10:38
     * @description 题目1
     * @param str
     * @return int
     **/
    public static int qs1_process1(String str){
        if (str == null || str.length() == 0){
            return 0;
        }
        return process1(str.toCharArray(),0);
    }

    //返回：str从index位置开始，到右边结束，能够找到的最多的转化可能性
    private static int process1(char[] chars, int index) {
        if (index >= chars.length){
            return 1;
        }
        //返回0，走不下去的情况
        if (chars[index] == '0'){
            return 0;
        }

        int res = 0;
        //单个的算，从1到9
        res += process1(chars,index+1);
        //2个字符转一个数，从10到26
        if (index+1 < chars.length &&  (chars[index] - '0')*10 + (chars[index+1] - '0') <= 26){
            res += process1(chars,index+2);
        }
        return res;
    }

    //*****************************************************************************************


    /*
     * @author gengzhihao
     * @date 2023/9/2 11:00
     * @description 题目2
     * @param str
     * @return int
     **/
    public static int qs2_process1(String str){
        if (str == null || str.length() == 0){
            return 0;
        }
        char[] chars = str.toCharArray();
        int[] ans = new int[chars.length+1];
        ans[chars.length] = 1;

        for (int index = chars.length-1; index >= 0; index--){
            if (chars[index] == '0'){
                ans[index] = 0;
                continue;
            }
            int res = 0;
            res += ans[index+1];
            if (index+1 < chars.length &&  (chars[index] - '0')*10 + (chars[index+1] - '0') <= 26){
                res += ans[index+2];
            }
            ans[index] = res;
        }
        return ans[0];
    }



    //*********************************************************************************************

    public static void main(String[] args) {
        int N = 30;
        int testTime = 1000000;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int len = (int) (Math.random() * N);
            String s = randomString(len);
            int ans0 = qs1_process1(s);
            int ans1 = qs2_process1(s);
            if (ans0 != ans1) {
                System.out.println(s);
                System.out.println(ans0);
                System.out.println(ans1);
                System.out.println("Oops!");
                break;
            }
        }
        System.out.println("测试结束");
    }


    // 为了测试
    public static String randomString(int len) {
        char[] str = new char[len];
        for (int i = 0; i < len; i++) {
            str[i] = (char) ((int) (Math.random() * 10) + '0');
        }
        return String.valueOf(str);
    }


    public static int dp1(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        char[] str = s.toCharArray();
        int N = str.length;
        int[] dp = new int[N + 1];
        dp[N] = 1;
        for (int i = N - 1; i >= 0; i--) {
            if (str[i] != '0') {
                int ways = dp[i + 1];
                if (i + 1 < str.length && (str[i] - '0') * 10 + str[i + 1] - '0' < 27) {
                    ways += dp[i + 2];
                }
                dp[i] = ways;
            }
        }
        return dp[0];
    }
}
