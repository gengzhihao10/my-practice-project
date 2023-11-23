package com.c.mashibing.suanfa.tixike;

/*
 题目1，
 实现KMP算法
 假设字符串str长度为N，字符串match长度为M，M <= N
 想确定str中是否有某个子串是等于match的。
 时间复杂度O(N)

 */
public class Code28_5KMP的code1 {


    /*
     * @author gengzhihao
     * @date 2023/11/22 10:45
     * @description 题目1
     * @param s1
     * @param s2
     * @return int
     **/
    public static int getIndexOf(String s1, String s2) {

        if (s1 == null || s1.length() == 0 || s2 == null || s2.length() == 0 || s1.length() < s2.length()) {
            return -1;
        }

        char[] c1 = s1.toCharArray();
        char[] c2 = s2.toCharArray();
        int[] next = getNext(c2);
        int x = 0;
        int y = 0;


        while (x < c1.length && y < c2.length) {
            if (c1[x] == c2[y]) {
                x++;
                y++;
            } else if (y == 0) {
                x++;
            } else {
                y = next[y];
            }
        }

        return y == s2.length() ? x - y : -1;
    }


    //获取s2的next数组
    private static int[] getNext(char[] c2) {
        if (c2.length == 1) {
            return new int[]{-1};
        }
        if (c2.length == 2) {
            return new int[]{-1, 0};
        }

        int i = 2;
        int pre = 0;
        int[] next = new int[c2.length];

        while (i < c2.length){
            if (c2[i-1] == c2[pre]){
                next[i++] = ++pre;
            }
            else if (pre == 0){
                next[i++] = 0;
            }
            else {
                pre = next[pre];
            }
        }
        return next;
    }


    // for test
    public static String getRandomString(int possibilities, int size) {
        char[] ans = new char[(int) (Math.random() * size) + 1];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = (char) ((int) (Math.random() * possibilities) + 'a');
        }
        return String.valueOf(ans);
    }

    public static void main(String[] args) {
        int possibilities = 5;
        int strSize = 20;
        int matchSize = 5;
        int testTimes = 5000000;
        System.out.println("test begin");
        for (int i = 0; i < testTimes; i++) {
            String str = getRandomString(possibilities, strSize);
            String match = getRandomString(possibilities, matchSize);
            if (getIndexOf(str, match) != str.indexOf(match)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("test finish");
    }


}
