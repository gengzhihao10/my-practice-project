package com.c.mashibing.suanfa.tixike;

import java.util.ArrayList;

/*
 题目1，给定一个二叉树节点，要求找到其最大的为搜索二叉树的子树的节点个数
 */
public class Code13_7找到最大的子树是搜索二叉树1 {

    //二叉树节点
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
     * @date 2023/7/29 11:11
     * @description 题目1
     * @param root
     * @return int
     **/
    public static Node qs1_process1(Node root){
        if (root == null){
            return null;
        }
        return process1(root).maxSubBSTHead;
    }

    private static Info process1(Node root){
        if (root == null){
            return null;
        }

        Info leftInfo = process1(root.left);
        Info rightInfo = process1(root.right);

        int max = root.value;
        int min = root.value;
        boolean isBST = true;
        int maxBSTNodes = 0;
        Node maxSubBSTHead = root;

        if (leftInfo != null){
            max = Math.max(max,leftInfo.max);
            min = Math.min(min,leftInfo.min);
            isBST = isBST && leftInfo.isBST;
            if (leftInfo.max >= root.value){
                isBST = false;
            }
            maxBSTNodes += leftInfo.isBST ? leftInfo.maxBSTNodes : 0;
        }
        if (rightInfo != null){
            max = Math.max(max,rightInfo.max);
            min = Math.min(min,rightInfo.min);
            isBST = isBST && rightInfo.isBST;
            if (rightInfo.min <= root.value){
                isBST = false;
            }
            maxBSTNodes += rightInfo.isBST ? rightInfo.maxBSTNodes : 0;
        }

        //如果是BST，那么前面已经加好了左右孩子的节点，再加上自己本身的节点数就可以
        if (isBST){
            maxBSTNodes += 1;
            maxSubBSTHead = root;
        }
        //如果不是BST，那么需要在左右孩子中选一个最大的
        else {
            if (leftInfo != null){
                maxBSTNodes = leftInfo.maxBSTNodes;
                maxSubBSTHead = leftInfo.maxSubBSTHead;
            }
            if (rightInfo != null){
                if (maxBSTNodes <= rightInfo.maxBSTNodes){
                    maxBSTNodes = rightInfo.maxBSTNodes;
                    maxSubBSTHead = rightInfo.maxSubBSTHead;
                }
            }
        }

        return new Info(max,min,isBST,maxBSTNodes,maxSubBSTHead);
    }

    private static class Info{
        int max;
        int min;
        boolean isBST;
        int maxBSTNodes;
        Node maxSubBSTHead;

        public Info() {
        }

        public Info(int max, int min, boolean isBST, int maxBSTNodes,Node maxSubBSTHead) {
            this.max = max;
            this.min = min;
            this.isBST = isBST;
            this.maxBSTNodes = maxBSTNodes;
            this.maxSubBSTHead = maxSubBSTHead;
        }
    }


    public static void main(String[] args) {
        int maxLevel = 4;
        int maxValue = 100;
        int testTimes = 1000000;
        for (int i = 0; i < testTimes; i++) {
            Node head = generateRandomBST(maxLevel, maxValue);
            Node res1 = maxSubBSTHead1(head);
            Node res2 = qs1_process1(head);
            if (res1 != res2) {
                System.out.println("Oops!");
                System.out.println("树为：");
                printTree(head);
                System.out.println("res1为");
                printTree(res1);
                System.out.println("res2为");
                printTree(res2);
                break;
            }
        }
        System.out.println("finish!");
    }

    public static Node maxSubBSTHead1(Node head) {
        if (head == null) {
            return null;
        }
        if (getBSTSize(head) != 0) {
            return head;
        }
        Node leftAns = maxSubBSTHead1(head.left);
        Node rightAns = maxSubBSTHead1(head.right);
        return getBSTSize(leftAns) >= getBSTSize(rightAns) ? leftAns : rightAns;
    }

    public static int getBSTSize(Node head) {
        if (head == null) {
            return 0;
        }
        ArrayList<Node> arr = new ArrayList<>();
        in(head, arr);
        for (int i = 1; i < arr.size(); i++) {
            if (arr.get(i).value <= arr.get(i - 1).value) {
                return 0;
            }
        }
        return arr.size();
    }

    public static void in(Node head, ArrayList<Node> arr) {
        if (head == null) {
            return;
        }
        in(head.left, arr);
        arr.add(head);
        in(head.right, arr);
    }

    // for test
    public static Node generateRandomBST(int maxLevel, int maxValue) {
        return generate(1, maxLevel, maxValue);
    }

    // for test
    public static Node generate(int level, int maxLevel, int maxValue) {
        if (level > maxLevel || Math.random() < 0.5) {
            return null;
        }
        Node head = new Node((int) (Math.random() * maxValue));
        head.left = generate(level + 1, maxLevel, maxValue);
        head.right = generate(level + 1, maxLevel, maxValue);
        return head;
    }



    public static void printTree(Node head) {
        System.out.println("Binary Tree:");
        printInOrder(head, 0, "H", 17);
        System.out.println();
    }

    public static void printInOrder(Node head, int height, String to, int len) {
        if (head == null) {
            return;
        }
        printInOrder(head.right, height + 1, "v", len);
        String val = to + head.value + to;
        int lenM = val.length();
        int lenL = (len - lenM) / 2;
        int lenR = len - lenM - lenL;
        val = getSpace(lenL) + val + getSpace(lenR);
        System.out.println(getSpace(height * len) + val);
        printInOrder(head.left, height + 1, "^", len);
    }

    public static String getSpace(int num) {
        String space = " ";
        StringBuffer buf = new StringBuffer("");
        for (int i = 0; i < num; i++) {
            buf.append(space);
        }
        return buf.toString();
    }
}
