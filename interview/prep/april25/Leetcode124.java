/*
 * 124. Binary Tree Maximum Path Sum
 * A path in a binary tree is a sequence of nodes where each pair of adjacent nodes in the sequence has an edge connecting them. 
 * A node can only appear in the sequence at most once. 
 * Note that the path does not need to pass through the root.
 * The path sum of a path is the sum of the node's values in the path.
 * Given the root of a binary tree, return the maximum path sum of any non-empty path.
 * Constraints:
   ** The number of nodes in the tree is in the range [1, 3 * 104].
   ** -1000 <= Node.val <= 1000
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
class Leetcode124 {
    int maxPathSum = Integer.MIN_VALUE;

    public int maxPathSum(TreeNode root) {
        dfs(root);
        return maxPathSum;
    }

    int dfs(TreeNode node) {
        if (node == null) {
            return 0;
        }
        //Kadane's         
        int localSum = 0;
        int leftSum = Math.max(dfs(node.left), 0); //ignoring negatives 
        int rightSum = Math.max(dfs(node.right), 0);
        localSum = node.val + leftSum + rightSum;

        maxPathSum = Math.max(maxPathSum, localSum);

        //return localSum; - this is wrong since you need to be propogating the gain upwards 
        return node.val + Math.max(leftSum, rightSum); 
    }
}
