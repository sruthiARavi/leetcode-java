/* Balance a Binary Search Tree
 * Given the root of a binary search tree, return a balanced binary search tree with the same node values. 
 * If there is more than one answer, return any of them.
 * A binary search tree is balanced if the depth of the two subtrees of every node never differs by more than 1.
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
class Leetcode1382 {
    public TreeNode balanceBST(TreeNode root) {
        // Question says given tree is BST so not verifying
        // If verification is needed, check leetcode #98

        // get sorted array from bst
        List<Integer> sortedNodes = new ArrayList<>();
        sortBST(root, sortedNodes);

        // balance the bst
        return balance(sortedNodes, 0, sortedNodes.size() - 1);
    }

    // sort using in-order traversal : L-Root-R
    void sortBST(TreeNode node, List<Integer> sortedNodes) {
        if (node == null) {
            return;
        }
        sortBST(node.left, sortedNodes);
        sortedNodes.add(node.val);
        sortBST(node.right, sortedNodes);
    }

    // traverse sorted list and construct balanced bst
    // mid is root, left of mid in left subtree, right of mid is right subtree
    TreeNode balance(List<Integer> sortedNodes, int low, int high) {
        if (low > high) {
            return null;
        }
        int mid = low + (high - low) / 2;
        TreeNode balancedNode = new TreeNode();
        balancedNode.val = sortedNodes.get(mid);
        balancedNode.left = balance(sortedNodes, low, mid - 1);
        balancedNode.right = balance(sortedNodes, mid + 1, high);
        return balancedNode;
    }
}
