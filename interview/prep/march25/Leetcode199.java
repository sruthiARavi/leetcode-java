/*
 * 199. Binary Tree Right Side View
 * Given the root of a binary tree, imagine yourself standing on the right side of it, 
 * return the values of the nodes you can see ordered from top to bottom.
 *
 * Constraints:
   ** The number of nodes in the tree is in the range [0, 100].
   ** -100 <= Node.val <= 100
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
class Leetcode199 {
    public List<Integer> rightSideView(TreeNode root) {
        // Using BFS and 2 queues approach, DFS might be faster 
        // O(N) time complexity and O(Diameter) space complexity i.e. queue size
        if(root==null) {
            return new ArrayList<>(); 
        }
        List<Integer> rightMostNodesInEverylevel = new ArrayList<>();
        ArrayDeque<TreeNode> nextLevel = new ArrayDeque<>() {
            {
                offer(root);
            }
        };
        ArrayDeque<TreeNode> currentLevel = new ArrayDeque<>();
        TreeNode node = new TreeNode();
        while (!nextLevel.isEmpty()) {
            currentLevel = nextLevel;
            nextLevel = new ArrayDeque<>();

            while (!currentLevel.isEmpty()) {
                node = currentLevel.poll();
                if (node.left != null) {
                    nextLevel.offer(node.left);
                }
                if (node.right != null) {
                    nextLevel.offer(node.right);
                }
            }
            rightMostNodesInEverylevel.add(node.val);
        }
        return rightMostNodesInEverylevel;
    }
}
