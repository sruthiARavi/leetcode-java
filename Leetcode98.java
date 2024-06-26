/*
 * Validate BST 
 * Given the root of a binary tree, determine if it is a valid binary search tree (BST).
 * A valid BST is defined as follows:
    * The left subtree of a node contains only nodes with keys less than the node's key.
    * The right subtree of a node contains only nodes with keys greater than the node's key.
 * Both the left and right subtrees must also be binary search trees.
 */

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 * int val;
 * TreeNode left;
 * TreeNode right;
 * TreeNode() {}
 * TreeNode(int val) { this.val = val; }
 * TreeNode(int val, TreeNode left, TreeNode right) {
 * this.val = val;
 * this.left = left;
 * this.right = right;
 * }
 * }
 */
class Leetcode98 {
    public boolean isValidBST(TreeNode root) {
        return isValid(root.left, Long.MIN_VALUE, root.val) &&
                isValid(root.right, root.val, Long.MAX_VALUE);
    }

    boolean isValid(TreeNode node, long min, long max) {
       /* checking if value for left nodes are in the range of lowest to current node val and 
        * the right nodes are in the range of current node val to highest (not inclusive). 
        */
        if (node == null) {
            return true;
        }
        if (node.val <= min || node.val >= max) {
            return false;
        }
        return isValid(node.left, min, node.val) && isValid(node.right, node.val, max);
    }
}  
}
