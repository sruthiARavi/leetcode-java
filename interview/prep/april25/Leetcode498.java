/*
 * 498. Diagonal Traverse
 * Given an m x n matrix mat, return an array of all the elements of the array in a diagonal order.
 */
class Leetcode498 {
    public int[] findDiagonalOrder(int[][] mat) {
        Map<Integer, List<Integer>> result = new TreeMap<>();
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[0].length; j++) {
                result.computeIfAbsent(i + j, k -> new ArrayList<Integer>()).add(mat[i][j]);
            }
        }
        int[] ans = new int[mat.length * mat[0].length];
        int idx = 0;
        for (Integer key : result.keySet()) {
            List<Integer> diagonalElts = result.get(key); 
            if(key%2 ==0) {
                Collections.reverse(diagonalElts); 
            }
            for (int num : diagonalElts) {
                ans[idx] = num;
                idx++;
            }
        }
        return ans;
    }
}
