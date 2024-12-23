/*
 * 2471. Minimum Number of Operations to Sort a Binary Tree by Level
 * You are given the root of a binary tree with unique values.
 * In one operation, you can choose any two nodes at the same level and swap their values.
 * Return the minimum number of operations needed to make the values at each level sorted in a strictly increasing order.
 * The level of a node is the number of edges along the path between it and the root node.
 */
class Solution {
    public int minimumOperations(TreeNode root) {
        // BFS search
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int minOps = 0;
        while (!queue.isEmpty()) {
            int[] values = new int[queue.size()];
            for (int i = 0; i < values.length; i++) {
                TreeNode node = queue.poll();
                values[i] = node.val;

                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
            }
            minOps += getMinSwaps(values);
        }
        return minOps;
    }

    // Calculate minimum swaps needed to sort an array
    private int getMinSwaps(int[] original) {
        // Use cycle sort algorithm
        int minSwaps = 0;
        int[] target = original.clone();
        Arrays.sort(target);
        Map<Integer, Integer> position = new HashMap<>(); // To store positions of each element in the array
        for (int i = 0; i < original.length; i++) {
            position.put(original[i], i); // All the values in the tree are unique
        }
        for (int i = 0; i < original.length; i++) {
            if (original[i] != target[i]) {
                minSwaps++;

                int swap_pos = position.get(target[i]);// Find where the val actually resides

                int temp = original[i];
                original[i] = target[i];
                original[swap_pos] = temp;

                position.put(temp, swap_pos);
                position.put(target[i], i); // not needed
            }
        }
        return minSwaps;
    }

  class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}
