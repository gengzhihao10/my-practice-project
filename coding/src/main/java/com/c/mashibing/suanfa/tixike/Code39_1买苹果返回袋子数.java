package com.c.mashibing.suanfa.tixike;

/*
 题目1，
 小虎去买苹果，商店只提供两种类型的塑料袋，每种类型都有任意数量。
 1）能装下6个苹果的袋子
 2）能装下8个苹果的袋子
 小虎可以自由使用两种袋子来装苹果，但是小虎有强迫症，他要求自己使用的袋子数量必须最少，且使用的每个袋子必须装满。
 给定一个正整数N，返回至少使用多少袋子。如果N无法让使用的每个袋子必须装满，返回-1

 */
public class Code39_1买苹果返回袋子数 {


    /*
     * @author gengzhihao
     * @date 2024/1/25 9:15
     * @description 暴力解
     * @param apple
     * @return int
     **/
    public static int minBags0(int apple){
        if (apple < 0){
            return -1;
        }

        int bag8 = 0;
        for (bag8 = apple / 8; bag8 >= 0; bag8--){
            int rest = apple - bag8 * 8;
            if (rest % 6 == 0){
                return bag8 + rest / 6;
            }
        }
        return -1;
    }

    /*
     * @author gengzhihao
     * @date 2024/1/25 9:06
     * @description 题目1
     * @param apple
     * @return int
     **/
    public static int minBags(int apple){
        //hard coding，硬编码
        if (apple < 18){
            return apple == 6 || apple == 8 ? 1 : (apple == 12 || apple == 14 || apple == 16 ? 2 : -1);
        }
        //奇数返回-1，偶数再去计算
        return apple % 2 != 0 ? -1 : (apple - 18) / 8 + 3;
    }

    //for test
    public static int minBagAwesome(int apple) {
        if ((apple & 1) != 0) { // 如果是奇数，返回-1
            return -1;
        }
        if (apple < 18) {
            return apple == 0 ? 0 : (apple == 6 || apple == 8) ? 1
                    : (apple == 12 || apple == 14 || apple == 16) ? 2 : -1;
        }
        return (apple - 18) / 8 + 3;
    }

    public static void main(String[] args) {
        for (int i = 1; i < 200;i++){
            if (minBagAwesome(i) != minBags(i)){
                System.out.println("mingabs:     " + i + " " + minBags(i));
                System.out.println("minBagAwesome: " + i + " " + minBagAwesome(i));
                break;
            }
//            System.out.println("mingabs0:      " + i + " " + minBags0(i));
//            System.out.println("mingabs:       " + i + " " + minBags(i));
//            System.out.println("minBagAwesome: " + i + " " + minBagAwesome(i));
//            System.out.println();
        }
    }
}
