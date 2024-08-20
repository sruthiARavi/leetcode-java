/*
 * Create Binary Tree From Descriptions
 * You are given a 2D integer array descriptions where descriptions[i] = [parenti, childi, isLefti] indicates that parenti is the parent of childi in a binary tree of unique values. Furthermore,
   ** If isLefti == 1, then childi is the left child of parenti.
   **  If isLefti == 0, then childi is the right child of parenti.
 * Construct the binary tree described by descriptions and return its root.
 * The test cases will be generated such that the binary tree is valid.
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

class Leetcode2196 {

    public TreeNode createBinaryTree(int[][] descriptions) {
        Map<Integer, TreeNode> valVsNode = new HashMap<>();
        Set<Integer> childVals = new HashSet<>();

        for (int i = 0; i < descriptions.length; i++) {
            int parentVal = descriptions[i][0];
            int childVal = descriptions[i][1];
            boolean isLeft = descriptions[i][2] == 1;

            TreeNode parentNode = valVsNode.containsKey(parentVal) ? valVsNode.get(parentVal)
                    : new TreeNode(parentVal, null, null);
            TreeNode childNode = valVsNode.containsKey(childVal) ? valVsNode.get(childVal)
                    : new TreeNode(childVal, null, null);
            if (isLeft) {
                parentNode.left = childNode;
            } else {
                parentNode.right = childNode;
            }
            valVsNode.put(parentVal, parentNode);
            valVsNode.put(childVal, childNode);

            childVals.add(childVal);
        }
        for (Integer val : valVsNode.keySet()) {
            if(!childVals.contains(val)) {
                return valVsNode.get(val); 
            }
        }
        return null;
    }
}
