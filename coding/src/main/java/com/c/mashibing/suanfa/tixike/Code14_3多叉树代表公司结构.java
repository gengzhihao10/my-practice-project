package com.c.mashibing.suanfa.tixike;

import java.util.ArrayList;
import java.util.List;

/*
 题目1，派对的最大快乐值，
 现有一个派对要邀请公司员工，邀请了一个员工，那么他的直接上下级就不能来了，要求排队的最大快乐值
    员工信息的定义如下:
    class Employee {
        public int happy; // 这名员工可以带来的快乐值
        List<Employee> nexts; // 这名员工有哪些直接下级
    }

 */
public class Code14_3多叉树代表公司结构 {

    private static class Employee {
        int happy;
        List<Employee> nexts;

        public Employee() {
        }

        public Employee(int happy) {
            this.happy = happy;
            nexts = new ArrayList<>();
        }

        public Employee(int happy, List<Employee> nexts) {
            this.happy = happy;
            this.nexts = nexts;
        }
    }

    public static int qs1_process1(Employee root){
        if (root == null){
            return 0;
        }
        Info info = process1(root);
        return Math.max(info.yes,info.no);
    }

    private static Info process1(Employee root){
        if (root == null){
            return new Info(0,0);
        }
        int yes = root.happy;
        int no = 0;
        Info info;
        for (Employee employee : root.nexts){
            info = process1(employee);
            yes += info.no;
            no += Math.max(info.yes,info.no);
        }
        return new Info(yes,no);
    }

    private static class Info{
        int yes;
        int no;

        public Info() {
        }

        public Info(int yes, int no) {
            this.yes = yes;
            this.no = no;
        }
    }


    // for test
    public static Employee genarateBoss(int maxLevel, int maxNexts, int maxHappy) {
        if (Math.random() < 0.02) {
            return null;
        }
        Employee boss = new Employee((int) (Math.random() * (maxHappy + 1)));
        genarateNexts(boss, 1, maxLevel, maxNexts, maxHappy);
        return boss;
    }

    // for test
    public static void genarateNexts(Employee e, int level, int maxLevel, int maxNexts, int maxHappy) {
        if (level > maxLevel) {
            return;
        }
        int nextsSize = (int) (Math.random() * (maxNexts + 1));
        for (int i = 0; i < nextsSize; i++) {
            Employee next = new Employee((int) (Math.random() * (maxHappy + 1)));
            e.nexts.add(next);
            genarateNexts(next, level + 1, maxLevel, maxNexts, maxHappy);
        }
    }

    public static void main(String[] args) {
        int maxLevel = 4;
        int maxNexts = 7;
        int maxHappy = 100;
        int testTimes = 100000;
        for (int i = 0; i < testTimes; i++) {
            Employee boss = genarateBoss(maxLevel, maxNexts, maxHappy);
            if (maxHappy1(boss) != qs1_process1(boss)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("finish!");
    }


    public static int maxHappy1(Employee boss) {
        if (boss == null) {
            return 0;
        }
        return process1(boss, false);
    }

    // 当前来到的节点叫cur，
    // up表示cur的上级是否来，
    // 该函数含义：
    // 如果up为true，表示在cur上级已经确定来，的情况下，cur整棵树能够提供最大的快乐值是多少？
    // 如果up为false，表示在cur上级已经确定不来，的情况下，cur整棵树能够提供最大的快乐值是多少？
    public static int process1(Employee cur, boolean up) {
        if (up) { // 如果cur的上级来的话，cur没得选，只能不来
            int ans = 0;
            for (Employee next : cur.nexts) {
                ans += process1(next, false);
            }
            return ans;
        } else { // 如果cur的上级不来的话，cur可以选，可以来也可以不来
            int p1 = cur.happy;
            int p2 = 0;
            for (Employee next : cur.nexts) {
                p1 += process1(next, true);
                p2 += process1(next, false);
            }
            return Math.max(p1, p2);
        }
    }

}
