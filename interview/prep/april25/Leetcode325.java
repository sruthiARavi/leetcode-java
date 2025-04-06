/*
 * 325. Maximum Size Subarray Sum Equals k
 * Given an integer array nums and an integer k, return the maximum length of a subarray that sums to k. 
 * If there is not one, return 0 instead.
 * Constraints:
   ** 1 <= nums.length <= 2 * 105
   ** -104 <= nums[i] <= 104
   ** -109 <= k <= 109
 */
class Leetcode325 {
    public int maxSubArrayLen(int[] nums, int k) {
        if (nums.length == 1) {
            if (nums[0] == k) {
                return 1;
            }
            return 0;
        }
        int maxLen = 0;
        //hashmap approach 
        Map<Integer, Integer> sumVsIdx = new HashMap<Integer, Integer>();
        sumVsIdx.put(0, -1);
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            /*
            * if there exists some subarray from i to j summing to  k in nums, then we know that
            * prefixSum[j] - prefixSum[i] = k 
            * prefixSum[j] - prefixSum[i] - k = 0
            * prefixSum[j] - k = prefixSum[i]
            */
            if (nums[i] == k) {
                maxLen = Math.max(maxLen, 1);
            }
            if (sumVsIdx.containsKey(sum - k)) {
                maxLen = Math.max(maxLen, i - sumVsIdx.get(sum - k));
            }
            if (!sumVsIdx.containsKey(sum)) {
                sumVsIdx.put(sum, i);
            }
        }
        return maxLen;
    }
}
