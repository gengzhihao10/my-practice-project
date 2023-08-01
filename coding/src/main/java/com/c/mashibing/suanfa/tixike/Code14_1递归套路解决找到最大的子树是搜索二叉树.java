package com.c.mashibing.suanfa.tixike;


import java.util.ArrayList;
import java.util.LinkedList;

/*
 题目1，使用递归套路，判断二叉树是不是完全二叉树
 题目2，返回二叉树的最大的搜索二叉树的子树的节点个数
 题目3，返回二叉树的最大的搜索二叉树的子树的头节点
 */
public class Code14_1递归套路解决找到最大的子树是搜索二叉树 {

    //二叉树的节点
    private static class Node{
        int value;
        Node left;
        Node right;

        public Node() {
        }

        public Node(int value) {
            this.value = value;
        }

        public Node(int value, Node left, Node right) {
            this.value = value;
            this.left = left;
            this.right = right;
        }

        @Override
        public String toString() {
            return String.valueOf(value);
        }
    }

    /*
     * @author gengzhihao
     * @date 2023/8/1 8:48
     * @description 题目1
     * @param root
     * @return boolean
     **/
    public static boolean qs1_process1(Node root){
        if (root == null){
            return true;
        }

        return process1(root).isCBT;
    }

    private static Info1 process1(Node root){
        if (root == null){
            return new Info1(true,true,0);
        }

        Info1 leftInfo = process1(root.left);
        Info1 rightInfo = process1(root.right);

        int height = Math.max(leftInfo.height,rightInfo.height) + 1;
        boolean isFull = leftInfo.isFull && rightInfo.isFull && (leftInfo.height == rightInfo.height);

        boolean isCBT = false;
        if (leftInfo.isFull && rightInfo.isFull && leftInfo.height == rightInfo.height){
            isCBT = true;
        }
        else if (leftInfo.isCBT && rightInfo.isFull && (leftInfo.height == rightInfo.height + 1)){
            isCBT = true;
        }
        else if (leftInfo.isFull && rightInfo.isFull && leftInfo.height == rightInfo.height + 1){
            isCBT = true;
        }
        else if (leftInfo.isFull && rightInfo.isCBT && leftInfo.height == rightInfo.height){
            isCBT = true;
        }
        return new Info1(isFull,isCBT,height);
    }

    private static class Info1{
        boolean isFull;
        boolean isCBT;
        int height;

        public Info1() {
        }

        public Info1(boolean isFull, boolean isCBT, int height) {
            this.isFull = isFull;
            this.isCBT = isCBT;
            this.height = height;
        }
    }

//    public static void main(String[] args) {
//        int maxLevel = 5;
//        int maxValue = 100;
//        int testTimes = 1000000;
//        for (int i = 0; i < testTimes; i++) {
//            Node head = generateRandomBST(maxLevel, maxValue);
//            if (isCompleteTree1(head) != qs1_process1(head)) {
//                System.out.println("Oops!");
//            }
//        }
//        System.out.println("finish!");
//    }
//
//
//    // for test
//    public static Node generateRandomBST(int maxLevel, int maxValue) {
//        return generate(1, maxLevel, maxValue);
//    }
//
//    // for test
//    public static Node generate(int level, int maxLevel, int maxValue) {
//        if (level > maxLevel || Math.random() < 0.5) {
//            return null;
//        }
//        Node head = new Node((int) (Math.random() * maxValue));
//        head.left = generate(level + 1, maxLevel, maxValue);
//        head.right = generate(level + 1, maxLevel, maxValue);
//        return head;
//    }



    public static boolean isCompleteTree1(Node head) {
        if (head == null) {
            return true;
        }
        LinkedList<Node> queue = new LinkedList<>();
        // 是否遇到过左右两个孩子不双全的节点
        boolean leaf = false;
        Node l = null;
        Node r = null;
        queue.add(head);
        while (!queue.isEmpty()) {
            head = queue.poll();
            l = head.left;
            r = head.right;
            if (
                // 如果遇到了不双全的节点之后，又发现当前节点不是叶节点
                    (leaf && (l != null || r != null)) || (l == null && r != null)

            ) {
                return false;
            }
            if (l != null) {
                queue.add(l);
            }
            if (r != null) {
                queue.add(r);
            }
            if (l == null || r == null) {
                leaf = true;
            }
        }
        return true;
    }
//****************************************************************************************************


    /*
     * @author gengzhihao
     * @date 2023/8/1 10:13
     * @description 题目2
     * @param root
     * @return int
     **/
    public static int qs2_process1(Node root){
        if (root == null){
            return 0;
        }

        return process2(root).maxSubBSTNodesNum;
    }

    private static Info2 process2(Node root){
        if (root == null){
            return null;
        }

        Info2 leftInfo = process2(root.left);
        Info2 rightInfo = process2(root.right);


        int max = root.value;
        int min = root.value;
        int nodesNum = 1;
        int maxSubBSTNodesNum = Math.max(leftInfo == null ? 0 : leftInfo.maxSubBSTNodesNum,
                rightInfo == null ? 0 : rightInfo.maxSubBSTNodesNum);

        if (leftInfo != null){
            max = Math.max(max,leftInfo.max);
            min = Math.min(min,leftInfo.min);
            nodesNum += leftInfo.nodesNum;
        }

        if (rightInfo != null){
            max = Math.max(max, rightInfo.max);
            min = Math.min(min, rightInfo.min);
            nodesNum += rightInfo.nodesNum;
        }

        if (leftInfo == null ? true : leftInfo.maxSubBSTNodesNum == leftInfo.nodesNum
        && rightInfo == null ? true : rightInfo.maxSubBSTNodesNum == rightInfo.nodesNum
        && (leftInfo == null ? true : leftInfo.max < root.value) && (rightInfo == null ? true : rightInfo.min > root.value)){
            maxSubBSTNodesNum = (leftInfo == null ? 0 : leftInfo.nodesNum)
                    + (rightInfo == null ? 0 : rightInfo.nodesNum)
                    + 1;
        }

        return new Info2(max,min,nodesNum,maxSubBSTNodesNum);
    }

    private static class Info2{
        int max;
        int min;
        int nodesNum;
        int maxSubBSTNodesNum;

        public Info2() {
        }

        public Info2(int max, int min, int nodesNum, int maxSubBSTNodesNum) {
            this.max = max;
            this.min = min;
            this.nodesNum = nodesNum;
            this.maxSubBSTNodesNum = maxSubBSTNodesNum;
        }
    }


//***********************************************************************************

    /*
     * @author gengzhihao
     * @date 2023/8/1 10:31
     * @description 题目3
     * @param root
     * @return Node
     **/
    public static Node qs3_process1(Node root){
        if (root == null){
            return null;
        }

        return process3(root).maxSubBSTHead;
    }

    private static Info3 process3(Node root){
        if (root == null){
            return null;
        }

        Info3 leftInfo = process3(root.left);
        Info3 rightInfo = process3(root.right);

        int max = root.value;
        int min = root.value;
        Node maxSubBSTHead = null;
        int maxSubBSTNodesNum = 0;

        if (leftInfo != null){
            max = Math.max(max, leftInfo.max);
            min = Math.min(min, leftInfo.min);

            maxSubBSTHead = leftInfo.maxSubBSTHead;
            maxSubBSTNodesNum = leftInfo.maxSubBSTNodesNum;
        }

        if (rightInfo != null){
            max = Math.max(max,rightInfo.max);
            min = Math.min(min, rightInfo.min);
            if (rightInfo.maxSubBSTNodesNum > maxSubBSTNodesNum){
                maxSubBSTNodesNum = rightInfo.maxSubBSTNodesNum;
                maxSubBSTHead = rightInfo.maxSubBSTHead;
            }
        }

        if ((leftInfo == null ? true : leftInfo.maxSubBSTHead == root.left)
        && (rightInfo == null ? true : rightInfo.maxSubBSTHead == root.right)
        && ((leftInfo == null ? true : leftInfo.max < root.value) && (rightInfo == null ? true : rightInfo.min > root.value))){
            maxSubBSTHead = root;
            maxSubBSTNodesNum = 1
                    +(leftInfo == null ? 0 : leftInfo.maxSubBSTNodesNum)
                    +(rightInfo == null ? 0 : rightInfo.maxSubBSTNodesNum);
        }
        return new Info3(max,min,maxSubBSTHead,maxSubBSTNodesNum);
    }

    private static class Info3{
        int max;
        int min;
        Node maxSubBSTHead;
        int maxSubBSTNodesNum;

        public Info3() {
        }

        public Info3(int max, int min, Node maxSubBSTHead, int maxSubBSTNodesNum) {
            this.max = max;
            this.min = min;
            this.maxSubBSTHead = maxSubBSTHead;
            this.maxSubBSTNodesNum = maxSubBSTNodesNum;
        }
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

    public static void main(String[] args) {
        int maxLevel = 4;
        int maxValue = 100;
        int testTimes = 1000000;
        for (int i = 0; i < testTimes; i++) {
            Node head = generateRandomBST(maxLevel, maxValue);
//            Node head = new Node(62);
//            head.left = new Node(80);
//            head.left.right = new Node(64);
//            head.left.right.left = new Node(81);
//            System.out.println("输入树");
//            printTree(head);
            Node res1 = maxSubBSTHead1(head);
            Node res2 = qs3_process1(head);
            if (res1 != res2) {
                System.out.println("Oops!");
//                System.out.println("输入树");
//                printTree(head);
                System.out.println("输出树1");
                printTree(res1);
                System.out.println("输出树2");
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
