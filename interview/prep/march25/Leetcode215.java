/*
 * 215. Kth Largest Element in an Array
 * Given an integer array nums and an integer k, return the kth largest element in the array.
 * Note that it is the kth largest element in the sorted order, not the kth distinct element.
 * Can you solve it without sorting?
 * Constraints:
   ** 1 <= k <= nums.length <= 105
   ** -104 <= nums[i] <= 104
 */
class Leetcode215 {
    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();// keep only k elets in min heap
        for (int num : nums) {
            minHeap.add(num); // natural ordering maintained in pq
            if (minHeap.size() > k) {
                minHeap.remove();
            }
        }

        return minHeap.peek(); // since we need the kth largest
    }
}
