/*
 * 21. Merge Two Sorted Lists
 * You are given the heads of two sorted linked lists list1 and list2.
 * Merge the two lists into one sorted list. The list should be made by splicing together the nodes of the first two lists.
 * Return the head of the merged linked list.
 */

/**
 * Definition for singly-linked list.
 * public class ListNode {
 * int val;
 * ListNode next;
 * ListNode() {}
 * ListNode(int val) { this.val = val; }
 * ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Leetcode21 {
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        if (list1 == null && list2 == null) {
            return null;
        }

        // Using two pointer
        ListNode result = new ListNode(Integer.MIN_VALUE);
        ListNode headNode = result;
        while (list1 != null && list2 != null) {
            System.out.println("lis1 : " + list1.val + ", lis2 : " + list2.val);
            if (list1.val <= list2.val) {
                result.next = list1;
                list1 = list1.next;
            } else {
                result.next = list2;
                list2 = list2.next;
            }
            result = result.next;
            System.out.println("val - " + result.val);
        }

        if (list1 == null) {
            result.next = list2;
            ;
        }
        if (list2 == null) {
            result.next = list1;
        }

        return headNode.next;
    }
}
