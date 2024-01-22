package com.c.mashibing.suanfa.tixike;

import java.util.ArrayList;

/*
todo
 题目1，
 设计一个结构包含如下两个方法：
 void add(int index, int num)：把num加入到index位置
 int get(int index) ：取出index位置的值
 void remove(int index) ：把index位置上的值删除
 要求三个方法时间复杂度O(logN)

 */
public class Code38_8AddRemoveGetIndexGreet代码 {

    public static class SBTNode<V>{


        public SBTNode(V v){

        }
    }


    public static class SbtList<V>{


        private SBTNode<V> rightRotate(SBTNode<V> cur){

        }

        private SBTNode<V> leftRotate(SBTNode<V> cur){

        }

        private SBTNode<V> maintain(SBTNode<V> cur){

        }

        public void add(int index, V num){

        }

        private SBTNode<V> add(SBTNode<V> root, int index, SBTNode<V> cur){

        }

        public void remove(int index){

        }

        private SBTNode<V> remove(SBTNode<V> root, int index){

        }


        public V get(int index){

        }

        private SBTNode<V> get(SBTNode<V> root, int index){

        }

        public int size(){

        }
    }


    // 通过以下这个测试，
    // 可以很明显的看到LinkedList的插入、删除、get效率不如SbtList
    // LinkedList需要找到index所在的位置之后才能插入或者读取，时间复杂度O(N)
    // SbtList是平衡搜索二叉树，所以插入或者读取时间复杂度都是O(logN)
    public static void main(String[] args) {
        // 功能测试
        int test = 50000;
        int max = 1000000;
        boolean pass = true;
        ArrayList<Integer> list = new ArrayList<>();
        SbtList<Integer> sbtList = new SbtList<>();
        for (int i = 0; i < test; i++) {
            if (list.size() != sbtList.size()) {
                pass = false;
                break;
            }
            if (list.size() > 1 && Math.random() < 0.5) {
                int removeIndex = (int) (Math.random() * list.size());
                list.remove(removeIndex);
                sbtList.remove(removeIndex);
            } else {
                int randomIndex = (int) (Math.random() * (list.size() + 1));
                int randomValue = (int) (Math.random() * (max + 1));
                list.add(randomIndex, randomValue);
                sbtList.add(randomIndex, randomValue);
            }
        }
        for (int i = 0; i < list.size(); i++) {
            if (!list.get(i).equals(sbtList.get(i))) {
                pass = false;
                break;
            }
        }
        System.out.println("功能测试是否通过 : " + pass);

        // 性能测试
        test = 500000;
        list = new ArrayList<>();
        sbtList = new SbtList<>();
        long start = 0;
        long end = 0;

        start = System.currentTimeMillis();
        for (int i = 0; i < test; i++) {
            int randomIndex = (int) (Math.random() * (list.size() + 1));
            int randomValue = (int) (Math.random() * (max + 1));
            list.add(randomIndex, randomValue);
        }
        end = System.currentTimeMillis();
        System.out.println("ArrayList插入总时长(毫秒) ： " + (end - start));

        start = System.currentTimeMillis();
        for (int i = 0; i < test; i++) {
            int randomIndex = (int) (Math.random() * (i + 1));
            list.get(randomIndex);
        }
        end = System.currentTimeMillis();
        System.out.println("ArrayList读取总时长(毫秒) : " + (end - start));

        start = System.currentTimeMillis();
        for (int i = 0; i < test; i++) {
            int randomIndex = (int) (Math.random() * list.size());
            list.remove(randomIndex);
        }
        end = System.currentTimeMillis();
        System.out.println("ArrayList删除总时长(毫秒) : " + (end - start));

        start = System.currentTimeMillis();
        for (int i = 0; i < test; i++) {
            int randomIndex = (int) (Math.random() * (sbtList.size() + 1));
            int randomValue = (int) (Math.random() * (max + 1));
            sbtList.add(randomIndex, randomValue);
        }
        end = System.currentTimeMillis();
        System.out.println("SbtList插入总时长(毫秒) : " + (end - start));

        start = System.currentTimeMillis();
        for (int i = 0; i < test; i++) {
            int randomIndex = (int) (Math.random() * (i + 1));
            sbtList.get(randomIndex);
        }
        end = System.currentTimeMillis();
        System.out.println("SbtList读取总时长(毫秒) :  " + (end - start));

        start = System.currentTimeMillis();
        for (int i = 0; i < test; i++) {
            int randomIndex = (int) (Math.random() * sbtList.size());
            sbtList.remove(randomIndex);
        }
        end = System.currentTimeMillis();
        System.out.println("SbtList删除总时长(毫秒) :  " + (end - start));

    }
}
