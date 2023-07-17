package com.c.mashibing.suanfa.tixike;

import java.util.Stack;

/*
 题目1，非递归，实现二叉树的中序遍历
 */
public class Code11_7非递归实现二叉树的先序中序后续遍历2 {

    //二叉树
    static class Node{
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
            return "value " + value ;
        }
    }

    /*
     * @author gengzhihao
     * @date 2023/7/17 11:35
     * @description 题目1
     * @param root
     **/
    public static void qs1_process1(Node root){
        if (root == null){
            return;
        }

        Node cur = root;
        Stack<Node> stack = new Stack<>();

        while (cur != null || !stack.isEmpty()){
            //1。将左边界都塞入栈中
            while (cur != null){
                stack.push(cur);
                if (cur.left == null){
                    break;
                }
                cur = cur.left;
            }
            //2.弹出节点并打印
            cur = stack.pop();
            System.out.print(cur.value + "  ");
            //3.将cur的右孩子设置为右节点
            cur = cur.right;

        }

    }

    public static void main(String[] args) {
        Node head = new Node(1);
        head.left = new Node(2);
        head.right = new Node(3);
        head.left.left = new Node(4);
        head.left.right = new Node(5);
        head.right.left = new Node(6);
        head.right.right = new Node(7);

        //4  2  5  1  6  3  7
        qs1_process1(head);
        System.out.println();
        System.out.println("========");


    }
}
