/*
 * 1749. Maximum Absolute Sum of Any Subarray
 * You are given an integer array nums. 
 * The absolute sum of a subarray [numsl, numsl+1, ..., numsr-1, numsr] is abs(numsl + numsl+1 + ... + numsr-1 + numsr).
 * Return the maximum absolute sum of any (possibly empty) subarray of nums.
 * Note that abs(x) is defined as follows:
   ** If x is a negative integer, then abs(x) = -x.
   ** If x is a non-negative integer, then abs(x) = x.
 */
class Solution {
    public int maxAbsoluteSum(int[] nums) {
        /* 
         * Max absolute sum will be either the max sum or the min sum.
         * So, ust run kadane twice, once calculating the max sum and once calculating the min sum.
         */ 
        // initialising everything to 0 to consider no sub-array case
    }
}
