package com.c.mashibing.suanfa.tixike;


/*
 题目1，实现AVL树+搜索二叉树（Binary Search Tree, BST），先实现AVL的部分，再实现BST的部分
 */
public class Code36_8AVL实现代码 {


    //AVL树节点
    public static class AVLNode<K extends Comparable<K>,V>{
        private K k;
        private V v;
        private AVLNode<K,V> l;
        private AVLNode<K,V> r;
        private int h;

        public AVLNode(K key, V value){
            k = key;
            v = value;
        }
    }


    //AVL树实现
    public static class AVLTreeMap<K extends Comparable<K>,V>{
        private AVLNode<K,V> root;
        private int size;

        public AVLTreeMap(){
            root = null;
            size = 0;
        }

        private AVLNode<K, V> rightRotate(AVLNode<K, V> cur){
            if (cur == null){
                return null;
            }

            AVLNode<K,V> left = cur.l;
            cur.l = left.r;
            left.r = cur;
            cur.h = Math.max(cur.l == null ? 0 : cur.l.h, cur.r == null ? 0 : cur.r.h) + 1;
            left.h = Math.max(left.l == null ? 0 : left.l.h, left.r == null ? 0 : left.r.h) + 1;
            return left;
        }

        private AVLNode<K, V> leftRotate(AVLNode<K, V> cur){
            if (cur == null){
                return null;
            }

            AVLNode<K,V> right = cur.r;
            cur.r = right.l;
            right.l = cur;
            cur.h = Math.max(cur.l == null ? 0 : cur.l.h, cur.r == null ? 0 : cur.r.h) + 1;
            right.h = Math.max(right.l == null ? 0 : right.l.h, right.r == null ? 0 : right.r.h) + 1;
            return right;
        }


        //检查cur节点是否有及子节点、孙子节点是否有违规
        private AVLNode<K, V> maintain(AVLNode<K, V> cur){
            if (cur == null){
                return null;
            }

            int leftHeight = cur.l == null ? 0 : cur.l.h;
            int rightHeight = cur.r == null ? 0 : cur.r.h;
            //不平衡
            if (Math.abs(leftHeight - rightHeight) > 1){
                //左树更高
                if (leftHeight > rightHeight){
                    //LL LR型违规
                    int leftLeftHeight = cur.l != null && cur.l.l != null ? cur.l.l.h : 0;
                    int leftRightHeight = cur.l != null && cur.l.r != null ? cur.r.r.h : 0;
                    if (leftLeftHeight >= leftRightHeight){
                        cur = rightRotate(cur);
                    }else {
                        cur.l = leftRotate(cur.l);
                        cur = rightRotate(cur);
                    }
                }
                //右树更高
                else {
                    int rightRightHeight = cur.r != null && cur.r.r != null ? cur.r.r.h : 0;
                    int rightLeftHeight = cur.r != null && cur.r.l != null ? cur.r.l.h : 0;
                    if (rightRightHeight >= rightLeftHeight){
                        cur = leftRotate(cur);
                    }else {
                        cur.r = rightRotate(cur.r);
                        cur = leftRotate(cur);
                    }
                }
            }

            return cur;
        }

        private AVLNode<K, V> add(AVLNode<K, V> cur, K key, V value){
            if (cur == null){
                return new AVLNode<>(key,value);
            }

            if (cur.k == key){
                cur.v = value;
                return cur;
            }

            if (cur.k.compareTo(key) < 0){
                cur.r = add(cur.r,key,value);
            }
            else{
                cur.l = add(cur.l,key,value);
            }
            //cur的高度有可能改变
            cur.h = Math.max(cur.l != null ? cur.l.h : 0, cur.r != null ? cur.r.h : 0) + 1;
            cur = maintain(cur);
            return cur;
        }

        private AVLNode<K, V> delete(AVLNode<K, V> cur, K key){
            if (cur == null){
                return null;
            }

            if (cur.k.compareTo(key) < 0){
                cur.r = delete(cur.r,key);
            }
            else if (cur.k.compareTo(key) > 0){
                cur.l = delete(cur.l,key);
            }
            //cur就是要删除的节点
            else {
                AVLNode<K,V> newCur;
                //cur的左右孩子都为Null
                if (cur.l == null && cur.r == null){
                    return null;
                }
                //只有左孩子为null
                else if (cur.l == null){
                    cur = cur.r;
                }
                //只有右孩子为null
                else if (cur.r == null){
                    cur = cur.l;
                }
                //左右孩子都不为null
                else {
                    newCur = cur.r;
                    while (newCur.l != null){
                        newCur = newCur.l;
                    }
                    cur.r = delete(cur.r,newCur.k);
                    cur.k = newCur.k;
                    cur.v = newCur.v;
                }
            }
            cur.h = Math.max(cur.l == null ? 0 : cur.l.h, cur.r == null ? 0 : cur.r.h) +1;
            cur = maintain(cur);
            return cur;
        }

        //找到其k值<=key且最接近Key的节点
        private AVLNode<K, V> findLastNoBigIndex(K key){
            if (key == null){
                return null;
            }

            AVLNode<K,V> ans = null;
            AVLNode<K,V> cur = root;
            while (cur != null){
                if (key.compareTo(cur.k) == 0){
                    ans = cur;
                    break;
                }
                else if (key.compareTo(cur.k) < 0){
                    cur = cur.l;
                }else {
                    ans = cur;
                    cur = cur.r;
                }
            }
            return ans;

        }

        //找到其k值>=key且最接近Key的节点
        private AVLNode<K, V> findLastNoSmallIndex(K key){
            if (key == null){
                return null;
            }

            AVLNode<K,V> ans = null;
            AVLNode<K,V> cur = root;
            while (cur != null){
                if (key.compareTo(cur.k) == 0){
                    ans = cur;
                    break;
                }
                else if (key.compareTo(cur.k) < 0){
                    ans = cur;
                    cur = cur.l;
                }else {
                    cur = cur.r;
                }
            }
            return ans;
        }

        //找到k最接近Key的节点
        private AVLNode<K, V> findLastIndex(K key){
            if (key == null){
                return null;
            }

            AVLNode<K,V> ans = null;
            AVLNode<K,V> cur = root;
            while (cur != null){
                ans = cur;
                if (key.compareTo(cur.k) == 0){
                    break;
                }
                else if (key.compareTo(cur.k) < 0){
                    cur = cur.l;
                }else {
                    cur = cur.r;
                }
            }
            return ans;

        }

        public int size(){
            return size;
        }

        public boolean containsKey(K key){
            if (key == null){
                return false;
            }

            return key.compareTo(findLastIndex(key).k) == 0;
        }

        public void put(K key, V value){
            if (key == null){
                return;
            }

            AVLNode<K,V> lastNode = findLastIndex(key);
            //存在key则只需要更改value，不需要size++
            if (lastNode != null && lastNode.k.compareTo(key) == 0){
                lastNode.v = value;
            }else {
                size++;
                root = add(root,key,value);
            }
        }

        public void remove(K key){
            if (key == null){
                return;
            }

            //防止delete时空指针。
            if (containsKey(key)){
                size--;
                root = delete(root,key);
            }

        }

        public V get(K key){
            if (key == null){
                return null;
            }

            AVLNode<K,V> res = findLastIndex(key);
            return key.compareTo(res.k) == 0 ? res.v : null;
        }

        //找到最左的key
        public K firstKey(){
            AVLNode<K,V> ans = root;
            while (ans.l != null){
                ans = ans.l;
            }
            return ans.k;
        }

        //找到最右的key
        public K lastKey(){
            AVLNode<K,V> ans = root;
            while (ans.r != null){
                ans = ans.r;
            }
            return ans.k;
        }

        //找到k最接近key同时key值比节点的k小
        public K floorKey(K key){
            if (key == null){
                return null;
            }

            AVLNode<K,V> floorNode = findLastNoBigIndex(key);
            return floorNode == null ? null : floorNode.k;
        }

        //找到k最接近key同时key值比节点的k大
        public K ceilingKey(K key){
            if (key == null){
                return null;
            }

            AVLNode<K,V> ceilingNode = findLastNoSmallIndex(key);
            return ceilingNode == null ? null : ceilingNode.k;
        }
    }
}
