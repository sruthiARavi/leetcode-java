/*
 *  Kth Largest Element in a Stream
 * Design a class to find the kth largest element in a stream. 
 * Note that it is the kth largest element in the sorted order, not the kth distinct element.
 * Implement KthLargest class:
   ** KthLargest(int k, int[] nums) Initializes the object with the integer k and the stream of integers nums.
   ** int add(int val) Appends the integer val to the stream and returns the element representing the kth largest element in the stream.
 */

class Leetcode703 {
    private int kValue;
    private PriorityQueue<Integer> pq;

    public KthLargest(int k, int[] nums) {
        this.kValue = k;
        this.pq = new PriorityQueue();
        for (int num : nums) {
            add(num);
        }
    }

    public int add(int val) {
        System.out.println(pq);
        if (pq.size() < kValue || pq.peek() < val) {
            pq.add(val);
            if (pq.size() > kValue) {
                pq.remove();
            }
        }
        return pq.peek();
    }

}

/**
 * Your KthLargest object will be instantiated and called as such:
 * KthLargest obj = new KthLargest(k, nums);
 * int param_1 = obj.add(val);
 */
