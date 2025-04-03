/*
 * 938. Range Sum of BST
 * Given the root node of a binary search tree and two integers low and high, 
 * return the sum of values of all nodes with a value in the inclusive range [low, high].
 * Constraints:
   ** The number of nodes in the tree is in the range [1, 2 * 104].
   ** 1 <= Node.val <= 105
   ** 1 <= low <= high <= 105
   ** All Node.val are unique.
 */
public class Leetcode938 {    

    //938. Range Sum of BST
    public class TreeNode { //since it doesn't access any of the variables of the enclosing class, it can be made static
        int val;
        PrepApr25.TreeNode left;
        PrepApr25.TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, PrepApr25.TreeNode left, PrepApr25.TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    int ans;

    void dfs(TreeNode node, int low, int high) {
        if (node != null) {
            if (low <= node.val && node.val <= high) {//within range
                ans += node.val;
            }
            if (low < node.val) {
                dfs(node.left, low, high);
            }
            if (node.val < high) {
                dfs(node.right, low, high);
            }
        }
    }

    public int rangeSumBSTRecursive(TreeNode root, int low, int high) {
        //Use dfs
        dfs(root, low, high);
        return ans;
    }

    public int rangeSumBSTIterative(TreeNode root, int low, int high) {
        //Use stack for iterative approach dfs
        if(root == null) {
            return 0;
        }
        Deque<TreeNode> stack = new ArrayDeque<>();
        stack.offer(root);
        int ans = 0;
        while (!stack.isEmpty()) {
            TreeNode node = stack.pollLast();
            if (low <= node.val && node.val <= high) {//within range
                ans += node.val;
            }
            if (low < node.val && node.left != null) { //dq doesn't accept null, hence a check is required
                stack.offer(node.left);
            }
            if (node.val < high && node.right != null) {
                stack.offer(node.right);
            }
        }
        return ans;
    }
}
