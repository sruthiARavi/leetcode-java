/*
 * 1295. Find Numbers with Even Number of Digits
 * Given an array nums of integers, return how many of them contain an even number of digits.  
 * Constraints:
   ** 1 <= nums.length <= 500
   ** 1 <= nums[i] <= 105
 */
class Leetcode1295 {
    public int findNumbers(int[] nums) {
        int count = 0;
        for (int num : nums) {
            int digitCnt = 0;
            while (num > 0) {
                num /= 10;
                digitCnt++;
            }
            if (digitCnt % 2 == 0) {
                count++;
            }
        }
        return count;
    }

    public int findNumbersUsingString(int[] nums) {
        int count = 0;
        for (int num : nums) {
            String s = num + "";
            if (s.length() % 2 == 0) {
                count++;
            }
        }
        return count;
    }
}
