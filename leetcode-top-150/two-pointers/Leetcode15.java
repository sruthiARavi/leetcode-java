/*
 * 15. 3Sum
 * Given an integer array nums, 
 * return all the triplets [nums[i], nums[j], nums[k]] such that i != j, i != k, and j != k, and nums[i] + nums[j] + nums[k] == 0.
 * Notice that the solution set must not contain duplicate triplets.
 */
class Leetcode15 {
    public List<List<Integer>> threeSum(int[] nums) {
        Set<List<Integer>> s = new HashSet<>();
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            //Variation : Commented out parts ensure that there are no duplicate triplets 
            /*if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }*/
            int num1 = nums[i];
            int left = i + 1;
            int right = nums.length - 1;
            while (left < right && right < nums.length) {
                /*if (nums[left] == num1) {
                    left++;
                    continue;
                } else if (nums[right] == num1 || nums[left] == nums[right]) {
                    right--;
                    continue;
                }*/
                int sum = nums[left] + nums[right];
                if (sum + num1 == 0) {
                    List<Integer> res = Arrays.asList(num1, nums[left], nums[right]);
                    //if(!result.contains(res)) {
                        s.add(res);
                    //}
                    left++;
                    right--;
                } else if (sum + num1 > 0) {
                    right--;
                } else {
                    left++;
                }
            }
        }
        result.addAll(s); 
        return result;
        /*
         * Sliding window - TLE 
        List<List<Integer>> result = new ArrayList<>();
        int sum = nums[0] + nums[1] + nums[2];
        if (sum == 0) {
            List<Integer> res = new ArrayList<>() {{
                add(0);
                add(1);
                add(2);
            }};
            result.add(res);
        }
        int i = 1;
        int j = 3;
        while (i < j && j < nums.length) {
            sum = sum - nums[i - 1] + nums[j];
            if (sum == 0) {
                List<Integer> res = new ArrayList<>() {{
                    add(0);
                    add(1);
                    add(2);
                }};
                result.add(res);
            }
        }
        return result; 
        */
    }
}
