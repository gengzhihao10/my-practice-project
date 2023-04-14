package com.c.mashibing.suanfa.xinshouke;

/**
 * 将两个有序链表和并为一个有序链表
 * 如 1->5->9, 2->3->7
 * 合并为1->2->3->5->7->9
 */
public class Code4_9两个有序链表的合并 {

    //链表节点
    static class ListNode{
        int value;
        ListNode next;

        public ListNode(int value) {
            this.value = value;
        }
    }

    /*
     * @author gengzhihao
     * @date 2023/4/13 9:59
     * @description
     * @param head1
     * @param head2
     * @return ListNode 新有序链表的头节点
     **/
    public static ListNode mergeTwoLists(ListNode head1, ListNode head2){
        //有一个为空，返回另外一个
        if (head1 == null || head2 == null){
            return head1 == null ? head2 : head1;
        }

        ListNode head = head1.value <= head2.value ? head1 : head2;
        ListNode cur1 = head.next;
        ListNode cur2 = head == head1 ? head2 :head1;
        ListNode pre = head;
        //两个链表当前节点都不为空时
        while (cur1 != null && cur2 != null){
            if (cur1.value <= cur2.value){
                pre.next = cur1;
                cur1 = cur1.next;
            }else {
                pre.next = cur2;
                cur2 = cur2.next;
            }
            pre = pre.next;
        }
        //剩下的链表，直接把指针甩过去就可以
        pre.next = cur1 != null ? cur1 : cur2;
        return head;
    }
}
