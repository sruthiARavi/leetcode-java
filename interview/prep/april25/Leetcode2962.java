/*
 * 2962. Count Subarrays Where Max Element Appears at Least K Times
 * You are given an integer array nums and a positive integer k.
 * Return the number of subarrays where the maximum element of nums appears at least k times in that subarray.
 * A subarray is a contiguous sequence of elements within an array.
 * Constraints:
   ** 1 <= nums.length <= 105
   ** 1 <= nums[i] <= 106
   ** 1 <= k <= 105
 */

class Leetcode2962 {
    public long countSubarrays(int[] nums, int k) {
        //Find max and maybe count ? 
        int max = 0;
        int totalMaxCnt = 0;
        for (int num : nums) {
            max = Math.max(max, num);
        }

        int left = 0, right = 0; 
        long count = 0;
        int windowMaxCnt = 0;
        while (right < nums.length) {
            int num = nums[right];
            if (num == max) {
                windowMaxCnt++;
            }

            //try reducing window
            while (left <= right && windowMaxCnt >= k) {
                count += nums.length - right;
                if (nums[left] == max) {
                    windowMaxCnt--;
                }
                left++;
            }
            right++;
        }
        return count;
    }
}
