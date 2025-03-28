/*
 * 1650. Lowest Common Ancestor of a Binary Tree III 
 * Given two nodes of a binary tree p and q, return their lowest common ancestor (LCA).

 * Each node will have a reference to its parent node. The definition for Node is below:

  class Node {
      public int val;
      public Node left;
      public Node right;
      public Node parent;
  }
  
 * According to the definition of LCA on Wikipedia: "
 * The lowest common ancestor of two nodes p and q in a tree T is the lowest node that has 
 * both p and q as descendants (where we allow a node to be a descendant of itself)."

 * Constraints:
   ** The number of nodes in the tree is in the range [2, 105].
   ** -109 <= Node.val <= 109
   ** All Node.val are unique.
   ** p != q
   ** p and q exist in the tree.
 */
public class Leetcode1650 {
    public static void main(String[] args) {
    }

    //1650. Lowest Common Ancestor of a Binary Tree III
    static class Node {
        public int val;
        public Node left;
        public Node right;
        public Node parent;
    }

    public Node lowestCommonAncestor(Node p, Node q) {
        Node headP = p;
        Node headQ = q;

        while (headP != headQ) {
            headP = headP == null ? q : headP.parent;
            headQ = headQ == null ? p : headQ.parent;
        }

        return headP;
    }

    public Node lowestCommonAncestorExtraSpace(Node p, Node q) {
        HashSet<Node> path = new HashSet<>();
        while (p != null) {
            path.add(p);
            p = p.parent;
        }
        while (q != null) {
            if (path.contains(q)) {
                return q;
            }
            q = q.parent;
        }
        return null;
    }
}
