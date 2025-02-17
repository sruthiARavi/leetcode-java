/*
 * https://leetcode.com/problems/check-if-array-is-sorted-and-rotated/description/?envType=daily-question&envId=2025-02-02
 * 1752. Check if Array Is Sorted and Rotated
 * Given an array nums, return true if the array was originally sorted in non-decreasing order, 
 * then rotated some number of positions (including zero). Otherwise, return false.
 * There may be duplicates in the original array.
 * Note: An array A rotated by x positions results in an array B of the same length 
 * such that B[i] == A[(i+x) % A.length] for every valid index i.
 */
class Leetcode1752 {
    public boolean check(int[] nums) {
        /*
         * If nums size <=1 return true
         * Compare nums[i] and nums[i-1] to see if there are instances where,
         * current element lesser than preceding element.
         * This could mean that this is the point where the inversion happened.
         * Also check nums[0] with nums[n-1] for rotation case.
         * If more than one instance of inversion seems to have occurred, then return
         * false.
         * Return true otherwise.
         */
        int inversions = 0;
        for (int i = 1; i < nums.length; ++i) {
            if (nums[i] < nums[i - 1]) {
                ++inversions;
            }
        }
        if (nums[0] < nums[nums.length - 1]) {
            ++inversions;
        }
        return inversions <= 1;
    }
}
