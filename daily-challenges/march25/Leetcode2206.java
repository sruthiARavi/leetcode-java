/*
 * https://leetcode.com/problems/divide-array-into-equal-pairs/description/?envType=daily-question&envId=2025-03-17
 * 2206. Divide Array Into Equal Pairs
 * You are given an integer array nums consisting of 2 * n integers.
 * You need to divide nums into n pairs such that:
   ** Each element belongs to exactly one pair.
   ** The elements present in a pair are equal.
 * Return true if nums can be divided into n pairs, otherwise return false.
 */

class Leetcode2206 {
    public boolean divideArray(int[] nums) {
        if (nums.length % 2 != 0) {
            return false;
        }
        HashSet<Integer> nums_set = new HashSet<>();
        for (Integer num : nums) {
            if (nums_set.contains(num)) {
                nums_set.remove(num);
                continue;
            }
            nums_set.add(num);
        }
        return nums_set.isEmpty();
    }
}
