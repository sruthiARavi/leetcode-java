/*
 * Given the root of a binary tree, each node in the tree has a distinct value.
 * After deleting all nodes with a value in to_delete, we are left with a forest (a disjoint union of trees).
 * Return the roots of the trees in the remaining forest. You may return the result in any order.
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
class Leetcode1110 {
    public List<TreeNode> delNodes(TreeNode root, int[] to_delete) {
        if (root == null) {
            return null;
        }
        ArrayList<TreeNode> forest = new ArrayList<>();

        root = delNodesAndGetForest(root, Arrays.stream(to_delete).boxed().collect(Collectors.toSet()), forest);
        if (root != null) {
            forest.add(root);
        }
        return forest;
    }

    TreeNode delNodesAndGetForest(TreeNode node, Set<Integer> to_delete, ArrayList<TreeNode> forest) { //post order traversal 
        if (node == null) {
            return null;
        }

        node.left = delNodesAndGetForest(node.left, to_delete, forest);
        node.right = delNodesAndGetForest(node.right, to_delete, forest);

        if (to_delete.contains(node.val)) {
            if (node.left != null) {
                forest.add(node.left);
            }
            if (node.right != null) {
                forest.add(node.right);
            }
            return null;
        }
        return node;
    }
}
