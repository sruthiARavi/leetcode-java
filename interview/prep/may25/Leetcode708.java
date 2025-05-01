/*
 * 708. Insert into a Sorted Circular Linked List
 * 
 * Given a Circular Linked List node, which is sorted in non-descending order, 
 * write a function to insert a value insertVal into the list such that it remains a sorted circular list. 
 * The given node can be a reference to any single node in the list and may not necessarily be the smallest value in the circular list.
 * If there are multiple suitable places for insertion, you may choose any place to insert the new value. 
 * After the insertion, the circular list should remain sorted. 
 * If the list is empty (i.e., the given node is null), you should create a new single circular list and return the reference to that single node. 
 * Otherwise, you should return the originally given node.
 *
 * Constraints:
   ** The number of nodes in the list is in the range [0, 5 * 104].
   ** -106 <= Node.val, insertVal <= 106
 */

/*
// Definition for a Node.
class Node {
    public int val;
    public Node next;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, Node _next) {
        val = _val;
        next = _next;
    }
};
*/

class Leetcode708 {
    public Node insert(Node head, int insertVal) {
        Node iNode = new Node(insertVal); 

        if(head == null) {
            iNode.next = iNode; 
            return iNode; 
        }

        if(head.next == head) {
            iNode.next = head; 
            head.next = iNode; 
            return head; 
        }

        Node prev = head, curr = head.next; 
        while(true) {
            // Case 1: Normal insert between two nodes
            if(insertVal >= prev.val && insertVal <= curr.val) {                
                break; 
            } 

            // Case 2: At the rotation point (prev is the largest, curr is the smallest)
            if(prev.val > curr.val) {
                if(insertVal >= prev.val || insertVal <= curr.val) {
                    break; 
                }
            }

            prev = prev.next; 
            curr = curr.next; 

            if(prev == head) {
                break; 
            }
        }

        prev.next = iNode; 
        iNode.next = curr; 

        return head; 
    }
}
