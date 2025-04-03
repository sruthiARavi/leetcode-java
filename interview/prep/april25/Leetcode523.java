/*
 * 523. Continuous Subarray Sum
 * Given an integer array nums and an integer k, return true if nums has a good subarray or false otherwise.
 * A good subarray is a subarray where:
   ** its length is at least two, and
   ** the sum of the elements of the subarray is a multiple of k.
 * Note that:
   ** A subarray is a contiguous part of the array.
   ** An integer x is a multiple of k if there exists an integer n such that x = n * k. 0 is always a multiple of k.
 * Constraints:
   ** 1 <= nums.length <= 105
   ** 0 <= nums[i] <= 109
   ** 0 <= sum(nums[i]) <= 231 - 1
   ** 1 <= k <= 231 - 1
 * Also remember, https://github.com/sruthiARavi/leetcode-java/commit/9878114564cfc4b9697db59b6c9326fbcb79b461
 */
class Leetcode523 {
    public boolean checkSubarraySum(int[] nums, int k) {
        int prefixSum = 0;
        Map<Integer, Integer> remainderVsIdx = new HashMap<>();
        remainderVsIdx.put(0,-1);
        for (int i = 0; i < nums.length; i++) {
            prefixSum += nums[i];
            int remainder = prefixSum % k;
            if (remainderVsIdx.containsKey(remainder) && i - remainderVsIdx.get(remainder) >= 2) {
                return true;
            } else if (!remainderVsIdx.containsKey(remainder)) {
                remainderVsIdx.put(remainder, i);
            }
        }
        return false;
    }
}
