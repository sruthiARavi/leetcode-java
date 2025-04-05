/*
 * 1863. Sum of All Subset XOR Totals
 * The XOR total of an array is defined as the bitwise XOR of all its elements, or 0 if the array is empty.
 * For example, the XOR total of the array [2,5,6] is 2 XOR 5 XOR 6 = 1.
 * Given an array nums, return the sum of all XOR totals for every subset of nums. 
 * Note: Subsets with the same elements should be counted multiple times.
 * An array a is a subset of an array b if a can be obtained from b by deleting some (possibly zero) elements of b.
 * Constraints:
   ** 1 <= nums.length <= 12
   ** 1 <= nums[i] <= 20
 */
class Leetcode1863 {
    //recursive call
    int dfs(int i, int[] nums, int total) {
        if (i == nums.length) {
            return total;
        }
        return dfs(i + 1, nums, total ^ nums[i]) //include this num in subset
                + dfs(i + 1, nums, total); //don't include this num in the subset 
    }

    public int subsetXORSum(int[] nums) {
        return dfs(0, nums, 0);
    }
}
