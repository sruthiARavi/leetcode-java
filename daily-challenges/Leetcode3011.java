/*
 * 3011. Find if Array Can Be Sorted
 * You are given a 0-indexed array of positive integers nums.
 * In one operation, you can swap any two adjacent elements if they have the same number of set bits. 
   * A set bit refers to a bit in the binary representation of a number that has a value of 1.
 * You are allowed to do this operation any number of times (including zero).
 * Return true if you can sort the array, else return false.
 */
class Leetcode3011 {
    public boolean canSortArray(int[] nums) {
        int[] inputArray = Arrays.copyOf(nums, nums.length); // To avoid modifying the input array directly
        // Using bubble sort. Need to refine this further.
        for (int i = 0; i < inputArray.length; i++) {
            for (int j = 0; j < inputArray.length - i - 1; j++) {
                if (inputArray[j] <= inputArray[j + 1]) {
                    continue;
                }
                if (Integer.bitCount(inputArray[j]) == Integer.bitCount(inputArray[j + 1])) {
                    int temp = inputArray[j];
                    inputArray[j] = inputArray[j + 1];
                    inputArray[j + 1] = temp;
                } else {
                    return false; // Value is greater but swap is not possible per the given conditions. Therefore, return false.
                }
            }
        }
        return true;
    }
}
