/*
 * 129. Sum Root to Leaf Numbers
 * You are given the root of a binary tree containing digits from 0 to 9 only.
 * Each root-to-leaf path in the tree represents a number.
   ** For example, the root-to-leaf path 1 -> 2 -> 3 represents the number 123.
 * Return the total sum of all root-to-leaf numbers. Test cases are generated so that the answer will fit in a 32-bit integer.
 * A leaf node is a node with no children.
 * Constraints:
   ** The number of nodes in the tree is in the range [1, 1000].
   ** 0 <= Node.val <= 9
   ** The depth of the tree will not exceed 10.
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
class Leetcode129 {
  
    int totalSum = 0;

    private void preOrder(TreeNode root, int currentNum) {
        //Root, Left, Right
        if (root != null) {
            currentNum = currentNum * 10 + root.val;
            if (root.left == null && root.right == null) {
                totalSum += currentNum;
            }
            preOrder(root.left, currentNum);
            preOrder(root.right, currentNum);
        }
    }

    public int sumNumbersRecursive(TreeNode root) {
        preOrder(root, 0);
        return totalSum;
    }
  
    public int sumNumbersIterative(TreeNode root) {
        Deque<Object[]> stack = new ArrayDeque<>();
        stack.offer(new Object[] { root, 0 });
        int totalSum = 0;
        while (!stack.isEmpty()) {
            Object[] pair = stack.pollLast();
            if (pair != null) {
                TreeNode node = (TreeNode) pair[0];
                Integer currentNum = (Integer) pair[1];
                if (node != null) {
                    currentNum = currentNum * 10 + node.val;
                    if (node.left == null && node.right == null) {
                        totalSum += currentNum;
                    } else {
                        stack.offer(new Object[] { node.left, currentNum });
                        stack.offer(new Object[] { node.right, currentNum });
                    }
                }
            }
        }
        return totalSum;
    }
}
