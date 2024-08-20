/* 
 * Sort Array by Increasing Frequency
 * Given an array of integers nums, sort the array in increasing order based on the frequency of the values. 
 * If multiple values have the same frequency, sort them in decreasing order.
 * Return the sorted array.
 */
class Leetcode1636 {
    public int[] frequencySort(int[] nums) {
        HashMap<Integer, Integer> numVsFreq = new HashMap<>();
        for (int num : nums) {
            numVsFreq.put(num, (numVsFreq.getOrDefault(num, 0)) + 1);
        }

        Integer[] numsObj = new Integer[nums.length];
        for (int i = 0; i < nums.length; i++) {
            numsObj[i] = nums[i];
        }

        Arrays.sort(numsObj, (a, b) -> {
            if (numVsFreq.get(a).equals(numVsFreq.get(b))) {
                return Integer.compare(b, a);
            }
            return Integer.compare(numVsFreq.get(a), numVsFreq.get(b));
        });

        for (int i = 0; i < nums.length; i++) {
            nums[i] = numsObj[i];
        }

        return nums;
    }
}
