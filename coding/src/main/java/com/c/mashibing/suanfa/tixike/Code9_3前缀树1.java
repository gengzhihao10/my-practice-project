package com.c.mashibing.suanfa.tixike;

import org.springframework.util.StringUtils;

/*
 题目1，实现一个前缀树。包括如下方法
 public void insert(String word)//将字符串插入前缀树
 public int search(String word)//有多少个字符串插入到了前缀树中
 public int prefixSearch(String pre) //有多少个字符串以传入的Pre字符串为前缀
 public void delete(String word)//在前缀树中删除字符串
 */
public class Code9_3前缀树1 {

    //前缀树节点
    public static class Node{
        int pass = 0;
        int end = 0;
        Node[] nextNode = new Node[26];

        public Node() {
        }

    }

    //前缀树
    public static class PreFixTree{

        Node root = null;

        //插入
        public void insert(String word){
            if (StringUtils.isEmpty(word)){
                return;
            }

            if (root == null){
                root = new Node();
            }
            Node cur = root;
            cur.pass++;
            char[] chars = word.toCharArray();
            for (int i = 0; i < chars.length; i++){
                int nextIndex = (int)(chars[i]-'a');
                if (cur.nextNode[nextIndex] == null){
                    cur.nextNode[nextIndex] = new Node();
                }
                cur.nextNode[nextIndex].pass++;
                cur = cur.nextNode[nextIndex];
            }
            cur.end++;
        }

        //查找字符串个数
        public int search(String word){
            if (StringUtils.isEmpty(word)){
                return 0;
            }

            if (root == null){
                return 0;
            }
            Node cur = root;
            char[] chars = word.toCharArray();
            for (int i = 0; i < chars.length; i++){
                int nextIndex = (int)(chars[i]-'a');
                if (cur.nextNode[nextIndex] == null){
                    return 0;
                }
                cur = cur.nextNode[nextIndex];
            }
            return cur.end;
        }

        //查找有多少个字符串以Pre为前缀
        public int prefixSearch(String pre){
            if (StringUtils.isEmpty(pre)){
                return 0;
            }

            if (root == null){
                return 0;
            }
            Node cur = root;
            char[] chars = pre.toCharArray();
            for (int i = 0; i < chars.length; i++){
                int nextIndex = (int)(chars[i]-'a');
                if (cur.nextNode[nextIndex] == null){
                    return 0;
                }
                cur = cur.nextNode[nextIndex];
            }
            return cur.pass;
        }


        //删除字符串
        public void delete(String word){
            if (StringUtils.isEmpty(word) && search(word) == 0){
                return;
            }

            Node cur = root;
            char[] chars = word.toCharArray();
            for (int i = 0; i < chars.length; i++){
                int nextIndex = (int)(chars[i]-'a');
                cur.nextNode[nextIndex].pass--;
                if (cur.nextNode[nextIndex].pass == 0){
                    cur.nextNode[nextIndex] = null;
                    return;
                }
                cur = cur.nextNode[nextIndex];
            }
            cur.end--;
        }

        public static void main(String[] args) {
            PreFixTree tree = new PreFixTree();
            tree.insert("abc");
            System.out.println("abc是否存在"+tree.search("abc"));
            tree.insert("abd");
            System.out.println("有多少个字符串以ab为前缀" + tree.prefixSearch("ab"));
            tree.delete("abc");
            System.out.println("abc是否存在"+tree.search("abc"));
            System.out.println("abd是否存在"+tree.search("abd"));
            tree.delete("abc");
        }
    }
}
