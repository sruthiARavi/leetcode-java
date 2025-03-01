/*
 * https://leetcode.com/problems/apply-operations-to-an-array/description/?envType=daily-question&envId=2025-03-01
 * 2460. Apply Operations to an Array
 * You are given a 0-indexed array nums of size n consisting of non-negative integers.
 * You need to apply n - 1 operations to this array where, in the ith operation (0-indexed), 
 * you will apply the following on the ith element of nums:
   ** If nums[i] == nums[i + 1], then multiply nums[i] by 2 and set nums[i + 1] to 0. Otherwise, you skip this operation.
 * After performing all the operations, shift all the 0's to the end of the array.
   ** For example, the array [1,0,2,0,0,1] after shifting all its 0's to the end, is [1,2,1,0,0,0].
 * Return the resulting array.
 * Note that the operations are applied sequentially, not all at once.
 */
class Leetcode2460 {
    public int[] applyOperations(int[] nums) {
        // Step 1: Apply operations on the array
        for (int index = 0; index < nums.length - 1; index++) {
            if (nums[index] == nums[index + 1] && nums[index] != 0) {
                nums[index] *= 2;
                nums[index + 1] = 0;
            }
        }

        // Step 2: Shift non-zero elements to the beginning
        int nonZeroIndex = 0;
        for (int iterateIndex = 0; iterateIndex < nums.length; iterateIndex++) {
            if (nums[iterateIndex] != 0) {
                nums[nonZeroIndex++] = nums[iterateIndex];
            }
        }

        // Step 3: Fill the remaining positions with zeros
        while (nonZeroIndex < nums.length) {
            nums[nonZeroIndex++] = 0;
        }
        return nums;
    }
}
