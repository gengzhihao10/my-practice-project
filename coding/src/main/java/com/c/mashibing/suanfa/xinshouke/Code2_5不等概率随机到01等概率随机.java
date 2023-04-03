package com.c.mashibing.suanfa.xinshouke;

public class Code2_5不等概率随机到01等概率随机 {

    /**
     * @author gengzhihao
     * @date 2023/2/12 17:41
     * @description 固定概率返回0和1（概率不等）
     * @return int
     **/
    public static int x(){
        return Math.random()<0.8 ? 0 : 1;
    }

    /**
     * @author gengzhihao
     * @date 2023/2/12 17:54
     * @description 等概率返回0和1
     * @return int
     **/
    public static int y(){
//        int ans1;
//        int ans2;
//        do {
//            ans1 = x();
//            ans2 = x();
//        }while ((ans1 == 0 && ans2 ==0) || (ans1 == 1 && ans2 ==1));
//
//        if (ans1 == 0 & ans2 == 1){
//            return 0;
//        }
//        else {
//            return 1;
//        }
        int ans = 0;
        do {
            ans = x();
        }while (ans == x());
        return ans;

    }
}
