package com.c.mashibing.suanfa.tixike;

/*
 题目1，实现morris遍历.
 将额外空间复杂度从logN降低为1
 题目2，基于morris遍历的前序
 题目3，基于morris遍历的中序
 */
public class Code31_2课堂答疑复杂度 {


    public static class Node {
        public int value;
        Node left;
        Node right;

        public Node(int data) {
            this.value = data;
        }
    }


    /*
     * @author gengzhihao
     * @date 2023/12/7 10:15
     * @description 题目1，morris遍历
     * @param head
     **/
    public static void morris(Node head){
        if (head == null){
            return;
        }

        Node cur = head;
        Node mostRight = null;
        while (cur != null){
            mostRight = cur.left;
            //cur有左孩子
            if (mostRight != null){
                //找到cur左子树的最右孩子
                while (mostRight.right != null && mostRight.right != cur){
                    mostRight = mostRight.right;
                }
                //第一次来到mostRight，此时right指针还没有被修改
                if (mostRight.right == null){
                    mostRight.right = cur;
                    cur = cur.left;
                    continue;
                }
                //第二次来到mostRight，此时right指针已经被修改到指向cur
                else{
                    mostRight.right = null;
                }
            }
            //cur向右移动
            cur = cur.right;
        }

    }

    /*
     * @author gengzhihao
     * @date 2023/12/7 10:15
     * @description 题目2
     * @param head
     **/
    public static void morrisPre(Node head){
        if (head == null){
            return;
        }

        Node cur = head;
        Node mostRight = null;
        while (cur != null){
            mostRight = cur.left;
            //cur有左孩子
            if (mostRight != null){
                //找到cur左子树的最右孩子
                while (mostRight.right != null && mostRight.right != cur){
                    mostRight = mostRight.right;
                }
                //第一次来到mostRight，此时right指针还没有被修改
                if (mostRight.right == null){
                    System.out.print(cur.value + " ");
                    mostRight.right = cur;
                    cur = cur.left;
                    continue;
                }
                //第二次来到mostRight，此时right指针已经被修改到指向cur
                else{
                    mostRight.right = null;
                }
            }
            else {
                System.out.print(cur.value + " ");
            }
            //cur向右移动
            cur = cur.right;
        }
        System.out.println();
    }

    /*
     * @author gengzhihao
     * @date 2023/12/7 10:15
     * @description 题目3
     * @param head
     **/
    public static void morrisIn(Node head){
        if (head == null){
            return;
        }

        Node cur = head;
        Node mostRight = null;
        while (cur != null){
            mostRight = cur.left;
            //cur有左孩子
            if (mostRight != null){
                //找到cur左子树的最右孩子
                while (mostRight.right != null && mostRight.right != cur){
                    mostRight = mostRight.right;
                }
                //第一次来到mostRight，此时right指针还没有被修改
                if (mostRight.right == null){
                    mostRight.right = cur;
                    cur = cur.left;
                    continue;
                }
                //第二次来到mostRight，此时right指针已经被修改到指向cur
                else{
                    mostRight.right = null;
                }
            }
            System.out.print(cur.value + " ");
            //cur向右移动
            cur = cur.right;
        }
        System.out.println();

    }


    //*****************************************************************************************

    public static void main(String[] args) {
        Node head = new Node(4);
        head.left = new Node(2);
        head.right = new Node(6);
        head.left.left = new Node(1);
        head.left.right = new Node(3);
        head.right.left = new Node(5);
        head.right.right = new Node(7);
        printTree(head);
        System.out.println("中序遍历：");
        morrisIn(head);
        System.out.println("前序遍历：");
        morrisPre(head);
//        morrisPos(head);
        printTree(head);

    }

    // for test -- print tree
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
