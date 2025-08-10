/*
 * 144. Binary Tree Preorder Traversal
 * Given the root of a binary tree, return the preorder traversal of its nodes' values.
 */
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Leetcode144 {
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<Integer>(); 
        traverse(root, result);         
        return result;
    }

    public void traverse(TreeNode node, List<Integer> result) {
        if(node == null) {
            return; 
        }
        result.add(node.val); 
        traverse(node.left, result); 
        traverse(node.right, result);          
    }
}
