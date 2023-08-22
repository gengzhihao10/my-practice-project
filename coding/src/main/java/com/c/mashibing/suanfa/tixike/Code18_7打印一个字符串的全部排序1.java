package com.c.mashibing.suanfa.tixike;



import java.util.LinkedList;
import java.util.List;

/*
 题目1，打印一个字符串的全部排序。
 也就是说字符串里的字符不可以丢弃，只是可以调整顺序
 题目2，同题目1，要求去重
 */
public class Code18_7打印一个字符串的全部排序1 {


    /*
     * @author gengzhihao
     * @date 2023/8/22 10:33
     * @description 题目1
     * @param str
     * @return List<String>
     **/
    public static List<String> qs1_process1(String str){
        if (str == null || str.length() == 0){
            return null;
        }
        char[] chars = str.toCharArray();
        List<Character> characters = new LinkedList<>();
        for (char c : chars){
            characters.add(c);
        }
        List<String> res = new LinkedList<>();
        String path = "";
        process1(characters,path,res);
        return res;
    }

    /*
     * @author gengzhihao
     * @date 2023/8/22 11:14
     * @description
     * @param characters 剩余的字符
     * @param index 结果str中index位置
     * @param path
     * @param res
     **/
    private static void process1(List<Character>  characters, String path, List<String> res) {
        if (characters.size() == 0){
            res.add(path);
            System.out.println(path);
            return;
        }
        for (int i = 0; i < characters.size(); i++){
            char c = characters.remove(i);
            process1(characters,path+c,res);
            characters.add(i,c);
        }
    }

    public static void main(String[] args) {
        qs1_process1("abc");
    }

    //**************************************************************************
    /*
     * @author gengzhihao
     * @date 2023/8/22 11:27
     * @description 题目2
     * @param str
     * @return List<String>
     **/
    public static List<String> qs2_process1(String str){
        if (str == null || str.length() == 0){
            return null;
        }
        char[] chars = str.toCharArray();
        List<Character> characters = new LinkedList<>();
        for (char c : chars){
            characters.add(c);
        }
        List<String> res = new LinkedList<>();
        String path = "";
        process2(characters,path,res);
        return res;
    }


    private static void process2(List<Character>  characters, String path, List<String> res) {
        if (characters.size() == 0){
            res.add(path);
            System.out.println(path);
            return;
        }
        boolean[] choice = new boolean[256];
        for (int i = 0; i < characters.size(); i++){
            if (!choice[characters.get(i)]){
                char c = characters.remove(i);
                process2(characters,path+c,res);
                characters.add(i,c);
                choice[characters.get(i)] = true;
            }
        }
    }
}
