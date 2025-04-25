/*
 * 31. Next Permutation
 * A permutation of an array of integers is an arrangement of its members into a sequence or linear order.
   ** For example, for arr = [1,2,3], the following are all the permutations of arr: [1,2,3], [1,3,2], [2, 1, 3], [2, 3, 1], [3,1,2], [3,2,1].
 * The next permutation of an array of integers is the next lexicographically greater permutation of its integer. 
 * More formally, if all the permutations of the array are sorted in one container according to their lexicographical order, 
 * then the next permutation of that array is the permutation that follows it in the sorted container. 
 * If such arrangement is not possible, the array must be rearranged as the lowest possible order (i.e., sorted in ascending order).
   ** For example, the next permutation of arr = [1,2,3] is [1,3,2].
   ** Similarly, the next permutation of arr = [2,3,1] is [3,1,2].
   ** While the next permutation of arr = [3,2,1] is [1,2,3] because [3,2,1] does not have a lexicographical larger rearrangement.
 * Given an array of integers nums, find the next permutation of nums.
 * The replacement must be in place and use only constant extra memory.
 * Constraints:
   ** 1 <= nums.length <= 100
   ** 0 <= nums[i] <= 100
 */
class Leetcode31 {
    public void nextPermutation(int[] nums) {
        // https://www.youtube.com/watch?v=v5Fz177Ihow
        // Step 1: Find the first index 'i' from the end where nums[i] < nums[i + 1]
        // This identifies the pivot point where the array stops increasing
        int i = nums.length - 2;
        while (i >= 0 && nums[i] >= nums[i + 1]) {
            i--;
        }

        // Step 2: If such a pivot exists (i.e., array is not in descending order)
        if (i >= 0) {
            // Find the smallest number greater than nums[i] from the right side (nums[i+1] to end)
            int j = nums.length - 1;
            while (nums[j] <= nums[i]) {
                j--;
            }
            // Swap nums[i] with nums[j] to get a slightly larger permutation
            swap(nums, i, j);
        }

        // Step 3: Reverse the sub-array from i + 1 to end to get the next smallest lexicographic order
        // Because the right side is currently in descending order
        reverse(nums, i + 1);
    }

    // Helper function to swap elements at two indices
    void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    // Helper function to reverse the array from index 'start' to the end
    void reverse(int[] nums, int start) {
        int end = nums.length - 1;
        while (start < end) {
            swap(nums, start++, end--);
        }
    }
}
