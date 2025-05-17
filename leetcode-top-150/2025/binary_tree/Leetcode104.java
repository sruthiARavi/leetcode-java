/*
 * 104. Maximum Depth of Binary Tree
 * Given the root of a binary tree, return its maximum depth.
 * A binary tree's maximum depth is the number of nodes along the longest path from the root node down to the farthest leaf node.
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
class Leetcode104 {
    int maxDepth = 0;

    public int maxDepth(TreeNode root) {
        if (root == null) {
            return maxDepth;
        }
        dfs(root, 1);
        return maxDepth;
    }

    void dfs(TreeNode node, int level) {
        if (node == null) {
            return;
        }
        maxDepth = Math.max(maxDepth, level);
        dfs(node.left, level + 1);
        dfs(node.right, level + 1);
    }
}
