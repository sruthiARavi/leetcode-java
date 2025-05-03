/*
 * 1004. Max Consecutive Ones III
 * Given a binary array nums and an integer k, return the maximum number of consecutive 1's in the array if you can flip at most k 0's.
 *  
 * Constraints:
   ** 1 <= nums.length <= 105
   ** nums[i] is either 0 or 1.
   ** 0 <= k <= nums.length
 */
class Leetcode1004 {
    public int longestOnes(int[] nums, int k) {
        int left = 0, right = 0, max = 0, zeroCount = 0;
        while (right < nums.length) {
            if (nums[right] == 0) {
                zeroCount++;
            }
            while (zeroCount > k) {
                if (nums[left] == 0) {
                    zeroCount--;
                }
                left++;
            }
            max = Math.max(max, right - left + 1);
            right++;
        }
        return max;
    }
}
