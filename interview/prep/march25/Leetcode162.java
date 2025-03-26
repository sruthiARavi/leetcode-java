/*
 * 162. Find Peak Element
 * A peak element is an element that is strictly greater than its neighbors.
 * 
 * Given a 0-indexed integer array nums, find a peak element, and return its index. 
 * If the array contains multiple peaks, return the index to any of the peaks.
 *
 * You may imagine that nums[-1] = nums[n] = -∞. 
 * In other words, an element is always considered to be strictly greater than a neighbor that is outside the array.
 * 
 * You must write an algorithm that runs in O(log n) time.
 * 
 * Constraints:
   ** 1 <= nums.length <= 1000
   ** -231 <= nums[i] <= 231 - 1
   ** nums[i] != nums[i + 1] for all valid i.
 */
class Leetcode162 {
    public int findPeakElement(int[] nums) {
        /*
         * We can view any given sequence in nums array as alternating ascending and
         * descending sequences.
         * By making use of this, and the fact that we can return any peak as the
         * result,
         * we can make use of Binary Search to find the required peak element.
         */
        // Iterative bin search
        int left_idx = 0, right_idx = nums.length - 1;
        int mid = -1;
        while (left_idx < right_idx) {
            mid = (left_idx + right_idx) / 2;
            if (nums[mid] > nums[mid + 1]) {
                right_idx = mid;
            } else {
                left_idx = mid + 1;
            }
        }
        return left_idx;
    }
}
