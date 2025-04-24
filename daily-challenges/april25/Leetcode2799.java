/*
 * 2799. Count Complete Subarrays in an Array
 * You are given an array nums consisting of positive integers.
 * We call a subarray of an array complete if the following condition is satisfied:
   ** The number of distinct elements in the subarray is equal to the number of distinct elements in the whole array.
 * Return the number of complete subarrays.
 * A subarray is a contiguous non-empty part of an array.
 */
class Leetcode2799 {
    public int countCompleteSubarrays(int[] nums) {
        if (nums.length == 1) {
            return 1;
        }

        Set<Integer> numsSet = Arrays.stream(nums).boxed().collect(Collectors.toSet());
        int numsDistinct = numsSet.size();
        int count = 0, left = 0, right = 0;
        Map<Integer, Integer> freqMap = new HashMap<>();
        for (right = 0; right < nums.length; right++) {
            freqMap.put(nums[right], freqMap.getOrDefault(nums[right], 0) + 1);
            while (freqMap.size() == numsDistinct) {
                count += nums.length - right; //because if curr sub array is valid, then including all the remaining elts in the array would still make it valid 

                freqMap.put(nums[left], freqMap.getOrDefault(nums[left], 0) - 1);
                if (freqMap.get(nums[left]) == 0) {
                    freqMap.remove(nums[left]);
                }
                left++;
            }
        }
        return count;
    }

  public int countCompleteSubarraysBrute(int[] nums) {
        if (nums.length == 1) {
            return 1;
        }
        Set<Integer> numsSet = Arrays.stream(nums).boxed().collect(Collectors.toSet());
        int numsDistinct = numsSet.size();
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            Set<Integer> subArrElts = new HashSet<>();
            for (int j = i; j < nums.length; j++) {
                subArrElts.add(nums[j]);
                if (subArrElts.size() == numsDistinct) {
                    count++;
                }
            }
        }
        return count;
    }
}
