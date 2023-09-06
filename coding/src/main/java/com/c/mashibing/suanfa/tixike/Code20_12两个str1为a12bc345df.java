package com.c.mashibing.suanfa.tixike;

/*
 题目1，
 给定两个字符串str1和str2，
 返回这两个字符串的最长公共子序列长度
 比如 ： str1 = “a12b3c456d”,str2 = “1ef23ghi4j56k”
 最长公共子序列是“123456”，所以返回长度6
 暴力递归
 题目2，同题目1，表直接计算的动态规划
 */
public class Code20_12两个str1为a12bc345df {


    /*
     * @author gengzhihao
     * @date 2023/9/4 11:21
     * @description 题目1
     * @param s1
     * @param s2
     * @return int
     **/
    public static int longestCommonSubsequence1(String s1, String s2){
        if (s1 == null || s1.length() == 0 || s2 == null || s2.length() == 0){
            return 0;
        }

        char[] c1 = s1.toCharArray();
        char[] c2 = s2.toCharArray();
        return process1(c1,c2,c1.length-1,c2.length-1);
    }

    //返回这两个字符串的最长公共子序列长度
    private static int process1(char[] s1, char[] s2,int index1, int index2) {
        if (index1 == 0 && index2 == 0){
            return s1[index1] == s2[index2] ? 1 : 0;
        }
        else if (index1 == 0) {
            if (s1[index1] == s2[index2]){
                return 1;
            }else {
                return process1(s1,s2,index1,index2-1);
            }
        }
        else if (index2 == 0){
            if (s1[index1] == s2[index2]){
                return 1;
            }else {
                return process1(s1,s2,index1-1,index2);
            }
        }else {
            int p1 = 0 + process1(s1,s2,index1,index2-1);
            int p2 = 0 + process1(s1,s2,index1-1,index2);
            int p3 = s1[index1] == s2[index2] ? 1 + process1(s1,s2,index1-1,index2-1) : 0;
            return Math.max(p1,Math.max(p2,p3));
        }
    }


    /*
     * @author gengzhihao
     * @date 2023/9/6 9:25
     * @description 题目2
     * @param s1
     * @param s2
     * @return int
     **/
    public static int longestCommonSubsequence2(String s1, String s2){
        if (s1 == null || s1.length() == 0 || s2 == null || s2.length() == 0){
            return 0;
        }

        char[] c1 = s1.toCharArray();
        char[] c2 = s2.toCharArray();
        int[][] ans = new int[c1.length][c2.length];

        ans[0][0] = c1[0] == c2[0] ? 1 : 0;
        for (int index2 = 1; index2 < c2.length; index2++){
            ans[0][index2] = c1[0] == c2[index2] ? 1 : ans[0][index2-1];
        }
        for (int index1 = 1; index1 < c1.length; index1++){
            ans[index1][0] = c1[index1] == c2[0] ? 1 : ans[index1-1][0];
        }

        for (int index1 = 1; index1 < c1.length; index1++){
            for (int index2 = 1; index2 < c2.length; index2++){
                ans[index1][index2] = Math.max((c1[index1] == c2[index2] ? 1 +ans[index1-1][index2-1] : 0),
                        Math.max(ans[index1][index2-1],ans[index1-1][index2]));
            }
        }
        return ans[c1.length-1][c2.length-1];
    }
}
