package com.c.mashibing.suanfa.tixike;

/*
 题目1，
 给定一个正整数N，表示有N份青草统一堆放在仓库里
 有一只牛和一只羊，牛先吃，羊后吃，它俩轮流吃草
 不管是牛还是羊，每一轮能吃的草量必须是：
 1，4，16，64…(4的某次方)
 谁最先把草吃完，谁获胜
 假设牛和羊都绝顶聪明，都想赢，都会做出理性的决定
 根据唯一的参数N，返回谁会赢

 */
public class Code39_2轮流吃草返回谁会赢 {


    /*
     * @author gengzhihao
     * @date 2024/1/26 10:32
     * @description 暴力解
     * @param n
     * @return String
     **/
    public static String whoWin0(int n) {
        if (n == 0) {
            return "后手";
        }
        if (n == 1) {
            return "先手";
        }

        //i *= 4有溢出风险
        for (int i = 1; i <= n; i *= 4) {
            //在下一轮中后手胜出，即本轮中的先手胜出
            if ("后手".equals(whoWin0(n - i))) {
                return "先手";
            }
            //大于，则意味着即将溢出
            if (i > n / 4) {
                break;
            }
        }
        return "后手";
    }

    /*
     * @author gengzhihao
     * @date 2024/1/25 9:17
     * @description 题目1
     * @param n
     * @return String
     **/
    public static String whoWin(int n) {
        int rest = n % 5;
        return rest == 0 || rest == 2 ? "后手" : "先手";
    }

    public static String winner2(int n) {
        if (n % 5 == 0 || n % 5 == 2) {
            return "后手";
        } else {
            return "先手";
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i <= 50; i++) {
//            System.out.println("winner2:   " + i + " : " + winner2(i));
//            System.out.println("whoWin0:   " + i + " : " + whoWin0(i));
//            System.out.println();
            if (!winner2(i).equals(whoWin(i))) {
                System.out.println("oops");
                System.out.println("winner2:   " + i + " : " + winner2(i));
                System.out.println("whoWin:   " + i + " : " + whoWin(i));
                break;
            }
        }
//        System.out.println(whoWin0(4));;
    }
}
