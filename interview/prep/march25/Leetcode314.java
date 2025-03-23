/*
 * 314. Binary Tree Vertical Order Traversal
 * Given the root of a binary tree, return the vertical order traversal of its nodes' values. (i.e., from top to bottom, column by column).
 * If two nodes are in the same row and column, the order should be from left to right.
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

class Leetcode314 {
    static class Pair<T, U> { //static class since the enclosing class' variables are not being referenced in any way 
        private T first;
        private U second;

        public Pair(T first, U second) {
            this.first = first;
            this.second = second;
        }

        public T getFirst() {
            return first;
        }

        public U getSecond() {
            return second;
        }

        @Override
        public String toString() {
            return getFirst() + " : " + getSecond();
        }
    }

    public List<List<Integer>> verticalOrder(TreeNode root) {
        // Use BFS
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }

        Map<Integer, ArrayList<Integer>> columnVsNodes = new HashMap<>();
        Queue<Pair<TreeNode, Integer>> nodeQueue = new ArrayDeque<>();
        int minColumnValue = 0;
        int maxColumnValue = 0;

        nodeQueue.offer(new Pair<TreeNode, Integer>(root, 0));

        while (!nodeQueue.isEmpty()) {
            Pair<TreeNode, Integer> nodeColDetails = nodeQueue.poll();
            TreeNode node = nodeColDetails.getFirst();
            Integer column = nodeColDetails.getSecond();

            if (node != null) {
                minColumnValue = Math.min(minColumnValue, column);
                maxColumnValue = Math.max(maxColumnValue, column);

                if (!columnVsNodes.containsKey(column)) {
                    columnVsNodes.put(column, new ArrayList<Integer>());
                }
                columnVsNodes.get(column).add(node.val);

                Pair<TreeNode, Integer> leftChild = new Pair<>(node.left, column - 1);
                Pair<TreeNode, Integer> rightChild = new Pair<>(node.right, column + 1);
                nodeQueue.offer(leftChild);
                nodeQueue.offer(rightChild);
            }
        }

        for (int i = minColumnValue; i <= maxColumnValue; i++) {
            result.add(columnVsNodes.get(i));
        }
        return result;
    }
}
