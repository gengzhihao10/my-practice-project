package com.c.mashibing.suanfa.tixike;


/**
 * @author gengzhihao
 * @date 2023/5/11 10:32
 * @description
 * 题目1,怎么把一个Int类型的数，提取出最右侧的1来
**/

public class Code3_3怎么把一个int类型的数提取出最右侧的1来 {

    /*
     * @author gengzhihao
     * @date 2023/5/11 10:30
     * @description 题目1主程序
     * @param target
     **/
    public static int extract(int target){
        int result = target & (-target);
        return result;
    }

    public static void main(String[] args) {
        int result = extract(40);
        System.out.println(result);
    }
}
