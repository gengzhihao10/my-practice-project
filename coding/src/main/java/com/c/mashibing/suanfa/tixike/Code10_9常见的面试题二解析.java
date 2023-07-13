package com.c.mashibing.suanfa.tixike;

import java.util.HashSet;
import java.util.Set;

/*
 题目1， 给定两个可能有环也可能无环的单链表，头节点head1和head2。
 请实现一个函数，如果两个链表相交，请返回相交的第一个交点。如果不相交，返回null
 要求，额外空间复杂度可以为O(N)
 题目2，类似题目1
 要求，如果两个链表长度之和为N，时间复杂度请达到O(N)，额外空间复杂度，请达到O(1)
 */
public class Code10_9常见的面试题二解析 {

    //链表节点
    static class Node{
        int value;
        Node next;

        public Node(int value) {
            this.value = value;
        }

        public Node(int value, Node next) {
            this.value = value;
            this.next = next;
        }
    }

    /*
     * @author gengzhihao
     * @date 2023/7/13 8:51
     * @description 题目1
     * @param head1
     * @param head2
     * @return Node
     **/
    public static Node qs1_process1(Node head1, Node head2){
        if (head1 == null || head2 == null ){
            return null;
        }
        Node loopNode1 = getLoopNode(head1);
        Node loopNode2 = getLoopNode(head2);
        //loopNode共3种情况
        //1.如果是都为空，说明都是无环链表，此时通过判断尾节点和计算长度差，来获取相交节点
        if (loopNode1 == null && loopNode2 == null){
            return bothNoLoopNode(head1,head2);
        }
        //2.都有环，返回2个入环节点中的一个即可
        else if (loopNode1 != null && loopNode2 != null){
            return bothHavingLoopNode(head1,loopNode1,head2,loopNode2);
        }
        //3.一个无环，一个有环，必不可能相交
        return null;

    }

    //两个链表都有环，如果相交，返回其中一个入环节点即可。
    private static Node bothHavingLoopNode(Node head1, Node loopNode1,Node head2, Node loopNode2) {
        //如果2个入环节点是同一个，则两个带环链表必在入环节点之前就相交了
        if (loopNode1 == loopNode2){
            int n = 0;
            Node cur1 = head1;
            Node cur2 = head2;
            while (cur1 != loopNode1){
                n++;
                cur1 = cur1.next;
            }
            while (cur2 != loopNode2){
                n--;
                cur2 = cur2.next;
            }
            //cur1表示更长的链表,cur2为更短的链表
            cur1 = n > 0 ? head1 : head2;
            cur2 = cur1 == head1 ? head2 : head1;
            n = Math.abs(n);
            while ( n != 0){
                cur1 = cur1.next;
                n--;
            }
            while (cur1 != cur2){
                cur1 = cur1.next;
                cur2 = cur2.next;
            }
            return cur1;
        }
        //否则，可能是没相交，也可能是在使用同一个环
        Node cur1 = loopNode1.next;
        while (cur1 != loopNode1){
            //链表1在遍历环的时候遇到了链表2的入环节点，说明2个链表是相交的，返回其中1个入环节点即可。
            if (cur1 == loopNode2){
                return loopNode1;
            }
            cur1 = cur1.next;
        }
        return null;
    }

    //都是无环链表，返回相交节点，如不相交，返回null
    private static Node bothNoLoopNode(Node head1, Node head2) {
        Node end1 = getEndNode(head1);
        Node end2 = getEndNode(head2);
        //尾节点不相同，就是没相交
        if (end1 != end2){
            return null;
        }
        //如果相交
        int n = 0;
        Node cur1 = head1;
        Node cur2 = head2;
        while (cur1 != end1){
            n++;
            cur1 = cur1.next;
        }
        while (cur2 != end2){
            n--;
            cur2 = cur2.next;
        }
        //cur1表示更长的链表,cur2为更短的链表
        cur1 = n > 0 ? head1 : head2;
        cur2 = cur1 == head1 ? head2 : head1;
        n = Math.abs(n);
        while ( n != 0){
            cur1 = cur1.next;
            n--;
        }
        while (cur1 != cur2){
            cur1 = cur1.next;
            cur2 = cur2.next;
        }
        return cur1;
    }

    //输入为无环链表，获取尾节点
    private static Node getEndNode(Node head) {
        Node cur = head;
        while (cur.next != null){
            cur = cur.next;
        }
        return cur;
    }


    //如果链表有环，返回入环节点
    private static Node getLoopNode(Node head) {
        //不可能有环的3种情况
        if (head == null || head.next == null || head.next.next == null){
            return null;
        }
        Set<Node> set = new HashSet<>();
        Node cur = head;
        while (cur != null){
            //入环节点会是第一个被发现重复的，返回入环节点
            if (set.contains(cur)){
                return cur;
            }
            set.add(cur);
            cur = cur.next;
        }
        return null;
    }

//
//    public static void main(String[] args) {
//        // 1->2->3->4->5->6->7->null
//        Node head1 = new Node(1);
//        head1.next = new Node(2);
//        head1.next.next = new Node(3);
//        head1.next.next.next = new Node(4);
//        head1.next.next.next.next = new Node(5);
//        head1.next.next.next.next.next = new Node(6);
//        head1.next.next.next.next.next.next = new Node(7);
//
//        // 0->9->8->6->7->null
//        Node head2 = new Node(0);
//        head2.next = new Node(9);
//        head2.next.next = new Node(8);
//        head2.next.next.next = head1.next.next.next.next.next; // 8->6
//        System.out.println(qs1_process1(head1, head2).value);
//
//        // 1->2->3->4->5->6->7->4...
//        head1 = new Node(1);
//        head1.next = new Node(2);
//        head1.next.next = new Node(3);
//        head1.next.next.next = new Node(4);
//        head1.next.next.next.next = new Node(5);
//        head1.next.next.next.next.next = new Node(6);
//        head1.next.next.next.next.next.next = new Node(7);
//        head1.next.next.next.next.next.next = head1.next.next.next; // 7->4
//
//        // 0->9->8->2...
//        head2 = new Node(0);
//        head2.next = new Node(9);
//        head2.next.next = new Node(8);
//        head2.next.next.next = head1.next; // 8->2
//        System.out.println(qs1_process1(head1, head2).value);
//
//        // 0->9->8->6->4->5->6..
//        head2 = new Node(0);
//        head2.next = new Node(9);
//        head2.next.next = new Node(8);
//        head2.next.next.next = head1.next.next.next.next.next; // 8->6
//        System.out.println(qs1_process1(head1, head2).value);
//
//    }

    //***************************************************************************************************

    /*
     * @author gengzhihao
     * @date 2023/7/13 10:47
     * @description 题目2
     * @param head1
     * @param head2
     * @return Node
     **/
    public static Node qs2_process1(Node head1,Node head2){
        if (head1 == null || head2 == null){
            return null;
        }

        Node loopNode1 = getLoopNode2(head1);
        Node loopNode2 = getLoopNode2(head2);

        if (loopNode1 == null && loopNode2 == null){
            return bothNoLoopNode(head1,head2);
        }
        if (loopNode1 != null && loopNode2 != null){
            return bothHavingLoopNode(head1,loopNode1,head2,loopNode2);
        }
        return null;
    }

    //如果链表有环，返回入环节点
    private static Node getLoopNode2(Node head) {
        //不可能有环的3种情况
        if (head == null || head.next == null || head.next.next == null){
            return null;
        }
        //1.设置快慢节点，当快慢节点相交，说明有环；如果快节点走到了Null，说明没环
        Node fast = head.next.next;
        Node slow = head.next;
        while (fast.next != null && fast.next.next != null){
            if (fast == slow){
                break;
            }
            fast = fast.next.next;
            slow = slow.next;
        }
        if (fast.next == null || fast.next.next == null){
            return null;
        }
        fast = head;
        while (fast != slow){
            fast = fast.next;
            slow = slow.next;
        }
        //2，快节点退回head，和慢节点一样step为1，当快慢节点相遇，该节点就是loopNode
        return fast;
    }


    public static void main(String[] args) {
        // 1->2->3->4->5->6->7->null
        Node head1 = new Node(1);
        head1.next = new Node(2);
        head1.next.next = new Node(3);
        head1.next.next.next = new Node(4);
        head1.next.next.next.next = new Node(5);
        head1.next.next.next.next.next = new Node(6);
        head1.next.next.next.next.next.next = new Node(7);

        // 0->9->8->6->7->null
        Node head2 = new Node(0);
        head2.next = new Node(9);
        head2.next.next = new Node(8);
        head2.next.next.next = head1.next.next.next.next.next; // 8->6
        System.out.println(qs2_process1(head1, head2).value);

        // 1->2->3->4->5->6->7->4...
        head1 = new Node(1);
        head1.next = new Node(2);
        head1.next.next = new Node(3);
        head1.next.next.next = new Node(4);
        head1.next.next.next.next = new Node(5);
        head1.next.next.next.next.next = new Node(6);
        head1.next.next.next.next.next.next = new Node(7);
        head1.next.next.next.next.next.next = head1.next.next.next; // 7->4

        // 0->9->8->2...
        head2 = new Node(0);
        head2.next = new Node(9);
        head2.next.next = new Node(8);
        head2.next.next.next = head1.next; // 8->2
        System.out.println(qs2_process1(head1, head2).value);

        // 0->9->8->6->4->5->6..
        head2 = new Node(0);
        head2.next = new Node(9);
        head2.next.next = new Node(8);
        head2.next.next.next = head1.next.next.next.next.next; // 8->6
        System.out.println(qs2_process1(head1, head2).value);

    }
}
