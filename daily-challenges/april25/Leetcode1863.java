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
    public int subsetXORSum(int[] nums) {
        //Check the neetcode video for this - https://www.youtube.com/watch?v=HToBFhTa1uQ
        //Multiplying a number by 2^x is the same as left shifting it by 2^x
        int or_num = 0;
        for (int i = 0; i < nums.length; i++) {
            //We perform OR operation so that we get the or_num bit set if at least one of the numbers in nums has a corresponding bit set in it
            or_num |= nums[i];
        }
        return or_num << nums.length - 1; // This is the same as or_num * (2^(nums.length-1))
    }

    //recursive call - O(2^n since 2^n subsets would be there which led to 2^n calls
    int dfs(int i, int[] nums, int total) {
        if (i == nums.length) {
            return total;
        }
        return dfs(i + 1, nums, total ^ nums[i]) //include this num in subset
                + dfs(i + 1, nums, total); //don't include this num in the subset
    }

    public int subsetXORSumRecursive(int[] nums) {
        return dfs(0, nums, 0);
    }
}
