/*
 * 2551. Put Marbles in Bags
 * You have k bags. You are given a 0-indexed integer array weights where weights[i] is the weight of the ith marble. 
 * You are also given the integer k.
 * Divide the marbles into the k bags according to the following rules:
   ** No bag is empty.
   ** If the ith marble and jth marble are in a bag, then all marbles with an index between the ith and jth indices should also be in that same bag.
   ** If a bag consists of all the marbles with an index from i to j inclusively, then the cost of the bag is weights[i] + weights[j].
 * The score after distributing the marbles is the sum of the costs of all the k bags.
 * Return the difference between the maximum and minimum scores among marble distributions.
 * Constraints:
   ** 1 <= k <= weights.length <= 105
   ** 1 <= weights[i] <= 109
 */
class Leetcode2551 {
    public long putMarbles(int[] weights, int k) {
        //TODO : Quickselect, instead of sorting the entire array
        /*
         * beginning of the array and end of the array definitely in consideration 
         * k-1 pairs' weights are needed for result calculation 
         * If we have a sorted list of pair sums, which follows the i<j rule, 
         * we can calculate max and min value from this  
         */
        int len = weights.length;
        int[] pairSums = new int[len - 1];
        for (int i = 0; i < len - 1; i++) { //range is 0 to n-1 because we still need to include those as cut points
            //To clarify, if one cut point is [1] then weight would be 1+1 of which leftmost we already calculate but for rightmost, it has to come from this pairsum array             
            pairSums[i] = weights[i] + weights[i + 1];
        }
        Arrays.sort(pairSums);

        long minVal = weights[0] + weights[len - 1];
        long maxVal = minVal;

        for (int i = 0; i < k - 1; i++) {
            minVal += pairSums[i];
            maxVal += pairSums[pairSums.length - 1 - i];
        }

        return maxVal - minVal;
    }
}
