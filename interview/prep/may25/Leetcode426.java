/*
 * 426. Convert Binary Search Tree to Sorted Doubly Linked List
 * Convert a Binary Search Tree to a sorted Circular Doubly-Linked List in place.
 * You can think of the left and right pointers as synonymous to the predecessor and successor pointers in a doubly-linked list. 
 * For a circular doubly linked list, 
 * the predecessor of the first element is the last element, and the successor of the last element is the first element.
 * We want to do the transformation in place. 
 * After the transformation, 
 * the left pointer of the tree node should point to its predecessor, and the right pointer should point to its successor. 
 * You should return the pointer to the smallest element of the linked list.
 * 
 * Constraints:
   ** The number of nodes in the tree is in the range [0, 2000].
   ** -1000 <= Node.val <= 1000
   ** All the values of the tree are unique.
 */


/*
// Definition for a Node.
class Node {
    public int val;
    public Node left;
    public Node right;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val,Node _left,Node _right) {
        val = _val;
        left = _left;
        right = _right;
    }
};
*/

class Leetcode426 {
    Node first = null, last = null;

    public Node treeToDoublyList(Node root) {
        if (root == null) {
            return null;
        }

        dfs(root);

        //close the doubly linked list (circular)
        last.right = first;
        first.left = last;

        return first; //basically returning the smallest node 
    }

    void dfs(Node node) {
        //in-order traversal 
        //left most node (smallest node since it is bst) becomes start of list, after that start linking nodes to its right 
        /*
         * Process like this 
            -> dfs(node.left); 
            -> node.val; 
            -> dfs(node.right); 
         */

        if (node != null) {
            
            //left node processing 
            dfs(node.left);

            //node processing  
            if (last != null) {
                // link the previous node (last)
                // with the current one (node)
                last.right = node;
                node.left = last;
            } else { //first time we are setting value; 
                first = node;
            }
            last = node; //curr node becomes latest until we reach the largest i.e. rightmost one 

            //right node processing 
            dfs(node.right);
        }
    }
}
