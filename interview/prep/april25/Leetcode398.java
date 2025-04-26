/*
 * 398. Random Pick Index
 * Given an integer array nums with possible duplicates, randomly output the index of a given target number. 
 * You can assume that the given target number must exist in the array.
 * Implement the Solution class:
   ** Solution(int[] nums) Initializes the object with the array nums.
   ** int pick(int target) Picks a random index i from nums where nums[i] == target. 
 * If there are multiple valid i's, then each index should have an equal probability of returning.
 *
 * Constraints:
   ** 1 <= nums.length <= 2 * 104
   ** -231 <= nums[i] <= 231 - 1
   ** target is an integer from nums.
   ** At most 104 calls will be made to pick.
 */

import java.util.Random;
import java.util.Arrays;

class Leetcode398 {

    int[] arr;
    Map<Integer, List<Integer>> targetVsIndices;
    Random rand;

    public Solution(int[] nums) {
        rand = new Random();
        targetVsIndices = new HashMap<Integer, List<Integer>>();
        this.arr = nums;

        /*for (int i = 0; i < nums.length; i++) {
            targetVsIndices.computeIfAbsent(nums[i], k -> new ArrayList<Integer>()).add(i);
        }*/
    }

    public int pick(int target) {
        return pick_ReservoidSampling(target); 
        //return pick_Map(target);
        //return pick_Brute(target); 
    }

    public int pick_reservoirSampling_randIdxOfMax() {
        //variant 2 : pick a random idx of the max number. Use constant space, O(n) time. values in range 100, 100 and nums len is 1 - 2*10^4
        int max = Integer.MIN_VALUE;
        int pickIdx = -1;
        int totalNumOfElts = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
                totalNumOfElts = 1;
                pickIdx = i;
            } else if (arr[i] == max) {
                totalNumOfElts++;
                if (rand.nextInt(totalNumOfElts) == 0) {
                    pickIdx = i;
                }
            }
        }
        return pickIdx;
    }

    public int[] pick_ReservoidSampling_Multi(int target, int k) {
        //This can also be used for variation : output k random elts from arr. However, if you want to avoid duplication, tracking using is set is needed which occupies O(k) space 
        int[] reservoir = new int[k];
        int count = 0;
        int filled = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == target) {
                count++;
                if (filled < k) {
                    reservoir[filled++] = i;
                } else {
                    int idx = rand.nextInt(count);
                    if (idx < k) {
                        reservoir[idx] = i;
                    }
                }
            }
        }
        return (filled < k) ? Arrays.copyOf(reservoir, filled) : reservoir;
    }
  
    public int pick_ReservoidSampling(int target) {
        /*
         * Reservoir sampling : 
         * How it works (for picking 1 item):
         * Take the first item — keep it.
         * For each new item (2nd, 3rd, 4th, ...):
            * Pick it with 1/n chance (n = current item number).
            * If you pick it, replace the previous one.
            * This way, every item has a 1/n chance of being the final pick.
         */
        int count = 0; 
        int idx = 0; 
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == target) {
                count++; 
                if(rand.nextInt(count) == 0) {
                    idx = i; 
                }
            }
        } 
        return idx;
    }

    public int pick_Map(int target) {
        List<Integer> targetIndices = targetVsIndices.get(target);
        int randIdx = rand.nextInt(targetIndices.size());
        return targetIndices.get(randIdx);
    }

    public int pick_Brute(int target) {
        List<Integer> targetIndices = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == target) {
                targetIndices.add(i);
            }
        }
        int randIdx = rand.nextInt(targetIndices.size());
        return targetIndices.get(randIdx);
    }
}

/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(nums);
 * int param_1 = obj.pick(target);
 */
