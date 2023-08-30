package com.c.mashibing.suanfa.tixike;

/*
todo
 题目1，
 给定一个整型数组arr，代表数值不同的纸牌排成一条线
 玩家A和玩家B依次拿走每张纸牌
 规定玩家A先拿，玩家B后拿
 但是每个玩家每次只能拿走最左或最右的纸牌
 玩家A和玩家B都绝顶聪明
 请返回最后获胜者的分数。
 题目2，同题目1，改为缓存版本
 题目3，同题目1，改为动态规划版本
 */
public class Code19_7给定一个整形数组arr代表数值不同的纸牌 {

    /*
     * @author gengzhihao
     * @date 2023/8/30 10:17
     * @description 题目1
     * @param arr
     * @return int
     **/
    public static int qs1_process1(int[] arr){
        int L = 0;
        int R = arr.length-1;
        int res1 = pre(arr,L,R);
        int res2 = last(arr,L,R);
        return Math.max(res1,res2);
    }

    //相对先手得到的收益
    private static int pre(int[] arr, int L, int R) {
        if (L==R){
            return arr[L];
        }
        int pos1 = arr[L] + last(arr,L+1,R);
        int pos2 = arr[R] + last(arr,L,R-1);
        return Math.max(pos1,pos2);
    }

    //相对后手得到的收益
    private static int last(int[] arr, int L, int R) {
        if (L == R){
            return 0;
        }
        int pos1 = pre(arr,L+1,R);
        int pos2 = pre(arr,L,R-1);
        return Math.min(pos1,pos2);
    }

    public static void main(String[] args) {
        int[] arr = { 5, 7, 4, 5, 8, 1, 6, 0, 3, 4, 6, 1, 7 };
        System.out.println(qs1_process1(arr));
//        System.out.println(win2(arr));
//        System.out.println(win3(arr));

    }

}
