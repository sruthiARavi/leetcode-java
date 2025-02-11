/*
 * 
 * Given an array of positive integers nums, return the maximum possible sum of an ascending subarray in nums.
 * A subarray is defined as a contiguous sequence of numbers in an array.
 * A subarray [numsl, numsl+1, ..., numsr-1, numsr] is ascending if for all i where l <= i < r, numsi  < numsi+1. 
 * Note that a subarray of size 1 is ascending.
 */
class Leetcode1800 {
    public int maxAscendingSum(int[] nums) {
        // Below strategy only works if strictly positive nums are present in the array
        int maxSum = 0;
        int currentSubArraySum = nums[0];
        for (int i = 1; i < nums.length; ++i) {
            if (nums[i] <= nums[i - 1]) {
                maxSum = Math.max(currentSubArraySum, maxSum);
                currentSubArraySum = 0;
            }
            currentSubArraySum += nums[i];
        }
        return Math.max(currentSubArraySum, maxSum);
    }
}
