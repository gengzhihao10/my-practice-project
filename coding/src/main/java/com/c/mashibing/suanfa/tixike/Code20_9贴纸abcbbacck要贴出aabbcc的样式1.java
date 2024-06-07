package com.c.mashibing.suanfa.tixike;


import java.util.HashMap;
import java.util.Map;

/*
 题目1，
 给定一个字符串str，给定一个字符串类型的数组arr，出现的字符都是小写英文
 arr每一个字符串，代表一张贴纸，你可以把单个字符剪开使用，目的是拼出str来
 返回需要至少多少张贴纸可以完成这个任务。
 例子：str= "babac"，arr = {"ba","c","abcd"}
 ba + ba + c  3  abcd + abcd 2  abcd+ba 2
 所以返回2
 暴力递归
 题目2，同题目1，优化递归
 题目3，同题目2，加缓存
 */
public class Code20_9贴纸abcbbacck要贴出aabbcc的样式1 {


    /*
     * @author gengzhihao
     * @date 2023/9/4 11:12
     * @description 题目1
     * @param stickers
     * @param target
     * @return int
     **/
    public static int minStickers1(String[] stickers, String target) {
        if (stickers == null || stickers.length == 0 || target == null || target.length() == 0) {
            return 0;
        }

        int res = process1(stickers, target);
        return res == Integer.MAX_VALUE ? -1 : res;
    }

    //如果剩余的target为空，则说明路径已走完，返回0.
    // 返回0后，由上一层递归调用得到新的min，并据此逐层调用+1，最终返回个数
    private static int process1(String[] stickers, String target) {
        if (target.length() == 0) {
            return 0;
        }

        int min = Integer.MAX_VALUE;
        for (String sticker : stickers) {
            String rest = eliminateChar(sticker, target);
            if (!rest.equals(target)) {
                min = Math.min(min, process1(stickers, rest));
            }
        }
        return min + (min == Integer.MAX_VALUE ? 0 : 1);
    }

    //将target中包含的sticker中的字符，消除掉，并返回target剩余的字符
    private static String eliminateChar(String sticker, String target) {
        char[] tchars = target.toCharArray();
        int[] ans = new int[26];
        for (char t : tchars) {
            ans[t - 'a']++;
        }
        char[] schars = sticker.toCharArray();
        for (char s : schars) {
            ans[s - 'a']--;
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 26; i++) {
            if (ans[i] > 0) {
                for (int j = 0; j < ans[i]; j++) {
                    sb.append((char) (i + 'a'));
                }
            }
        }
        String res = sb.toString();
//        System.out.println(res);
        return res;
    }

    public static void main(String[] args) {
        String[] stickers = {"these", "guess", "about", "garden", "him"};
        String target = "atomher";
        int res = minStickers3(stickers, target);
        System.out.println(res);

//        String[] stickers = {"with","example","science"};
//        String target = "thehat";
//        int res = minStickers2(stickers,target);
//        System.out.println(res);
    }


    /*
     * @author gengzhihao
     * @date 2023/9/4 11:13
     * @description 题目2
     * @param stickers
     * @param target
     * @return int
     **/
    public static int minStickers2(String[] stickers, String target) {
        if (stickers == null || stickers.length == 0 || target == null || target.length() == 0) {
            return 0;
        }
        int[][] newStickers = new int[stickers.length][26];
        for (int i = 0; i < stickers.length; i++) {
            char[] chars = stickers[i].toCharArray();
            for (char c : chars) {
                newStickers[i][c - 'a']++;
            }
        }

        int res = process2(newStickers, target);
        return res == Integer.MAX_VALUE ? -1 : res;
    }

    private static int process2(int[][] stickers, String target) {
        if (target.length() == 0) {
            return 0;
        }
        char[] tchars = target.toCharArray();
        //统计target词频
        int[] ans = new int[26];
        for (char t : tchars) {
            ans[t - 'a']++;
        }

        int min = Integer.MAX_VALUE;
        for (int[] sticker : stickers) {
            if (sticker[tchars[0] - 'a'] > 0) {
                //ans[i]词频大于0的，组成剩余字符串rest
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < 26; i++) {

                    int res = ans[i] - sticker[i];
                    for (int j = 0; j < res; j++) {
                        sb.append((char) (i + 'a'));
                    }
                }
                String rest = sb.toString();
                min = Math.min(min, process2(stickers, rest));
            }
        }
        return min + (min == Integer.MAX_VALUE ? 0 : 1);
    }


    /*
     * @author gengzhihao
     * @date 2023/9/4 11:13
     * @description 题目3
     * @param stickers
     * @param target
     * @return int
     **/
    public static int minStickers3(String[] stickers, String target) {
        if (stickers == null || stickers.length == 0 || target == null || target.length() == 0) {
            return 0;
        }
        int[][] newStickers = new int[stickers.length][26];
        for (int i = 0; i < stickers.length; i++) {
            char[] chars = stickers[i].toCharArray();
            for (char c : chars) {
                newStickers[i][c - 'a']++;
            }
        }
        Map<String,Integer> dp = new HashMap<>();
        int res = process3(newStickers, target,dp);
        return res == Integer.MAX_VALUE ? -1 : res;
    }

    private static int process3(int[][] stickers, String target, Map<String, Integer> dp) {
        if (dp.containsKey(target)){
            return dp.get(target);
        }

        if (target.length() == 0) {
            return 0;
        }
        char[] tchars = target.toCharArray();
        //统计target词频
        int[] ans = new int[26];
        for (char t : tchars) {
            ans[t - 'a']++;
        }

        int min = Integer.MAX_VALUE;
        for (int[] sticker : stickers) {
            if (sticker[tchars[0] - 'a'] > 0) {
                //ans[i]词频大于0的，组成剩余字符串rest
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < 26; i++) {

                    int res = ans[i] - sticker[i];
                    for (int j = 0; j < res; j++) {
                        sb.append((char) (i + 'a'));
                    }
                }
                String rest = sb.toString();
                min = Math.min(min, process3(stickers, rest,dp));
            }
        }
        min = min + (min == Integer.MAX_VALUE ? 0 : 1);
        dp.put(target, min);
        return min;
    }
}
