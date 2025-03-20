/*
 * 3191. Minimum Operations to Make Binary Array Elements Equal to One I
 * You are given a binary array nums.
 * You can do the following operation on the array any number of times (possibly zero):
   ** Choose any 3 consecutive elements from the array and flip all of them.
 * Flipping an element means changing its value from 0 to 1, and from 1 to 0.
 * Return the minimum number of operations required to make all elements in nums equal to 1. If it is impossible, return -1.
 */
class Leetcode3191 {
    public int minOperations(int[] nums) {
        // Sliding window
        // Since we have to change in triplets, start at index 2 (third element)
        int arr_length = nums.length;
        if (arr_length < 3) {
            return -1;
        }        
        /* Adds to the time 
        if (Arrays.stream(nums).noneMatch(x -> x == 0)) {
            return 0;
        } 
         */
        int count = 0;
        for (int i = 2; i < arr_length; i++) {
            if (nums[i - 2] == 0) {
                count++;
                nums[i - 2] = 1;
                nums[i - 1] ^= 1; // it's a less costly operation to just xor with 1
                nums[i] ^= 1;
            }
        }
        if (nums[arr_length - 2] == 0 || nums[arr_length - 1] == 0) {
            return -1;
        }
        return count;
    }
}
