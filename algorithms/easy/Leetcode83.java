/*
 * 83. Remove Duplicates from Sorted List
 * Given the head of a sorted linked list, delete all duplicates such that each element appears only once. 
 * Return the linked list sorted as well.
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
class Leetcode83 {
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode itrNode = head;

        while (itrNode != null && itrNode.next != null) {
            int currVal = itrNode.val;
            int nextVal = itrNode.next.val;

            if (currVal == nextVal) {
                itrNode.next = itrNode.next.next;
                continue;
            }
            itrNode = itrNode.next;
        }
        return head;
    }
}
