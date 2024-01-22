package com.c.mashibing.suanfa.tixike;

import lombok.val;

/*
 题目1，
 有一个滑动窗口（讲过的）：
 1）L是滑动窗口最左位置、R是滑动窗口最右位置，一开始LR都在数组左侧
 2）任何一步都可能R往右动，表示某个数进了窗口
 3）任何一步都可能L往右动，表示某个数出了窗口
 想知道每一个窗口状态的中位数

 */
public class Code38_5有一个滑动窗口想知道每一个窗口状态的中位数信息 {


    /*
     * @author gengzhihao
     * @date 2024/1/15 11:52
     * @description 题目1
     * @param nums
     * @param k
     * @return double[]
     **/
    public static double[] medianSlidingWindow(int[] nums, int k){
        if (nums == null || nums.length == 0 || k < 0){
            return null;
        }

        // 题目没有说无重复数字，如果 在有重复数字的情况下，使用Integer作为key塞进map中，有可能因为Integer常量池的存在（-127~128复用对象）导致两个相同的数字使用同一个对象
        // 进而导致结果出错。所以这里又套了一层，这样比较的就是对象的内存地址。
        SizeBalancedTreeMap<Node> map = new SizeBalancedTreeMap<>();
        //先在map中放置k-1个,索引为0~k-2
        for (int i = 0; i < k-1; i++){
            map.add(new Node(i,nums[k]));
        }
        double[] ans = new double[nums.length - k + 1];
        int index = 0;
        for (int i = k-1; i < nums.length; i++){
            map.add(new Node(i,nums[k]));
            //数量为偶数
            if (map.size() % 2 == 0){
                Node a = map.getIndexKey(map.size() / 2);
                Node b = map.getIndexKey(map.size() / 2 - 1);
                ans[index++] = (double) ((a == null ? 0 : a.value) + (b == null ? 0 : b.value)) / 2;
            }
            //数量为奇数
            else {
                Node a = map.getIndexKey(map.size() / 2);
                ans[index++] = (double)(a == null ? 0 : a.value);
            }
            map.remove(new Node(i - k + 1,nums[i - k + 1]));
        }
        return ans;
    }

    //主函数里用的node
    public static class Node implements Comparable<Node> {
        private int index;
        private int value;

        public Node(int i, int v){
            this.index = i;
            this.value = v;
        }

        @Override
        public int compareTo(Node o) {
            return this.value != o.value ? Integer.compare(this.value, o.value) : Integer.compare(this.index,o.index);
        }
    }

    //有序表内部维护数据使用的node
    public static class SBTNode<K extends Comparable<K>>{
        private K key;
        private SBTNode<K> l;
        private SBTNode<K> r;
        private int size;

        public SBTNode(K k){
            this.key = k;
            this.size = 0;
        }
    }


    public static class SizeBalancedTreeMap<K extends Comparable<K>>{

        private SBTNode<K> root;

        private SBTNode<K> rightRotate(SBTNode<K> cur){
            SBTNode<K> left = cur.l;
            cur.l = left.r;
            left.r = cur;
            left.size = cur.size;
            cur.size = (cur.l != null ? cur.l.size : 0) + (cur.r != null ? cur.r.size : 0) + 1;
            return left;
        }

        private SBTNode<K> leftRotate(SBTNode<K> cur){
            SBTNode<K> right = cur.r;
            cur.r = right.l;
            right.l = cur;
            right.size = cur.size;
            cur.size = (cur.l != null ? cur.l.size : 0) + (cur.r != null ? cur.r.size : 0) + 1;
            return right;
        }

        private SBTNode<K> maintain(SBTNode<K> cur){
            if (cur == null){
                return null;
            }

            int left = cur.l != null ? cur.l.size : 0;
            int right = cur.r != null ? cur.r.size : 0;
            int leftLeft = cur.l != null && cur.l.l != null ? cur.l.l.size : 0;
            int rightRight = cur.r != null && cur.r.r != null ? cur.r.r.size : 0;
            int leftRight = cur.l != null && cur.l.r != null ? cur.l.r.size : 0;
            int rightLeft = cur.r != null && cur.r.l != null ? cur.r.l.size : 0;
            if (leftLeft > right){
                cur = rightRotate(cur);
                cur.r = maintain(cur.r);
                cur = maintain(cur);
            }
            else if (leftRight > right){
                cur.l = leftRotate(cur.l);
                cur = rightRotate(cur);
                cur.l = maintain(cur.l);
                cur.r = maintain(cur.r);
                cur = maintain(cur);
            }
            else if (rightLeft > left ){
                cur.r = rightRotate(cur.r);
                cur = leftRotate(cur);
                cur.l = maintain(cur.l);
                cur.r = maintain(cur.r);
                cur = maintain(cur);
            }else if (rightRight > left){
                cur = leftRotate(cur);
                cur.l = maintain(cur.l);
                cur = maintain(cur);
            }
            return cur;
        }

        private SBTNode<K> findLastIndex(K key){
            if (key == null){
                return null;
            }

            SBTNode<K> pre = null;
            SBTNode<K> cur = root;
            while (root != null){
                pre = cur;
                if (key.compareTo(cur.key) == 0){
                    break;
                }
                else if (key.compareTo(cur.key) < 0){
                    cur = cur.l;
                }
                else if (key.compareTo(cur.key) > 0){
                    cur = cur.r;
                }
            }
            return pre;
        }

        private SBTNode<K> add(SBTNode<K> cur, K key){
            if (cur == null){
                return null;
            }

            cur.size++;
            if (cur.key.compareTo(key) > 0){
                cur.l = add(cur.l,key);
            }
            else if (cur.key.compareTo(key) < 0){
                cur.r = add(cur.r,key);
            }

            return maintain(cur);
        }

        private SBTNode<K> delete(SBTNode<K> cur, K key){
            if (cur == null){
                return null;
            }
            else {
                //cur.key < key
                if (cur.key.compareTo(key) < 0){
                    cur.r = delete(cur.r,key);
                }
                //cur.key > key
                else if (cur.key.compareTo(key) > 0){
                    cur.l = delete(cur.l,key);
                }
                //cur.key == key
                else {
                    //没有左右孩子
                    if (cur.l == null && cur.r == null){
                        cur = null;
                    }
                    //只有右孩子
                    else if (cur.l == null){
                        cur = cur.r;
                    }
                    //只有左孩子
                    else if (cur.r == null){
                        cur = cur.l;
                    }
                    //左右孩子都有
                    else {
                        //找到右孩子的最左孩子des
                        SBTNode<K> des = cur.r;
                        SBTNode<K> pre = null;
                        des.size--;
                        while (des.l != null){
                            pre = des;
                            des = des.l;
                            des.size--;
                        }
                        //des和cur调换
                        //Pre不为空，意味着cur.r有最左孩子，有一个pre变量需要调
                        if (pre != null){
                            pre.l = des.r;
                            des.r = cur.r;
                        }
                        des.l = cur.l;
                        des.size = des.l.size + (des.r != null ? des.r.size : 0) + 1;
                        cur = des;
                    }
                }
            }

            return cur;
        }



        public int size(){
            return root == null ? 0 : root.size;
        }

        public boolean containsKey(K key){
            if (key == null){
                return false;
            }

            SBTNode<K> find = findLastIndex(key);
            return find != null && find.key.compareTo(key) == 0;
        }

        public void add(K key){
            if (key == null){
                return;
            }

            if (!containsKey(key)){
                root = add(root,key);
            }
        }


        public void remove(K key){
            if (key == null || !containsKey(key)){
                return;
            }

            root = delete(root,key);
        }

        //index为SB树从左到右的索引，0~size-1
        public K getIndexKey(int index){
            if (index < 0 || index >= size()){
                return null;
            }

            SBTNode<K> find = getIndex(root, index + 1);
            return find == null ? null : find.key;
        }

        //kth表示第几个，从1开始到size()
        private SBTNode<K> getIndex(SBTNode<K> cur, int kth){
            if (cur == null){
                return null;
            }

            int left = cur.l == null ? 0 : cur.l.size;
            if (left <= kth){
                return getIndex(cur.l,kth);
            }else if (left + 1 == kth){
                return cur;
            }else {
                return getIndex(cur.r,kth - left - 1);
            }
        }
    }

}
