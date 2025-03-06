/*
 * https://leetcode.com/problems/find-missing-and-repeated-values/?envType=daily-question&envId=2025-03-06
 * 2965. Find Missing and Repeated Values
 * You are given a 0-indexed 2D integer matrix grid of size n * n with values in the range [1, n2]. 
 * Each integer appears exactly once except a which appears twice and b which is missing. 
 * The task is to find the repeating and missing numbers a and b.
 * Return a 0-indexed integer array ans of size 2 where ans[0] equals to a and ans[1] equals to b.
 */
class Leetcode2965 {
    public int[] findMissingAndRepeatedValues(int[][] grid) {
        int[] result = new int[]{-1, -1};
        Map<Integer, Integer> frequencyMap = new HashMap<>();
        for (int[] row : grid) {
            for (int elt : row) {
                frequencyMap.put(elt, frequencyMap.getOrDefault(elt, 0) + 1);
            }
        }
        for (int i = 1; i <= grid.length * grid.length; i++) {
            if (!frequencyMap.containsKey(i)) {
                result[1] = i;
            } else if (frequencyMap.get(i) == 2) {
                result[0] = i;
            }
        }
        return result;
    }
}
