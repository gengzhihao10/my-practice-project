package com.c.mashibing.suanfa.tixike;

/*
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

    //***********************************************************************************

    /*
     * @author gengzhihao
     * @date 2023/8/31 10:01
     * @description 题目2
     * @param arr
     * @return int
     **/
    public static int qs2_process1(int[] arr){
        int L = 0;
        int R = arr.length-1;
        int N = arr.length;
        int[][] preArr = new int[N][N];
        int[][] lastArr = new int[N][N];

        for (int i = 0; i < N; i++){
            for (int j = 0; j < N; j++){
                preArr[i][j] = -1;
                lastArr[i][j] = -1;
            }
        }

        int res1 = pre2(arr,L,R,preArr,lastArr);
        int res2 = last2(arr,L,R,preArr,lastArr);
        return Math.max(res1,res2);
    }

    //相对先手得到的收益
    private static int pre2(int[] arr, int L, int R,int[][] preArr,int[][] lastArr) {
        if (preArr[L][R] != -1){
            return preArr[L][R];
        }

        if (L==R){
            preArr[L][R] = arr[L];
            return arr[L];
        }

        int ans = 0;
        int pos1 = arr[L] + last2(arr,L+1,R,preArr,lastArr);
        int pos2 = arr[R] + last2(arr,L,R-1,preArr,lastArr);
        ans = Math.max(pos1,pos2);

        preArr[L][R] = ans;
        return ans;
    }

    //相对后手得到的收益
    private static int last2(int[] arr, int L, int R,int[][] preArr,int[][] lastArr) {
        if (lastArr[L][R] != -1){
            return lastArr[L][R];
        }

        if (L == R){
            lastArr[L][R] = 0;
            return 0;
        }

        int ans = 0;
        int pos1 = pre2(arr,L+1,R,preArr,lastArr);
        int pos2 = pre2(arr,L,R-1,preArr,lastArr);
        ans = Math.min(pos1,pos2);

        lastArr[L][R] = ans;
        return ans;
    }

    //********************************************************************************


    /*
     * @author gengzhihao
     * @date 2023/8/31 10:14
     * @description 题目3
     * @param arr
     * @return int
     **/
    public static int qs3_process1(int[] arr){
        int N = arr.length;
        int[][] preArr = new int[N][N];
        int[][] lastArr = new int[N][N];

        //基于base case的初始化
        for (int i = 0; i < N; i++){
            preArr[i][i] = arr[i];
            lastArr[i][i] = 0;
        }

        for (int i = 1; i < N; i++){
            int L = 0;
            int R = i;
            while (R < N){
                preArr[L][R] = Math.max(arr[L] + lastArr[L+1][R],arr[R] + lastArr[L][R-1]);
                lastArr[L][R] = Math.min(preArr[L+1][R],preArr[L][R-1]);

                L++;
                R++;
            }
        }
        return Math.max(preArr[0][N-1],lastArr[0][N-1]);
    }

    public static void main(String[] args) {
        int[] arr = { 5, 7, 4, 5, 8, 1, 6, 0, 3, 4, 6, 1, 7 };
        System.out.println(qs1_process1(arr));
        System.out.println(qs2_process1(arr));
        System.out.println(qs3_process1(arr));

    }

}
