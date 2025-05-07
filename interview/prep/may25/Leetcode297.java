/*
 * 297. Serialize and Deserialize Binary Tree
 * Serialization is the process of converting a data structure or object into a sequence of bits so that 
 * it can be stored in a file or memory buffer, 
 * or transmitted across a network connection link to be reconstructed later in the same or another computer environment.
 * Design an algorithm to serialize and deserialize a binary tree. 
 * There is no restriction on how your serialization/deserialization algorithm should work. 
 * You just need to ensure that a binary tree can be serialized to a string and this string can be deserialized to the original tree structure.
 * Clarification: The input/output format is the same as how LeetCode serializes a binary tree. 
 * You do not necessarily need to follow this format, so please be creative and come up with different approaches yourself.
 */
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Leetcode297 {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if(root == null) {
            return null; 
        }
        //bfs ? 
        List<String> res = new ArrayList<>(); 
        Queue<TreeNode> q = new LinkedList<>(); 
        q.offer(root); 
        while(!q.isEmpty()) {
            TreeNode node = q.poll();
            if(node == null) {
                res.add(null); 
                continue; 
            }
            res.add(node.val+""); 
            q.offer(node.left); 
            q.offer(node.right); 
        }
        return res.toString(); 
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if(data == null) {
            return null; 
        }                
        
        data = data.substring(1, data.length()-1); 
        String[] nodes = data.split(","); 
        String n1 = nodes[0].trim(); 
        if(n1.equals("null")) {
            return null; 
        }
        n1 = n1.trim(); 
        Integer val = Integer.parseInt(n1); 
        TreeNode root = new TreeNode(val); 
        TreeNode dummy = root; 
        boolean addLeftChild = true; 
        
        Queue<TreeNode> q = new LinkedList<>(); 
        q.offer(dummy); 
        
        for(int i = 1; i<nodes.length; i++) {
            String s = nodes[i].trim(); 
            if(s.equals("null")) {
                if(addLeftChild) {
                    addLeftChild = false; 
                } else {
                    q.poll(); 
                    addLeftChild = true; 
                }      
                continue; 
            }
            TreeNode node = new TreeNode(Integer.parseInt(s)); 
            if(addLeftChild) {
                TreeNode parent = q.peek(); 
                parent.left = node; 
                q.offer(node); 
                addLeftChild = false; 
            } else {
                TreeNode parent = q.poll(); 
                parent.right = node; 
                q.offer(node); 
                addLeftChild = true; 
            }
        }
        
        return root; 
    }
}

// Your Codec object will be instantiated and called as such:
// Codec ser = new Codec();
// Codec deser = new Codec();
// TreeNode ans = deser.deserialize(ser.serialize(root));
