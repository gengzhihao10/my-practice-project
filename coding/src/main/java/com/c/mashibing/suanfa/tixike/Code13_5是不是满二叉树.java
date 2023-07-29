package com.c.mashibing.suanfa.tixike;

import java.util.LinkedList;

/*
 题目1，判断一棵树是不是满二叉树
 */
public class Code13_5是不是满二叉树 {


    //二叉树节点
    private static class TreeNode {
        int value;
        TreeNode left;
        TreeNode right;

        public TreeNode() {
        }

        public TreeNode(int value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return String.valueOf(value);
        }
    }


    /*
     * @author gengzhihao
     * @date 2023/7/29 10:55
     * @description 题目1
     * @param root
     * @return boolean
     **/
    public static boolean qs1_process1(TreeNode root){
        if (root == null){
            return true;
        }

        return process(root);
    }

    private static boolean process(TreeNode root){
        if (root == null){
            return true;
        }

        boolean leftInfo = process(root.left);
        boolean rightInfo = process(root.right);

        boolean isFBT = leftInfo && rightInfo && (root.left == null) == (root.right == null);

        return isFBT;
    }

    private static class Info{
        boolean isFBT;
//        int nodeNum;
        int height;

        public Info() {
        }

        public Info(boolean isFBT, int height) {
            this.isFBT = isFBT;
//            this.nodeNum = nodeNum;
            this.height = height;
        }
    }

//    public static void main(String[] args) {
//        int maxLevel = 5;
//        int maxValue = 100;
//        int testTimes = 1000000;
//        for (int i = 0; i < testTimes; i++) {
//            TreeNode head = generateRandomBST(maxLevel, maxValue);
//            boolean isCompleteTree1 = isCompleteTree1(head);
//            boolean qs1_process1 = qs1_process1(head);
//            if (isCompleteTree1 != qs1_process1) {
//                System.out.println("Oops!");
//                printTree(head);
//                System.out.println("暴力方法："+isCompleteTree1);
//                System.out.println("我的方法："+qs1_process1);
//                break;
//            }
//        }
//        System.out.println("finish!");
//    }
//
//    public static TreeNode generateRandomBST(int maxLevel, int maxValue) {
//        return generate(1, maxLevel, maxValue);
//    }
//
//    public static TreeNode generate(int level, int maxLevel, int maxValue) {
//        if (level > maxLevel || Math.random() < 0.5) {
//            return null;
//        }
//        TreeNode head = new TreeNode((int) (Math.random() * maxValue));
//        head.left = generate(level + 1, maxLevel, maxValue);
//        head.right = generate(level + 1, maxLevel, maxValue);
//        return head;
//    }
//
//    public static boolean isCompleteTree1(TreeNode head) {
//        if (head == null) {
//            return true;
//        }
//        LinkedList<TreeNode> queue = new LinkedList<>();
//        // 是否遇到过左右两个孩子不双全的节点
//        boolean leaf = false;
//        TreeNode l = null;
//        TreeNode r = null;
//        queue.add(head);
//        while (!queue.isEmpty()) {
//            head = queue.poll();
//            l = head.left;
//            r = head.right;
//            if (
//                // 如果遇到了不双全的节点之后，又发现当前节点不是叶节点
//                    (leaf && (l != null || r != null)) || (l == null && r != null)
//
//            ) {
//                return false;
//            }
//            if (l != null) {
//                queue.add(l);
//            }
//            if (r != null) {
//                queue.add(r);
//            }
//            if (l == null || r == null) {
//                leaf = true;
//            }
//        }
//        return true;
//    }
//
//    public static void printTree(TreeNode head) {
//        System.out.println("Binary Tree:");
//        printInOrder(head, 0, "H", 17);
//        System.out.println();
//    }
//
//    public static void printInOrder(TreeNode head, int height, String to, int len) {
//        if (head == null) {
//            return;
//        }
//        printInOrder(head.right, height + 1, "v", len);
//        String val = to + head.value + to;
//        int lenM = val.length();
//        int lenL = (len - lenM) / 2;
//        int lenR = len - lenM - lenL;
//        val = getSpace(lenL) + val + getSpace(lenR);
//        System.out.println(getSpace(height * len) + val);
//        printInOrder(head.left, height + 1, "^", len);
//    }
//
//    public static String getSpace(int num) {
//        String space = " ";
//        StringBuffer buf = new StringBuffer("");
//        for (int i = 0; i < num; i++) {
//            buf.append(space);
//        }
//        return buf.toString();
//    }
}
