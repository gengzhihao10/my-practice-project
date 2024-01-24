package com.c.mashibing.suanfa.tixike;

import java.util.ArrayList;
import java.util.List;

/*
 题目1，
 设计一个结构包含如下两个方法：
 void add(int index, int num)：把num加入到index位置
 int get(int index) ：取出index位置的值
 void remove(int index) ：把index位置上的值删除
 要求三个方法时间复杂度O(logN)

 */
public class Code38_8AddRemoveGetIndexGreet代码 {

    public static class SBTNode<V> {
        SBTNode<V> l;
        SBTNode<V> r;
        V v;
        int size;

        public SBTNode(V v) {
            this.v = v;
            size = 1;
        }
    }


    public static class SbtList<V> {
        private SBTNode<V> root;

        private SBTNode<V> rightRotate(SBTNode<V> cur) {
            SBTNode<V> left = cur.l;
            cur.l = left.r;
            left.r = cur;
            left.size = cur.size;
            cur.size = (cur.l != null ? cur.l.size : 0) + (cur.r != null ? cur.r.size : 0) + 1;
            return left;
        }

        private SBTNode<V> leftRotate(SBTNode<V> cur) {
            SBTNode<V> right = cur.r;
            cur.r = right.l;
            right.l = cur;
            right.size = cur.size;
            cur.size = (cur.l != null ? cur.l.size : 0) + (cur.r != null ? cur.r.size : 0) + 1;
            return right;
        }


        private SBTNode<V> maintain(SBTNode<V> cur) {
            if (cur == null) {
                return null;
            }

            int left = cur.l != null ? cur.l.size : 0;
            int right = cur.r != null ? cur.r.size : 0;
            int leftLeft = cur.l != null && cur.l.l != null ? cur.l.l.size : 0;
            int leftRight = cur.l != null && cur.l.r != null ? cur.l.r.size : 0;
            int rightRight = cur.r != null && cur.r.r != null ? cur.r.r.size : 0;
            int rightLeft = cur.r != null && cur.r.l != null ? cur.r.l.size : 0;
            //ll型违规
            if (leftLeft > right) {
                cur = rightRotate(cur);
                cur.r = maintain(cur.r);
                cur = maintain(cur);
            }
            //rr型违规
             else if (rightRight > left) {
                cur = leftRotate(cur);
                cur.l = maintain(cur.l);
                cur = maintain(cur);
            }
            //lr型违规
            else if (leftRight > right) {
                cur.l = leftRotate(cur.l);
                cur = rightRotate(cur);
                cur.l = maintain(cur.l);
                cur.r = maintain(cur.r);
                cur = maintain(cur);
            }
            //rl型违规
            else if (rightLeft > left) {
                cur.r = rightRotate(cur.r);
                cur = leftRotate(cur);
                cur.l = maintain(cur.l);
                cur.r = maintain(cur.r);
                cur = maintain(cur);
            }
            return cur;
        }

        public void add(int index, V num) {
            SBTNode<V> cur = new SBTNode<>(num);
            if (root == null) {
                root = cur;
            } else {
                if (index >= 0 && index <= size()) {
                    root = add(root, index, cur);
                }
            }
        }

        private SBTNode<V> add(SBTNode<V> root, int index, SBTNode<V> cur) {
            if (root == null) {
                return cur;
            }

            root.size++;
            int leftAndHeadSize = (root.l != null ? root.l.size : 0) + 1;
            if (index < leftAndHeadSize) {
                root.l = add(root.l, index, cur);
            } else {
                root.r = add(root.r, index - leftAndHeadSize, cur);
            }
            root = maintain(root);
            return root;
        }


        public void remove(int index) {
            if (index >= 0 && index < size()) {
                root = remove(root, index);
            }
        }

        private SBTNode<V> remove(SBTNode<V> root, int index) {
            if (root == null) {
                return null;
            } else {
                int leftSize = root.l != null ? root.l.size : 0;
                root.size--;
                if (index < leftSize) {
                    root.l = remove(root.l, index);
                } else if (index > leftSize) {
                    root.r = remove(root.r, index - leftSize - 1);
                } else {
                    if (root.l == null && root.r == null) {
                        root = null;
                    } else if (root.l == null) {
                        root = root.r;
                    } else if (root.r == null) {
                        root = root.l;
                    } else {
                        SBTNode<V> pre = null;
                        SBTNode<V> des = root.r;
                        des.size--;
                        while (des.l != null) {
                            pre = des;
                            des = des.l;
                            des.size--;
                        }
                        //des有左孩子
                        if (pre != null) {
                            pre.l = des.r;
                            des.r = root.r;
                        }
                        des.l = root.l;
                        des.size = des.l.size + (des.r != null ? des.r.size : 0) + 1;
                        root = des;
                    }
                }
            }
            return root;
        }


        public V get(int index) {
            if (root == null) {
                return null;
            }

            if (index >= 0 && index < size()) {
                return get(root, index).v;
            }
            return null;
        }

        private SBTNode<V> get(SBTNode<V> root, int index) {
            if (root == null) {
                return null;
            }

            int leftSize = root.l != null ? root.l.size : 0;
            if (index < leftSize) {
                return get(root.l, index);
            } else if (index == leftSize) {
                return root;
            } else {
                return get(root.r, index - leftSize - 1);
            }
        }


        public int size() {
            return root == null ? 0 : root.size;
        }

    }

//    public static void main(String[] args) {
//        SbtList<Integer> list = new SbtList<>();
//        while (true){
//            int index = list.size() == 0 ? 0 : (int)(Math.random() * list.size());
//            int value = (int)(Math.random() * 100);
//            list.add(index,value);
//        }
//    }

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
                System.out.println("删除index:" + removeIndex);
                list.remove(removeIndex);
                sbtList.remove(removeIndex);
            } else {
                int randomIndex = (int) (Math.random() * (list.size() + 1));
                int randomValue = (int) (Math.random() * (max + 1));
                System.out.println("加入index:" + randomIndex + " value:" + randomValue);
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
