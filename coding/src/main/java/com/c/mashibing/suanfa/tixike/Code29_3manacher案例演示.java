package com.c.mashibing.suanfa.tixike;

/*
 题目1，
 实现manacher算法，找到最长回文子串的大小
 */
public class Code29_3manacher案例演示 {


    /*
     * @author gengzhihao
     * @date 2023/11/27 10:10
     * @description 题目1
     * @param s
     * @return int
     **/
    public static int manacher(String s){
        if (s == null || s.length() == 0){
            return 0;
        }

        //在s中每个字符左右都添加#字符
        char[] str = addCharacter(s.toCharArray());

        //记录以下标为i的字符串的最大回文半径
        int[] pArr = new int[str.length];
        int max = -1;
        int C = -1, R = -1;
        for (int i = 0; i < str.length; i++){
            //i为中心，不用遍历，最小能扩到多少
            pArr[i] = R > i ? Math.min(pArr[2 * C - i],R - i) : 1;
            while ( i + pArr[i] < str.length && i - pArr[i] >= 0 && (str[i + pArr[i]] == str[i - pArr[i]])){
                pArr[i]++;
            }
            if (i + pArr[i] > R){
                R = i + pArr[i];
                C = i;
            }
            max = Math.max(max,pArr[i]);
        }
        return max-1;
    }

    private static char[] addCharacter(char[] chars) {
        char[] res = new char[chars.length * 2 + 1];
        int index = 0;
        for (int i = 0; i < res.length; i++){
            res[i] = ((i & 1) == 1) ? chars[index++] : '#';
        }
        return res;
    }

    //*************************************************************************************
    public static char[] manacherString(String str) {
        char[] charArr = str.toCharArray();
        char[] res = new char[str.length() * 2 + 1];
        int index = 0;
        for (int i = 0; i != res.length; i++) {
            res[i] = (i & 1) == 0 ? '#' : charArr[index++];
        }
        return res;
    }

    // for test
    public static int right(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        char[] str = manacherString(s);
        int max = 0;
        for (int i = 0; i < str.length; i++) {
            int L = i - 1;
            int R = i + 1;
            while (L >= 0 && R < str.length && str[L] == str[R]) {
                L--;
                R++;
            }
            max = Math.max(max, R - L - 1);
        }
        return max / 2;
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
        int testTimes = 5000000;
        System.out.println("test begin");
        for (int i = 0; i < testTimes; i++) {
            String str = getRandomString(possibilities, strSize);
            if (manacher(str) != right(str)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("test finish");
    }
}
