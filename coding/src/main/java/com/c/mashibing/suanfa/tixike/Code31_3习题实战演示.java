package com.c.mashibing.suanfa.tixike;

/*
 题目1，基于morris遍历实现后序遍历
 题目2，基于morris遍历判断是否为搜索二叉树
 */
public class Code31_3习题实战演示 {


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
     * @date 2023/12/7 11:44
     * @description 题目1
     * @param head
     **/
    public static void morrisPos(Node head){
        if (head == null){
            return;
        }

        Node cur = head;
        Node mostRight = null;

        while (cur != null){
            mostRight = cur.left;
            if (mostRight != null){
                while (mostRight.right != null && mostRight.right != cur){
                    mostRight = mostRight.right;
                }

                if (mostRight.right == null){
                    mostRight.right = cur;
                    cur = cur.left;
                    continue;
                }
                else {
                    mostRight.right = null;
                    //逆序打印左子树的右边界
                    printRightEdgeByReversed(cur.left);
                }
            }
            cur = cur.right;
        }
        printRightEdgeByReversed(cur);
    }

    //逆序打印root节点的右边界
    private static void printRightEdgeByReversed(Node root) {
        Node newHead = reverseRightEdge(root);
        Node cur = newHead;
        while (cur != null){
            System.out.print(cur.value + " ");
            cur = cur.right;
        }
        reverseRightEdge(newHead);
    }

    //反转
    private static Node reverseRightEdge(Node root) {
        Node pre = null;
        Node cur = root;
        Node next = null;
        while (cur != null){
            next = cur.right;
            cur.right = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }

    /*
     * @author gengzhihao
     * @date 2023/12/7 11:44
     * @description 题目2
     * @param head
     * @return boolean
     **/
    public static boolean isBST(Node head){
        if (head == null){
            return false;
        }

        Node cur = head;
        Node mostRight = null;
        Node pre = null;
        boolean ans = true;

        while (cur != null){
            mostRight = cur.left;
            if (mostRight != null){
                while (mostRight.right != null && mostRight.right != cur){
                    mostRight = mostRight.right;
                }

                if (mostRight.right == null){
                    mostRight.right = cur;
                    cur = cur.left;
                    continue;
                }
                else {
                    mostRight.right = null;
                }
            }
            if (pre != null && pre.value >= cur.value){
                ans =false;
            }
            pre = cur;
            cur = cur.right;
        }
        return ans;
    }
}
