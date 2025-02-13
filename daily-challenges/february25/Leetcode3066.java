/*
 * 3066. Minimum Operations to Exceed Threshold Value II
 * You are given a 0-indexed integer array nums, and an integer k.
 * In one operation, you will:
   ** Take the two smallest integers x and y in nums.
   ** Remove x and y from nums.
   ** Add min(x, y) * 2 + max(x, y) anywhere in the array.
 * Note that you can only apply the described operation if nums contains at least two elements.
 * Return the minimum number of operations needed so that all elements of the array are greater than or equal to k.
 * The input is generated such that an answer always exists. 
 * That is, there exists some sequence of operations after which all elements of the array are greater than or equal to k.
 */
class Leetcode3066 {
    public int minOperations(int[] nums, int k) {
        PriorityQueue<Long> minHeap = new PriorityQueue<Long>();
        for (int num : nums) {
            minHeap.add((long) num);
        }
        int numOfOperations = 0;
        while (minHeap.size() >= 2 && minHeap.peek() < k) { // The input is generated such that an answer always exists.
            long l1 = minHeap.poll();
            long l2 = minHeap.poll();
            long result = Math.min(l1, l2) * 2 + Math.max(l1, l2);
            numOfOperations++;
            minHeap.add(result);
        }
        return numOfOperations;
    }
}
