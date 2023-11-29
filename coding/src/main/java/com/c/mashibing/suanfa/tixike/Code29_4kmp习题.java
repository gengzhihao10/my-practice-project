package com.c.mashibing.suanfa.tixike;

import org.springframework.util.CollectionUtils;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/*
 题目1，判断2个字符串是否是旋转字符串，如12345 45123是旋转字符串
 题目2，有2棵二叉树，判断其中一棵树是否是另一棵树的子树
 测试链接 : https://leetcode.cn/problems/subtree-of-another-tree/
 */
public class Code29_4kmp习题 {


    /*
     * @author gengzhihao
     * @date 2023/11/28 10:54
     * @description 题目1
     * @param a
     * @param b
     * @return boolean
     **/
    public static boolean isRotation(String a, String b){
        //不接受字符串为空，或者2个字符串长度不相等
        if (a == null || b == null || a.length() == 0 || b.length() == 0 || a.length() != b.length()){
            return false;
        }

        String a2 = a + a;
        return kmp(a2,b) != -1;
    }

    //kmp算法，用于判断s2是否为s1的子串
    private static int kmp(String s1, String s2) {
        char[] c1 = s1.toCharArray();
        char[] c2 = s2.toCharArray();
        //获取b的next信息
        int[] next = getNext(c2);
        int x = 0, y = 0;

        while (x < c1.length && y < c2.length){
            if (c1[x] == c2[y]){
                x++;
                y++;
            }
            else if (y == 0){
                x++;
            }else {
                y = next[y];
            }
        }
        return y >= c2.length ? x - c2.length : -1;
    }

    //获取每个元素之前字符数组中，前子数组和后子数组最长有多少相等，返回长度
    private static int[] getNext(char[] chars) {
        int[] next = new int[chars.length];
        next[0] = -1;
        next[1] = 0;

        int cur = 2;
        int pre = 0;

        while (cur < next.length){
            if (chars[cur-1] == chars[pre]){
                next[cur++] = ++pre;
            }
            else if (pre == 0){
                next[cur++] = 0;
            }else {
                pre = next[pre];
            }
        }

        return next;
    }

    //*************************************************************************

    public static class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;

        public TreeNode(int v) {
            val = v;
        }
    }


    /*
     * @author gengzhihao
     * @date 2023/11/28 10:54
     * @description 题目2
     * @param big
     * @param small
     * @return boolean
     **/
    public static boolean isSubtree(TreeNode big, TreeNode small) {
        if (big == null || small == null){
            return false;
        }

        List<String> bigList = new LinkedList<>();
        List<String> smallList = new LinkedList<>();
        preSerialize(big,bigList);
        preSerialize(small,smallList);
        String[] bigArr = toArray(bigList);
        String[] smallArr = toArray(smallList);
        return kmp2(bigArr,smallArr) != -1;
    }

    private static int kmp2(String[] c1, String[] c2) {
        //获取b的next信息
        int[] next = getNext2(c2);
        int x = 0, y = 0;

        while (x < c1.length && y < c2.length){
//            System.out.print("c1[x]: " + c1[x] + " c2[y]: " + c2[y] + " " );
//            System.out.println("判断结果: " + c1[x] == c2[y]);
//            if ("4".equals(c1[x]) && "4".equals(c2[y])){
//                System.out.println("c1[x] identityHashCode: " + System.identityHashCode(c1[x]));
//                System.out.println("c2[y] identityHashCode: " + System.identityHashCode(c2[y]));
//            }
            if (c1[x].equals(c2[y])){
                x++;
                y++;
            }
            else if (y == 0){
                x++;
            }else {
                y = next[y];
            }
        }
        return y >= c2.length ? x - c2.length : -1;
    }

    //获取每个元素之前字符数组中，前子数组和后子数组最长有多少相等，返回长度
    private static int[] getNext2(String[] strs) {
        int[] next = new int[strs.length];
        next[0] = -1;
        next[1] = 0;

        int cur = 2;
        int pre = 0;

        while (cur < next.length){
            if (strs[cur-1].equals(strs[pre])){
                next[cur++] = ++pre;
            }
            else if (pre == 0){
                next[cur++] = 0;
            }else {
                pre = next[pre];
            }
        }

        return next;
    }

    private static String[] toArray(List<String> list) {
        String[] res = new String[list.size()];
        for (int i = 0; i < res.length; i++){
            res[i] = list.get(i);
        }
        return res;
    }

    private static void preSerialize(TreeNode root,List<String> list) {
        if (root == null){
            list.add("null");
            return;
        }
        list.add(String.valueOf(root.val));
        preSerialize(root.left,list);
        preSerialize(root.right,list);
    }


//    public static void main(String[] args) {
//        String str1 = "yunzuocheng";
//        String str2 = "zuochengyun";
//        System.out.println(isRotation(str1, str2));
//
//    }

    public static void main(String[] args) {
//        TreeNode node1 = new TreeNode(1);
//        TreeNode node2 = new TreeNode(2);
//        TreeNode node3= new TreeNode(3);
//        TreeNode node4 = new TreeNode(4);
//        TreeNode node5 = new TreeNode(5);
//        TreeNode node0 = new TreeNode(0);
//        node3.left = node4;
//        node3.right = node5;
//        node4.left = node1;
//        node4.right = node2;
//        node2.left = node0;
//
//
//        TreeNode node6 = new TreeNode(4);
//        TreeNode node7 = new TreeNode(1);
//        TreeNode node8 = new TreeNode(2);
//        node6.left = node7;
//        node6.right = node8;
//
//        System.out.println(isSubtree(node3,node6));

        TreeNode node1= new TreeNode(1);
        TreeNode node2= new TreeNode(2);
        TreeNode node3= new TreeNode(3);
        TreeNode node4= new TreeNode(4);
        TreeNode node5= new TreeNode(5);
        node3.left = node4;
        node3.right = node5;
        node4.left = node1;
        node4.right = node2;


        TreeNode node6= new TreeNode(4);
        TreeNode node7= new TreeNode(1);
        TreeNode node8= new TreeNode(2);
        node6.left = node7;
        node6.right = node8;

        System.out.println(isSubtree(node3,node6));

    }
}
