//Almost same as Leetcode 31, the meta variation being, find the previous permutation. Therefore, we just modify the 2 relevant conditions to solve it 
class Leetcode31_previous_permutation {
    public void previousPermutation(int[] nums) {
        // Step 1: Find the first index 'i' from the end where nums[i] > nums[i + 1]
        // This means nums[i] can be made smaller by rearranging the suffix
        int i = nums.length - 2;
        while (i >= 0 && nums[i] <= nums[i + 1]) {
            i--;
        }

        // Step 2: If such a pivot exists
        if (i >= 0) {
            // Find the largest number to the right of nums[i] that is smaller than nums[i]
            int j = nums.length - 1;
            while (nums[j] >= nums[i]) {
                j--;
            }
            // Swap them to make the permutation smaller
            swap(nums, i, j);
        }

        // Step 3: Reverse the suffix starting from i + 1
        // This ensures we get the largest possible order (closest smaller permutation)
        reverse(nums, i + 1);
    }

    // Swap helper
    void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    // Reverse helper
    void reverse(int[] nums, int start) {
        int end = nums.length - 1;
        while (start < end) {
            swap(nums, start++, end--);
        }
    }
}
