/*
 * Minimum Swaps to Group All 1's Together II
 * A swap is defined as taking two distinct positions in an array and swapping the values in them.
 * A circular array is defined as an array where we consider the first element and the last element to be adjacent.
 * Given a binary circular array nums, return the minimum number of swaps required to group all 1's present in the array together at any location.
 */
class Leetcode2134 {
    public int minSwaps(int[] nums) {
        /*
         * https://www.youtube.com/watch?v=Jr-vAGyEuMg 
         * Use sliding window (single pointer). Window size is constant i.e. total no.
         * of ones in the array.
         * Total no. of zeroes in the window is the total no. of swaps needed so count
         * that.
         * For circular array, we can either have a copy or we can simply use index %
         * array length.
         */
        int one_count = 0;
        for (int num : nums) {
            if (num == 1) {
                one_count++;
            }
        }
        if(one_count == 0 || one_count == nums.length) {
            return 0; 
        }
        int result = Integer.MAX_VALUE;
        int zeroes = 0;
        for (int rightPointer = 0; rightPointer < (nums.length + one_count - 1); rightPointer++) {
            /*
             * window size = one_count
             * rightPointer should go upto : last elt in nums array -> first (one_count - 1) elts in the array. - 1 because since arrays are 0-indexed.
             * leftPointer = rightPointer - one_count + 1 
             */ 

             // We slide by incrementing rightPointer (and taking that zero, if present, into consideration).              
            if (nums[rightPointer % nums.length] == 0) {
                zeroes++;
            }
            /* To move the leftPointer for the sliding window, 
             * we calculate the appropriate previous leftPointer (and take that zero, if present, out of consideration).
             * currentLeftPointer = rightPointer - one_count + 1. 
             * previousLeftPointer = currentLeftPointer - 1. 
             * So, previousLeftPointer = rightPointer - one_count.
             */
            if (rightPointer >= one_count && nums[rightPointer - one_count] == 0) {
                zeroes--;
            }
            //We only start comparing with result / decrementing zeroes, when the appropraite window size has been reached. We don't do anything until then.
            if (rightPointer >= one_count - 1) {
                result = Math.min(result, zeroes);
            }
        }
        return result;
    }
}
