package com.c.mashibing.suanfa.tixike;

import java.util.LinkedList;
import java.util.Queue;

/*
 题目1，实现二叉树的按层遍历
 */
public class Code12_2实现二叉树的按层遍历 {


    //二叉树节点
    static class Node{
        int value;
        Node left;
        Node right;

        public Node() {
        }

        public Node(int value) {
            this.value = value;
        }
    }


    /*
     * @author gengzhihao
     * @date 2023/7/19 9:16
     * @description 题目1
     * @param root
     **/
    public static void qs1_process1(Node root){
        if (root == null){
            return;
        }

        Node cur = null;
        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()){
            cur = queue.poll();
            System.out.print(" " + cur.value);
            if (cur.left != null){
                queue.offer(cur.left);
            }
            if (cur.right != null){
                queue.offer(cur.right);
            }
        }
    }


    public static void main(String[] args) {
        Node n1 = new Node(1);
        Node n2 = new Node(2);
        Node n3 = new Node(3);
        Node n4 = new Node(4);
        Node n5 = new Node(5);
        Node n6 = new Node(6);
        n1.left = n2;
        n1.right = n3;
        n2.left = n4;
        n2.right = n5;
        n3.left = n6;

        qs1_process1(n1);

    }
}
