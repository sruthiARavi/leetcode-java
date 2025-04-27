/*
 * 3392. Count Subarrays of Length Three With a Condition
 * Given an integer array nums, 
 * return the number of subarrays of length 3 such that the sum of the first and third numbers equals exactly half of the second number.
 * Constraints:
   ** 3 <= nums.length <= 100
   ** -100 <= nums[i] <= 100
 */
class Leetcode3392 {
    public int countSubarrays(int[] nums) {
        //only consecutive numbers are part of valid subarray
        int count = 0;
        for (int i = 0; i < nums.length - 2; i++) {
            if (nums[i + 1] == (2 * (nums[i] + nums[i + 2]))) {
                count++;
            }
        }
        return count;
    }
}
