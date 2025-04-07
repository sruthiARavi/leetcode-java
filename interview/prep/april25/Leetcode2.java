/*
 * 2. Add Two Numbers
 * You are given two non-empty linked lists representing two non-negative integers. 
 * The digits are stored in reverse order, and each of their nodes contains a single digit. 
 * Add the two numbers and return the sum as a linked list.
 * You may assume the two numbers do not contain any leading zero, except the number 0 itself.
 * 
 * Constraints:
   ** The number of nodes in each linked list is in the range [1, 100].
   ** 0 <= Node.val <= 9
   ** It is guaranteed that the list represents a number that does not have leading zeros.
 * 
 * Example : 
   ** Input: l1 = [2,4,3], l2 = [5,6,4]
   ** Output: [7,0,8]
   ** Explanation: 342 + 465 = 807.
 */
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Leetcode2 {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(); 
        ListNode result = dummy; 
        int carry = 0; 
        while(true) {
            if(l1 == null && l2 == null && carry == 0) {
                break; 
            }
            int num1 = l1 == null ? 0 : l1.val; 
            int num2 = l2 == null ? 0 : l2.val; 
            int sum = num1 + num2 + carry; 
            
            carry = sum/10; 
            result.next = new ListNode(sum%10); 
            
            l1 = l1 == null ? null : l1.next; 
            l2 = l2 == null ? null : l2.next; 
            result = result.next; 
        }
        return dummy.next; 
    }
}
