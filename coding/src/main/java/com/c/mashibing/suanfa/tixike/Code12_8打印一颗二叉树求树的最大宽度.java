package com.c.mashibing.suanfa.tixike;

import java.util.LinkedList;
import java.util.Queue;

/*
 题目1，设计一个能够打印二叉树的打印函数(很少考，不写了)
 题目2，秋二叉树最宽的层有多少个节点
 */
public class Code12_8打印一颗二叉树求树的最大宽度 {

    //二叉树
    private static class Node{
        int value;
        Node left;
        Node right;

        public Node() {
        }

        public Node(int value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return String.valueOf(value);
        }
    }

    /*
     * @author gengzhihao
     * @date 2023/7/22 11:04
     * @description 题目2
     * @param root
     * @return int
     **/
    public static int qs2_process1(Node root){
        if (root == null){
            return 0;
        }

        int maxWidth = 0;
        int width = 0;
        Node curEnd = root,nextEnd = null;
        Queue<Node> queue = new LinkedList<>();
        Node cur = root;
        queue.offer(cur);

        while (!queue.isEmpty()){
            cur = queue.poll();
            if (cur.left != null){
                queue.offer(cur.left);
                nextEnd = cur.left;
            }
            if (cur.right != null){
                queue.offer(cur.right);
                nextEnd = cur.right;
            }
            width++;
            if (cur == curEnd){
                maxWidth = Math.max(maxWidth,width);
                curEnd = nextEnd;
                width = 0;
            }
        }
        return maxWidth;
    }


    public static void main(String[] args) {
        Node n1 = new Node(1);
        Node n2 = new Node(2);
        Node n3 = new Node(3);
        Node n4 = new Node(4);
        Node n5 = new Node(5);
        Node n6 = new Node(6);
        Node n7 = new Node(7);
        Node n8 = new Node(8);
        n1.left = n2;
        n1.right = n3;
        n2.left = n4;
        n2.right = n5;
        n3.left = n6;
        n3.right = n7;
        n6.left = n8;

        System.out.println(qs2_process1(n1));
    }
}
