/*
 * Lowest Common Ancestor of a Binary Search Tree
 * Given a binary search tree (BST), find the lowest common ancestor (LCA) node of two given nodes in the BST.
 * According to the definition of LCA on Wikipedia: “The lowest common ancestor is defined between two nodes p and q as the lowest node in T that has both p and q as descendants 
 * (where we allow a node to be a descendant of itself).”
 */

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 * int val;
 * TreeNode left;
 * TreeNode right;
 * TreeNode(int x) { val = x; }
 * }
 */

class Leetcode235 {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        while(root!=null) {
            if((root==p || root==q) || //A node can be its own descendant. If the node itself is root, then there is no other ancestor for it. So, we choose it. 
                (root.val>p.val && root.val<q.val) ||  //p is on the left & q is on the right subtree. So root is the only and lowest common ancestor. 
                    (root.val<p.val &&root.val>q.val)) { // q is on the left & p is on the right subtree. So root is the only and lowest common ancestor. 
                return root; 
            }
            if(root.val>p.val && root.val>q.val) { //both are in the left subtree
                return lowestCommonAncestor(root.left, p, q); 
            }
            if(root.val<p.val && root.val<q.val) { //both are in the right subtree
                return lowestCommonAncestor(root.right, p, q); 
            }
        }
        return null; 
    }
}
