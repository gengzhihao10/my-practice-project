package com.c.mashibing.suanfa.tixike;

/*
 题目1，实现基于Size Balance Tree(SBT，SB树)的有序表
 */
public class Code37_1SB树 {

    public static class SBTNode<K extends Comparable<K>, V>{
        private K k;
        private V v;
        private int size;
        private SBTNode<K,V> l;
        private SBTNode<K,V> r;

        public SBTNode(K k, V v) {
            this.k = k;
            this.v = v;
            size = 1;
        }
    }

    public static class SizeBalancedTreeMap<K extends Comparable<K>, V>{
        private SBTNode<K,V> root;

        private SBTNode<K, V> rightRotate(SBTNode<K, V> cur){
            if (cur == null){
                return null;
            }
            SBTNode<K,V> left = cur.l;
            cur.l = left.r;
            left.r = cur;
            cur.size = 1 + (cur.l != null ? cur.l.size : 0) + (cur.r != null ? cur.r.size : 0);
            left.size = 1 + (left.l != null ? left.l.size : 0) + (left.r != null ? left.r.size : 0);
            return left;
        }

        private SBTNode<K, V> leftRotate(SBTNode<K, V> cur){
            if (cur == null){
                return null;
            }

            SBTNode<K,V> right = cur.r;
            cur.r = right.l;
            right.l = cur;
            cur.size = 1 + (cur.l != null ? cur.l.size : 0) + (cur.r != null ? cur.r.size : 0);
            right.size = 1 + (right.l != null ? right.l.size : 0) + (right.r != null ? right.r.size : 0);
            return right;
        }

        private SBTNode<K, V> maintain(SBTNode<K, V> cur){
            if (cur == null){
                return null;
            }

            int leftSize = cur.l != null ? cur.l.size : 0;
            int rightSize = cur.r != null ? cur.r.size : 0;
            int leftLeftSize = cur.l != null && cur.l.l != null ? cur.l.l.size : 0;
            int leftRightSize = cur.l != null && cur.l.r != null ? cur.l.r.size : 0;
            int rightLeftSize = cur.r != null && cur.r.l != null ? cur.r.l.size : 0;
            int rightRightSize = cur.r != null && cur.r.r != null ? cur.r.r.size : 0;
            //ll型违规
            if (leftLeftSize > rightSize){
                cur = rightRotate(cur);
                cur.r = maintain(cur.r);
                cur = maintain(cur);
            }
            //lr型违规
            else if (leftRightSize > rightSize){
                cur.l = leftRotate(cur.l);
                cur = rightRotate(cur);
                cur.l = maintain(cur.l);
                cur.r = maintain(cur.r);
                cur = maintain(cur);
            }
            //rl型违规
            else if (rightLeftSize > leftSize){
                cur.r = rightRotate(cur.r);
                cur = leftRotate(cur);
                cur.l = maintain(cur.l);
                cur.r = maintain(cur.r);
                cur = maintain(cur);
            }
            //rr型违规
            else if (rightRightSize > leftSize){
                cur = leftRotate(cur);
                cur.l = maintain(cur.l);
                cur = maintain(cur);
            }
            return cur;
        }

        //找到其节点的k值最接近key的节点
        private SBTNode<K, V> findLastIndex(K key){
            if (key == null){
                return null;
            }

            SBTNode<K,V> ans = null;
            SBTNode<K,V> cur = root;
            while (cur != null){
                ans = cur;
                if (cur.k.compareTo(key) == 0){
                    break;
                }
                else if (cur.k.compareTo(key) < 0){
                    cur = cur.r;
                }else if (cur.k.compareTo(key) > 0){
                    cur = cur.l;
                }
            }
            return ans;
        }

        //找到其节点的k值>=Key且最接近Key的节点
        private SBTNode<K, V> findLastNoSmallIndex(K key){
            if (key == null){
                return null;
            }

            SBTNode<K,V> ans = null;
            SBTNode<K,V> cur = root;
            while (cur != null){
                if (cur.k.compareTo(key) == 0){
                    ans = cur;
                    break;
                }
                else if (cur.k.compareTo(key) < 0){
                    cur = cur.r;
                }else if (cur.k.compareTo(key) > 0){
                    ans = cur;
                    cur = cur.l;
                }
            }
            return ans;
        }

        //找到其节点的k值<=Key且最接近Key的节点
        private SBTNode<K, V> findLastNoBigIndex(K key){
            if (key == null){
                return null;
            }

            SBTNode<K,V> ans = null;
            SBTNode<K,V> cur = root;
            while (cur != null){
                if (cur.k.compareTo(key) == 0){
                    ans = cur;
                    break;
                }
                else if (cur.k.compareTo(key) < 0){
                    ans = cur;
                    cur = cur.r;
                }else if (cur.k.compareTo(key) > 0){
                    cur = cur.l;
                }
            }
            return ans;
        }

        //默认不会加入相同key的元素
        private SBTNode<K, V> add(SBTNode<K, V> cur, K key, V value){
            if (key == null || value == null){
                return null;
            }

            if (cur == null){
                cur = new SBTNode<>(key,value);
            }else {
                cur.size++;
                if (cur.k.compareTo(key) > 0){
                    cur.l = add(cur.l,key,value);
                }else {
                    cur.r = add(cur.r,key,value);
                }
            }
            cur = maintain(cur);
            return cur;
        }

        private SBTNode<K, V> delete(SBTNode<K, V> cur, K key){
            if (cur == null){
                return null;
            }

            if (cur.k.compareTo(key) < 0){
                cur = delete(cur.r,key);
            }else if (cur.k.compareTo(key) > 0){
                cur = delete(cur.l,key);
            }else {
                if (cur.l == null && cur.r == null){
                    cur = null;
                }else if (cur.l == null){
                    cur = cur.r;
                }else if (cur.r == null){
                    cur = cur.l;
                }else {
                    SBTNode<K,V> pre = null;
                    SBTNode<K,V> des = cur.r;
                    des.size--;
                    while (des.l != null){
                        pre = des;
                        des = des.l;
                        des.size--;
                    }
                    //pre为null，说明cur.r没有左孩子
                    //cur.r没有左孩子和有左孩子，是两种不同的情况
                    if (pre == null){
                        des.l = cur.l;
                    }else {
                        pre.l = des.r;
                        des.l = cur.l;
                        des.r = cur.r;
                    }
                    des.size = 1 + (des.l != null ? des.l.size : 0) + (des.r != null ? des.r.size : 0);
                    cur = des;
                }
            }
            return cur;
        }

        //kth，第k个元素，从1开始
        private SBTNode<K, V> getIndex(SBTNode<K, V> cur, int kth){
            if (cur == null){
                return null;
            }

            if (kth == (cur.l != null ? cur.l.size : 0) + 1){
                return cur;
            }else if (kth <= (cur.l != null ? cur.l.size : 0)){
                return getIndex(cur.l,kth);
            }else {
                return getIndex(cur.r,kth - (cur.l != null ? cur.l.size : 0) - 1);
            }
        }

        public int size(){
            return root != null ? root.size : 0;
        }

        public boolean containsKey(K key){
            if (key == null){
                return false;
            }

            SBTNode<K,V> find = findLastIndex(key);
            return find != null && find.k.compareTo(key) == 0;
        }

        public void put(K key, V value){
            if (key == null || value == null){
                return;
            }

            SBTNode<K,V> find = findLastIndex(key);
            if (find != null && find.k.compareTo(key) == 0){
                find.v = value;
            }else {
                root = add(root,key,value);
            }
        }

        public void remove(K key){
            if (key == null){
                return;
            }

            if (containsKey(key)){
                root = delete(root,key);
            }
        }

        //index为索引，从0开始计算，返回index位置的节点的K
        public K getIndexKey(int index){
            if (index < 0 || index >= size()){
                return null;
            }

            SBTNode<K,V> find = getIndex(root,index + 1);
            return find != null ? find.k : null;
        }

        //同上一个方法，但是返回V
        public V getIndexValue(int index){
            if (index < 0 || index >= size()){
                return null;
            }

            SBTNode<K,V> find = getIndex(root,index + 1);
            return find != null ? find.v : null;
        }

        public V get(K key){
            if (key == null){
                return null;
            }

            SBTNode<K,V> find = findLastIndex(key);
            if (find != null && find.k.compareTo(key) == 0){
                return find.v;
            }
            return null;
        }

        public K firstKey(){
            SBTNode<K,V> ans = null;
            SBTNode<K,V> cur = root;
            while (cur != null){
                ans = cur;
                cur = cur.l;
            }
            return ans != null ? ans.k : null;
        }

        public K lastKey(){
            SBTNode<K,V> ans = null;
            SBTNode<K,V> cur = root;
            while (cur != null){
                ans = cur;
                cur = cur.r;
            }
            return ans != null ? ans.k : null;
        }

        //floor，地板。我理解的floorKey应该是找到的节点的k大于等于Key，
        // 但是从左神的方法代码来看，找到的节点k小于等于key，按照他的来
        //找到其节点的k值>=Key且最接近Key的节点的k值
        public K floorKey(K key){
            if (key == null){
                return null;
            }

            SBTNode<K,V> find = findLastNoBigIndex(key);
            return find != null ? find.k : null;
        }

        //ceiling，天花板
        //找到其节点的k值<=Key且最接近Key的节点的k值
        public K ceilingKey(K key){
            if (key == null){
                return null;
            }

            SBTNode<K,V> find = findLastNoSmallIndex(key);
            return find != null ? find.k : null;
        }


        //*******************************************************

        // for test
        public static void printAll(SBTNode<String, Integer> head) {
            System.out.println("Binary Tree:");
            printInOrder(head, 0, "H", 17);
            System.out.println();
        }

        // for test
        public static void printInOrder(SBTNode<String, Integer> head, int height, String to, int len) {
            if (head == null) {
                return;
            }
            printInOrder(head.r, height + 1, "v", len);
            String val = to + "(" + head.k + "," + head.v + ")" + to;
            int lenM = val.length();
            int lenL = (len - lenM) / 2;
            int lenR = len - lenM - lenL;
            val = getSpace(lenL) + val + getSpace(lenR);
            System.out.println(getSpace(height * len) + val);
            printInOrder(head.l, height + 1, "^", len);
        }

        // for test
        public static String getSpace(int num) {
            String space = " ";
            StringBuffer buf = new StringBuffer("");
            for (int i = 0; i < num; i++) {
                buf.append(space);
            }
            return buf.toString();
        }

        public static void main(String[] args) {
            SizeBalancedTreeMap<String, Integer> sbt = new SizeBalancedTreeMap<String, Integer>();
            sbt.put("d", 4);
            sbt.put("c", 3);
            sbt.put("a", 1);
            sbt.put("b", 2);
            // sbt.put("e", 5);
            sbt.put("g", 7);
            sbt.put("f", 6);
            sbt.put("h", 8);
            sbt.put("i", 9);
            sbt.put("a", 111);
            System.out.println(sbt.get("a"));
            sbt.put("a", 1);
            System.out.println(sbt.get("a"));
            for (int i = 0; i < sbt.size(); i++) {
                System.out.println(sbt.getIndexKey(i) + " , " + sbt.getIndexValue(i));
            }
            printAll(sbt.root);
            System.out.println(sbt.firstKey());
            System.out.println(sbt.lastKey());
            System.out.println(sbt.floorKey("g"));
            System.out.println(sbt.ceilingKey("g"));
            System.out.println(sbt.floorKey("e"));
            System.out.println(sbt.ceilingKey("e"));
            System.out.println(sbt.floorKey(""));
            System.out.println(sbt.ceilingKey(""));
            System.out.println(sbt.floorKey("j"));
            System.out.println(sbt.ceilingKey("j"));
            sbt.remove("d");
            printAll(sbt.root);
            sbt.remove("f");
            printAll(sbt.root);

        }
    }

}
