/*
 * 560. Subarray Sum Equals K
 * Given an array of integers nums and an integer k, return the total number of subarrays whose sum equals to k.
 * 
 * A subarray is a contiguous non-empty sequence of elements within an array.
 *
 * Constraints:
   ** 1 <= nums.length <= 2 * 104
   ** -1000 <= nums[i] <= 1000
   ** -107 <= k <= 107
 */
class Leetcode560 {
    public int subarraySum(int[] nums, int k) {
      //Cumulative sum implementation 
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            int sum = 0;
            for (int j = i; j < nums.length; j++) { //j=i since we wanna consider self
                sum += nums[j];
                if (sum == k) {
                    count++; //We don't break here since array has both positive and negative elts
                }
            }
        }
        return count;
    }
  
    public int subarraySum1(int[] nums, int k) {
        /*
         * Cumulative sum upto idx j - cumulative sum upto idx i = k, then increment
         * count 1
         * Consequently, sum upto idx j - k = sum upto i, then also count can be
         * incremented to 1
         * Thus, store the sums in a hashmap in order to compare
         */
        HashMap<Integer, Integer> cumulativeSum = new HashMap<>();
        int count = 0;
        int sum = 0;
        // insert a 0 so that if first elt = k, then condition true and we can increment
        // count
        cumulativeSum.put(0, 1);
        for (int num : nums) {
            sum += num;
            if (cumulativeSum.containsKey(sum - k)) {
                /*
                 * For every sum, 
                 * we check the number of times (sum-k) has occured, say n. 
                 * This means that, at this index, 
                 * when included as part of the subarray, which contributes to the sum, 
                 * can combine with some previous elements in the array to form k, 
                 * which we determine by checking (sum-k) count leading to n.
                 * So, at this index, 
                 * we can get n subarrays which would add up to k.
                 * Therefore, we add n to the final count. 
                 */
                count += cumulativeSum.get(sum - k);
            }
            cumulativeSum.put(sum, cumulativeSum.getOrDefault(sum, 0) + 1);
        }
        return count;
    }
}
