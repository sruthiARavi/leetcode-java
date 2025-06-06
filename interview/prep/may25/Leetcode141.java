/*
 * 141. Linked List Cycle
 * Given head, the head of a linked list, determine if the linked list has a cycle in it.
 * There is a cycle in a linked list if there is some node in the list that can be reached again by continuously following the next pointer. 
 * Internally, pos is used to denote the index of the node that tail's next pointer is connected to. 
 * Note that pos is not passed as a parameter.
 * Return true if there is a cycle in the linked list. Otherwise, return false.
 *
 * Constraints:
   ** The number of the nodes in the list is in the range [0, 104].
   ** -105 <= Node.val <= 105
   ** pos is -1 or a valid index in the linked-list.
 * 
 * Follow up: Can you solve it using O(1) (i.e. constant) memory?
 */

/**
 * Definition for singly-linked list.
 * class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class Leetcode141 {
    public boolean hasCycle(ListNode head) {
        //Floyd's algorithm 
        
        if(head == null || head.next == null) {
            return false; 
        }
        ListNode slow = head; 
        ListNode fast = head; 

        while(fast != null && fast.next != null) {
            slow = slow.next; 
            fast = fast.next.next; 

            if(slow == fast) {
                return true; 
            }
        }

        return false; 
    }

    public boolean hasCycle1(ListNode head) {
        if(head == null || head.next == null) {
            return false; 
        }

        Set<ListNode> s = new HashSet<>(); 
        while(head.next != null) {
            if(s.contains(head)) {
                return true; 
            }
            s.add(head); 
            head = head.next; 
        }
        return false; 
    }
}
