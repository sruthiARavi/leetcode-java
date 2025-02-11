class Solution {
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
