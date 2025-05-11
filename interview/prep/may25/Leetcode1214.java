/*
 * 1214. Two Sum BSTs
 * Given the roots of two binary search trees, root1 and root2, 
 * return true if and only if there is a node in the first tree and a node in the second tree whose values sum up to a given integer target.
 * 
 * Constraints:
   ** The number of nodes in each tree is in the range [1, 5000].
   ** -109 <= Node.val, target <= 109
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
class Solution {
    public boolean twoSumBSTs(TreeNode root1, TreeNode root2, int target) {
        Set<Integer> t1 = new HashSet<>(); 
        dfs(root1, t1); 
        
        Queue<TreeNode> q = new LinkedList<>(); 
        q.add(root2); 
        while(!q.isEmpty()) {
            TreeNode node = q.poll(); 
            if(t1.contains(target - node.val)) {
                return true;                 
            }    
            if(node.left != null) {
                q.add(node.left); 
            }
            if(node.right != null) {
                q.add(node.right); 
            }            
        }
        return false; 
    }
    
    void dfs(TreeNode node, Set<Integer> t) {
        if(node == null) {
            return; 
        }
        t.add(node.val); 
        dfs(node.left, t); 
        dfs(node.right, t); 
    }
}
