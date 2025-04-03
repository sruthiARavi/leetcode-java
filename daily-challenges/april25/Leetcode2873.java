/*
 * 2873. Maximum Value of an Ordered Triplet I 
 * You are given a 0-indexed integer array nums.
 * Return the maximum value over all triplets of indices (i, j, k) such that i < j < k. If all such triplets have a negative value, return 0.
 * The value of a triplet of indices (i, j, k) is equal to (nums[i] - nums[j]) * nums[k].
 * Constraints:
   ** 3 <= nums.length <= 100
   ** 1 <= nums[i] <= 106
 * https://www.youtube.com/watch?v=sFY_14A28x0
 */
class Leetcode2873 {
    public long maximumTripletValue(int[] nums) {
        int n = nums.length;
        //For result to be max, we need i and k max, with j min 
        //Alternatively, we need i-j to be max and k to be max 
        //So, we are going to try for iMax, i-j max and enumerate for k 
        long result = 0L, iMax = 0L, diffMax = 0L;
        for (int num : nums) {
            //determine result first, diffMax second and iMax third since i<j<k and we are enumerating for k
            result = Math.max(result, diffMax * num);
            diffMax = Math.max(diffMax, iMax - num);
            iMax = Math.max(iMax, num);
        }
        return result;
    }

    public long maximumTripletValueGreedyn2(int[] nums) {
        int n = nums.length;
        long result = 0L;
        int left = nums[0];
        for (int j = 1; j < n; j++) {
            if (nums[j] > left) {
                left = nums[j];
                continue;
            }
            for (int k = j + 1; k < n; k++) {
                result = Math.max(result, (long) (left - nums[j]) * nums[k]);
            }
        }
        return result;
    }

    public long maximumTripletValueBrute(int[] nums) {
        int n = nums.length;
        long result = 0L;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                for (int k = j + 1; k < n; k++) {
                    result = Math.max(result, (long) (nums[i] - nums[j]) * nums[k]);
                }
            }
        }
        return result;
    }
}
