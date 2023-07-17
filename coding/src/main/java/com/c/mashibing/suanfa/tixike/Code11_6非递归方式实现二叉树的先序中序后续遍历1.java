package com.c.mashibing.suanfa.tixike;

import java.util.Stack;

/*
 题目1，非递归，实现二叉树的先序遍历
 题目2，非递归，实现二叉树的后续遍历
 */
public class Code11_6非递归方式实现二叉树的先序中序后续遍历1 {

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
    }

    /*
     * @author gengzhihao
     * @date 2023/7/17 10:21
     * @description 题目1
     * @param head
     **/
    public static void qs1_process1(Node root){
        if (root == null){
            return;
        }

        Node cur = root;
        System.out.print(cur.value + "  ");
        Stack<Node> stack = new Stack<>();
        while (!stack.isEmpty() || cur != null){
            if (cur != null){
                stack.push(cur.right);
                stack.push(cur.left);
            }
            if (!stack.isEmpty()){
                cur = stack.pop();
                if (cur != null){
                    System.out.print(cur.value + "  ");
                }
            }
        }
    }

    /*
     * @author gengzhihao
     * @date 2023/7/17 11:11
     * @description 题目2
     * @param root
     **/
    public static void qs2_process1(Node root){
        if (root == null){
            return;
        }

        Node cur = root;
        Stack<Node> stack1 = new Stack<>();
        Stack<Node> stack2 = new Stack<>();
        stack2.push(root);

        while (!stack1.isEmpty() || cur != null){
            if (cur != null){
                stack1.push(cur.left);
                stack1.push(cur.right);
            }
            if (!stack1.isEmpty()){
                cur = stack1.pop();
                if (cur != null){
                    stack2.push(cur);
                }
            }
        }

        while (!stack2.isEmpty()){
            cur = stack2.pop();
            if (cur != null){
                System.out.print(cur.value + "  ");
            }
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

        //1  2  4  5  3  6  7
        qs1_process1(head);
        System.out.println();
        System.out.println("========");

        //4  5  2  6  7  3  1
        qs2_process1(head);
        System.out.println();
        System.out.println("========");
//
//        qs3_process1(head);
//        System.out.println();
//        System.out.println("========");
    }
}
