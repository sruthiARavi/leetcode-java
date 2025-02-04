/*
 * 3151. Special Array I
 * An array is considered special if every pair of its adjacent elements contains two numbers with different parity.
 * You are given an array of integers nums. Return true if nums is a special array, otherwise, return false.
 */
class Leetcode3151 {
    public boolean isArraySpecial(int[] nums) {
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] % 2 == nums[i + 1] % 2) {
                return false;
            }
        }
        return true;
    }
}
