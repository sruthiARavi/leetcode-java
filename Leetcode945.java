/*
 * Minimum Increment to Make Array Unique
 * You are given an integer array nums. 
 * In one move, you can pick an index i where 0 <= i < nums.length and increment nums[i] by 1.
 * Return the minimum number of moves to make every value in nums unique.
 * The test cases are generated so that the answer fits in a 32-bit integer.
 */
class Leetcode945 {
    public int minIncrementForUnique(int[] nums) {
        int minIncrements = 0;
        Arrays.sort(nums);
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] <= nums[i - 1]) {
                minIncrements += (nums[i - 1] + 1 - nums[i]);
                nums[i] = nums[i - 1] + 1;
            }
        }
        return minIncrements;
    }
}
