package com.c.mashibing.suanfa.tixike;

/*
 题目1，给定一个字符串str，只由‘X’和‘.’两种字符构成。
    ‘X’表示墙，不能放灯，也不需要点亮
    ‘.’表示居民点，可以放灯，需要点亮
    如果灯放在i位置，可以让i-1，i和i+1三个位置被点亮
    返回如果点亮str中所有需要点亮的位置，至少需要几盏灯
 */
public class Code15_8给定字符串str返回点亮str中所有点亮的位置至少需要几盏灯 {


    /*
     * @author gengzhihao
     * @date 2023/8/5 10:32
     * @description 题目1
     * @param str
     * @return int
     **/
    public static int qs1_process1(String str){
        char[] chars = str.toCharArray();

        if (chars == null || chars.length == 0 || (chars.length == 1 && chars[0] == 'X')){
            return 0;
        }
        if (chars.length == 1 && chars[0] == '.'){
            return 1;
        }

        int count = 0;
        int i = 0;
        while (i < chars.length){
            //i位置为X，
            if (chars[i] == 'X'){
                i += 1;
            }
            //i位置是.
            else {
                count++;
                //i+1位置是'X'
                if (i+1 >= chars.length){
                    return count;
                }
                if (chars[i+1] == 'X'){
                    i += 2;
                }
                //i+1位置是.
                else {
                    i += 3;
                }
            }
        }
        return count;
    }



    public static void main(String[] args) {
        int len = 20;
        int testTime = 100000;
        for (int i = 0; i < testTime; i++) {
            String test = randomString(len);
            int ans1 = qs1_process1(test);
//            int ans2 = minLight2(test);
            int ans3 = minLight3(test);
            if (ans1 != ans3) {
                System.out.println("oops!");
            }
        }
        System.out.println("finish!");
    }

    // for test
    public static String randomString(int len) {
        char[] res = new char[(int) (Math.random() * len) + 1];
        for (int i = 0; i < res.length; i++) {
            res[i] = Math.random() < 0.5 ? 'X' : '.';
        }
        return String.valueOf(res);
    }

    // 更简洁的解法
    // 两个X之间，数一下.的数量，然后除以3，向上取整
    // 把灯数累加
    public static int minLight3(String road) {
        char[] str = road.toCharArray();
        int cur = 0;
        int light = 0;
        for (char c : str) {
            if (c == 'X') {
                light += (cur + 2) / 3;
                cur = 0;
            } else {
                cur++;
            }
        }
        light += (cur + 2) / 3;
        return light;
    }
}
