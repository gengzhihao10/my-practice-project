package com.c.mashibing.suanfa.tixike;

import java.util.LinkedList;
import java.util.Queue;

/*
 题目1，实现AC自动机

 */
public class Code33_7AC自动机code {

    public static class Node{
        int end;
        Node fail;
        Node[] nexts;

        public Node() {
            end = 0;
            fail = null;
            nexts = new Node[26];
        }
    }


    public static class ACAutomation {
        private Node root;

        public ACAutomation() {
            root = new Node();
        }

        //将词汇插入ac自动机，构成敏感词前缀树
        public void insert(String string){
            if (string == null || string.length() == 0){
                return;
            }

            char[] str = string.toCharArray();
            Node cur = root;
            int index = 0;

            for (int i = 0; i < str.length; i++){
                index = str[i] - 'a';
                if (cur.nexts[index] == null){
                    cur.nexts[index] = new Node();
                }
                cur = cur.nexts[index];
            }
            cur.end++;
        }


        //给敏感词前缀树的fail指针build好
        public void build(){
            Node cur = root;
            Node cfail = null;
            Queue<Node> queue = new LinkedList<>();
            queue.add(root);

            while (!queue.isEmpty()){
                cur = queue.poll();
                for (int i = 0; i < 26; i++){
                    //存在这个方向的子节点，将其fail指针build好
                    if (cur.nexts[i] != null){
                        cur.nexts[i].fail = root;
                        cfail = cur.fail;

                        while (cfail != null){
                            if (cfail.nexts[i] != null){
                                cur.nexts[i].fail = cfail.nexts[i];
                                break;
                            }
                            cfail = cfail.fail;
                        }
                        queue.add(cur.nexts[i]);
                    }
                }
            }
        }


        //搜索正文content中有多少敏感词
        public int containNum(String content){
            char[] str = content.toCharArray();

            Node cur = root;
            Node follow = null;
            int index = 0;
            int sum = 0;

            for (int i = 0; i < str.length; i++){
                index = str[i] - 'a';

                while (cur.nexts[index] == null && cur != root){
                    cur = cur.fail;
                }
                cur = cur.nexts[index] != null ? cur.nexts[index] : root;
                follow = cur;
                //结算cur及其fail指针一串上的所有节点
                while (follow != root){
                    if (follow.end == -1){
                        break;
                    }
                    sum += follow.end;
                    follow.end = -1;
                    follow = follow.fail;
                }
            }
            return sum;
        }
    }

    public static void main(String[] args) {
        ACAutomation ac = new ACAutomation();
        ac.insert("dhe");
        ac.insert("he");
        ac.insert("c");
        ac.build();
        System.out.println(ac.containNum("cdhe"));
    }
}
