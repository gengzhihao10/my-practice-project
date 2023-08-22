package com.c.mashibing.suanfa.tixike;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/*
 题目1，打印一个字符串的全部子序列 。
 子序列是所有字符必须从前到后的顺序取自原字符串，可以决定某个字符要还是不要
 题目2，同题目1，要求不能出现重复字面值
 */
public class Code18_6打印一个字符串的全部子序列要求不要出现重复字面值得子序列 {

    /*
     * @author gengzhihao
     * @date 2023/8/21 10:24
     * @description 题目1
     * @param str
     * @return List<String>
     **/
    public static List<String> qs1_process1(String str){
        if (str == null || str.length() == 0){
            return null;
        }
        char[] chars = str.toCharArray();
        List<String> res = new LinkedList<>();
        String path = "";
        process1(chars,0,res,path);
        return res;
    }

    private static void process1(char[] chars, int index, List<String> res, String path) {
        if (index == chars.length){
            res.add(path);
            return;
        }
        //不选择chars[index]字符作为子序列
        process1(chars,index+1,res,path);
        //选择chars[index]字符作为子序列
        process1(chars,index+1,res,path+chars[index]);
    }

    public static void main(String[] args) {
        List<String> list = qs1_process1("abc");
        printList(list);
    }

    private static void printList(List<String> list) {
        for (String str : list){
            System.out.println(str);
        }
    }

    /*
     * @author gengzhihao
     * @date 2023/8/21 10:31
     * @description 题目2
     * @param str
     * @return List<String>
     **/
    public static Set<String> qs2_process1(String str){
        if (str == null || str.length() == 0){
            return null;
        }
        char[] chars = str.toCharArray();
        Set<String> res = new HashSet<>();
        String path = "";
        process2(chars,0,res,path);
        return res;
    }

    private static void process2(char[] chars, int index, Set<String> res, String path) {
        if (index == chars.length){
            res.add(path);
            return;
        }
        //不选择chars[index]字符作为子序列
        process2(chars,index+1,res,path);
        //选择chars[index]字符作为子序列
        process2(chars,index+1,res,path+chars[index]);
    }
}
