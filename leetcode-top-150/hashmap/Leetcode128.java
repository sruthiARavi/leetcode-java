/*
 * 128. Longest Consecutive Sequence
 * Given an unsorted array of integers nums, return the length of the longest consecutive elements sequence.
 * You must write an algorithm that runs in O(n) time.
 */
class Leetcode128 {
    public int longestConsecutive(int[] nums) {
        if (nums.length <= 1) {
            return nums.length;
        }
        Arrays.sort(nums);
        int maxStreak = 1;
        int currStreak = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] != nums[i - 1]) {
                if (nums[i] == nums[i - 1] + 1) {
                    currStreak++;
                } else {
                    maxStreak = Math.max(currStreak, maxStreak);
                    currStreak = 1;
                }
            }
        }
        return Math.max(currStreak, maxStreak);
    }
}
