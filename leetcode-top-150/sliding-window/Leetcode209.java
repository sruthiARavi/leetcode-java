/*
 * 209. Minimum Size Subarray Sum
 * Given an array of positive integers nums and a positive integer target, 
 * return the minimal length of a subarray whose sum is greater than or equal to target. 
 * If there is no such subarray, return 0 instead.
 */
class Leetcode209 {
    public int minSubArrayLen(int target, int[] nums) {
        // Sliding window
        int left = 0;
        int right = 0;
        int sum = 0; 
        int minLen = Integer.MAX_VALUE;
        for (; right < nums.length; right++) {
            sum += nums[right];

            while (sum >= target) {
                minLen = Math.min(minLen, right - left + 1);
                sum -= nums[left++];
            }
        }
        return minLen == Integer.MAX_VALUE ? 0 : minLen;
    }
}
