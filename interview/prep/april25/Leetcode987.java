/*
 * 987. Vertical Order Traversal of a Binary Tree
 * Given the root of a binary tree, calculate the vertical order traversal of the binary tree.
 * For each node at position (row, col), its left and right children will be at positions (row + 1, col - 1) and (row + 1, col + 1) respectively. The root of the tree is at (0, 0).
 * The vertical order traversal of a binary tree is a list of top-to-bottom orderings for each column index 
 * starting from the leftmost column and ending on the rightmost column. 
 * There may be multiple nodes in the same row and same column. 
 * In such a case, sort these nodes by their values.
 * Return the vertical order traversal of the binary tree.
 * Constraints:
   ** The number of nodes in the tree is in the range [1, 1000].
   ** 0 <= Node.val <= 1000
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
class Leetcode987 {
    TreeMap<Integer, List<int[]>> colVsNodeValues = new TreeMap<>();

    void dfsVerticalTraversal(TreeNode node, int row, int col) {
        //do left, root, right? not now anyway
        if (node == null) {
            return;
        }
        
        List<int[]> values = colVsNodeValues.getOrDefault(col, new ArrayList<>());
        values.add(new int[] { row, node.val });
        colVsNodeValues.put(col, values);

        dfsVerticalTraversal(node.left, row + 1, col - 1);
        dfsVerticalTraversal(node.right, row + 1, col + 1);
    }

    public List<List<Integer>> verticalTraversal(TreeNode root) {
        dfsVerticalTraversal(root, 0, 0);
        List<List<Integer>> result = new ArrayList<>();
        for (List<int[]> values : colVsNodeValues.values()) {
            values.sort((a, b) -> a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]); //sort by row, then sort by val ascending
            List<Integer> colValues = new ArrayList<>();
            for (int[] value : values) {
                colValues.add(value[1]);
            }
            result.add(colValues);
        }
        return result;
    }
}
