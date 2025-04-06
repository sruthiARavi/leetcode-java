/*
 * 34. Find First and Last Position of Element in Sorted Array
 * Given an array of integers nums sorted in non-decreasing order, find the starting and ending position of a given target value.
 * If target is not found in the array, return [-1, -1].
 * You must write an algorithm with O(log n) runtime complexity.
 * Constraints:
   ** 0 <= nums.length <= 105
   ** -109 <= nums[i] <= 109
   ** nums is a non-decreasing array.
   ** -109 <= target <= 109
 */
class Leetcode34 {
    int modifiedBinSearch(int[] nums, int target, boolean findFirst) {
        int left = 0, mid = 0, right = nums.length - 1;
        while (left <= right) {
            mid = left + (right - left) / 2;
            if (nums[mid] > target) {
                right = mid - 1;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else if (nums[mid] == target) {
                if (findFirst) {
                    if (mid == left || nums[mid - 1] != target) {
                        return mid;
                    } else {
                        right = mid - 1;
                    }
                } else { //findLast
                    if (mid == right || nums[mid + 1] != target) {//meaning target is at the end of the array
                        return mid;
                    } else {
                        left = mid + 1;
                    }
                }
            }
        }
        return -1;
    }

    public int[] searchRange(int[] nums, int target) {
        //binary search
        int beginIdx = modifiedBinSearch(nums, target, true);
        if (beginIdx == -1) {
            return new int[] { -1, -1 };
        }
        int endIdx = modifiedBinSearch(nums, target, false);
        return new int[] { beginIdx, endIdx };
    }

}
