package com.c.mashibing.suanfa.tixike;

/*
 题目1，递归，实现二叉树的先序遍历
 题目2，递归，实现二叉树的中序遍历
 题目3，递归，实现二叉树的后序遍历

 */
public class Code11_4二叉树的先序中序后续遍历 {

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
     * @date 2023/7/17 9:36
     * @description 题目1
     * @param root
     **/
    public static void qs1_process1(Node root){
        //递归中止条件
        if (root == null){
            return;
        }

        System.out.print(root.value + "  ");
        qs1_process1(root.left);
        qs1_process1(root.right);
    }

    /*
     * @author gengzhihao
     * @date 2023/7/17 9:44
     * @description 题目2
     * @param root
     **/
    public static void qs2_process1(Node root){
        //递归中止条件
        if (root == null){
            return;
        }

        qs2_process1(root.left);
        System.out.print(root.value + "  ");
        qs2_process1(root.right);
    }

    /*
     * @author gengzhihao
     * @date 2023/7/17 9:44
     * @description 题目3
     * @param root
     **/
    public static void qs3_process1(Node root){
        //递归中止条件
        if (root == null){
            return;
        }

        qs3_process1(root.left);
        qs3_process1(root.right);
        System.out.print(root.value + "  ");

    }


    public static void main(String[] args) {
        Node head = new Node(1);
        head.left = new Node(2);
        head.right = new Node(3);
        head.left.left = new Node(4);
        head.left.right = new Node(5);
        head.right.left = new Node(6);
        head.right.right = new Node(7);

//        qs1_process1(head);
//        System.out.println();
//        System.out.println("========");

        qs2_process1(head);
        System.out.println();
        System.out.println("========");

//        qs3_process1(head);
//        System.out.println();
//        System.out.println("========");
    }
}
