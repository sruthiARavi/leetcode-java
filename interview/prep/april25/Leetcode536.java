/*
 * 536. Construct Binary Tree from String
 * You need to construct a binary tree from a string consisting of parenthesis and integers.
 * The whole input represents a binary tree. It contains an integer followed by zero, one or two pairs of parenthesis. 
 * The integer represents the root's value and a pair of parenthesis contains a child binary tree with the same structure.
 * You always start to construct the left child node of the parent first if it exists.
 * Constraints:
   ** 0 <= s.length <= 3 * 104
   ** s consists of digits, '(', ')', and '-' only.
   ** All numbers in the tree have value at most than 230.
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
class Leetcode536 {
    public TreeNode str2tree(String s) {
        if (s.length() == 0) {
            return null;
        }

        Deque<TreeNode> stack = new ArrayDeque<>();
        int idx = 0;
        int[] rootNodeDetails = getNextNum(s, idx);
        idx = rootNodeDetails[1];
        TreeNode root = new TreeNode(rootNodeDetails[0]);
        stack.offer(root);

        /*
         * first integer is root 
         * push it into stack 
         * second is (left child)
         * keep pushing into stack and pop only whenever you encounter a closing paranthesis
         * then (right child)
         */

        while (idx < s.length()) {
            char c = s.charAt(idx);
            switch (c) {
                case '(':
                    TreeNode parent = stack.peekLast();
                    idx++;
                    int[] nextNode = getNextNum(s, idx);
                    int nodeVal = nextNode[0];
                    int updatedIdx = nextNode[1];
                    idx = updatedIdx;
                    TreeNode child = new TreeNode(nodeVal);
                    if (parent.left == null) {
                        parent.left = child;
                    } else {
                        parent.right = child;
                    }
                    stack.offer(child);
                    break;
                case ')':
                    stack.pollLast();
                    idx++;
                    break;
                default:
                    idx++; //skip unknown char
            }
        }
        return root;
    }

    int[] getNextNum(String s, int i) {
        int[] result = new int[2];
        int sign = 1;
        if (s.charAt(i) == '-') {
            sign = -1;
            i++;
        } else if (!Character.isDigit(s.charAt(i))) {
            return result;
        }

        int num = 0;
        while (i < s.length() && Character.isDigit(s.charAt(i))) {
            num = num * 10 + (s.charAt(i) - '0');
            i++;
        }
        result[0] = num * sign;
        result[1] = i;
        return result;
    }
}
