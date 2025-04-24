/*
 * 528. Random Pick with Weight
 * You are given a 0-indexed array of positive integers w where w[i] describes the weight of the ith index.
 * You need to implement the function pickIndex(), which randomly picks an index in the range [0, w.length - 1] (inclusive) and returns it. 
 * The probability of picking an index i is w[i] / sum(w).
 * For example, if w = [1, 3], the probability of picking index 0 is 1 / (1 + 3) = 0.25 (i.e., 25%), 
 * and the probability of picking index 1 is 3 / (1 + 3) = 0.75 (i.e., 75%).
 */
class Leetcode528 {

    //https://www.youtube.com/watch?v=cw826XIOZIg

    int[] prefixSum;
    int sum = 0;

    public Solution(int[] w) {
        prefixSum = new int[w.length];
        int idx = 0;
        for (int num : w) {
            sum += num;
            prefixSum[idx] = sum;
            idx++;
        }
    }

    public int pickIndex() {
        //binary search 
        int target = (int) (Math.random() * sum);
        int left = 0, right = prefixSum.length - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (target >= prefixSum[mid]) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left;
    }

    public int pickIndexLinearSearch() {
        int ans = -1;
        int target = (int) (Math.random() * sum); //random gives nums from 0.0 to 1.0 
        for (int i = 0; i < prefixSum.length; i++) {
            if (target < prefixSum[i]) {
                return i;
            }
        }
        return ans;
    }
}

/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(w);
 * int param_1 = obj.pickIndex();
 */
