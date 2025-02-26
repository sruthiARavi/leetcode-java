/*
 * 53. Maximum Subarray
 * Given an integer array nums, find the subarray with the largest sum, and return its sum.
 */
class Leetcode53 {
    public int maxSubArray(int[] nums) {
        //Kadane's algorithm
        int maxSubArraySum = nums[0];
        int currentSum = 0;
        for (int i = 0; i < nums.length; i++) {
            if (currentSum < 0) {
                currentSum = 0;
            }
            currentSum += nums[i];
            maxSubArraySum = Math.max(currentSum, maxSubArraySum);
        }
        return maxSubArraySum;
    }
}
