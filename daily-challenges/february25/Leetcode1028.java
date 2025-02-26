/*
 * 1028. Recover a Tree From Preorder Traversal
 * We run a preorder depth-first search (DFS) on the root of a binary tree.
 * At each node in this traversal, we output D dashes (where D is the depth of this node), then we output the value of this node.  
 * If the depth of a node is D, the depth of its immediate child is D + 1.  The depth of the root node is 0.
 * If a node has only one child, that child is guaranteed to be the left child.
 * Given the output traversal of this traversal, recover the tree and return its root.
 */
class Leetcode1028 {
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

    public TreeNode recoverFromPreorder(String traversal) {
        if (traversal == null || traversal.isEmpty()) {
            // Input conditions render this moot but it's good practice to add this check anyway
            return null;
        }

        Stack<TreeNode> treenodeStack = new Stack<>();
        int index = 0;
        while (index < traversal.length()) {
            /*
             * 1. Count the number of dashes
             * 2. Extract the value
             * 3. Create the current node
             * 4. Adjust the stack depth
             * 5. Attach the current node to its parent
             * 6. Push the current node onto the stack
             */

            // 1. Count the number of dashes to find the depth
            int currentDepth = 0;
            while (index < traversal.length() && traversal.charAt(index) == '-') {
                currentDepth++;
                index++;
            }

            // 2. Extract the value
            int value = 0;
            while (index < traversal.length() && Character.isDigit(traversal.charAt(index))) {
                value = value * 10 + traversal.charAt(index) - '0'; // left shift and add
                index++; 
            }

            // 3. Create the current node
            TreeNode currentNode = new TreeNode(value);

            // 4. Adjust the stack depth
            while (treenodeStack.size() > currentDepth) {
                treenodeStack.pop();
            }

            // 5. Attach the current node to its parent
            if (!treenodeStack.isEmpty()) {
                if (treenodeStack.peek().left == null) {
                    treenodeStack.peek().left = currentNode;
                } else {
                    treenodeStack.peek().right = currentNode;
                }
            }

            // 6. Push the current node onto the stack
            treenodeStack.push(currentNode); // This is how the rootNode (& subsequent nodes) get pushed onto the stack
        }
        // The root is the first node in the stack
        while (treenodeStack.size() > 1) {
            treenodeStack.pop();
        }
        return treenodeStack.peek(); // can pop too
    }
}
