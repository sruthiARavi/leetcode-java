/*
 * 2176. Count Equal and Divisible Pairs in an Array
 * Given a 0-indexed integer array nums of length n and an integer k, 
 * return the number of pairs (i, j) where 0 <= i < j < n, 
 * such that nums[i] == nums[j] and (i * j) is divisible by k.
 * 
 * Constraints:
   ** 1 <= nums.length <= 100
   ** 1 <= nums[i], k <= 100
 */
class Leetcode2176 {
    public int countPairsBruteForce(int[] nums, int k) {
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] == nums[j] && (i * j) % k == 0) {
                    count++;
                }
            }
        }
        return count;
    }
}
