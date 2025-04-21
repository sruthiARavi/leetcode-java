/*
 * 1539. Kth Missing Positive Number
 * Given an array arr of positive integers sorted in a strictly increasing order, and an integer k.
 * Return the kth positive integer that is missing from this array.
 * Constraints:
   ** 1 <= arr.length <= 1000
   ** 1 <= arr[i] <= 1000
   ** 1 <= k <= 1000
   ** arr[i] < arr[j] for 1 <= i < j <= arr.length
 * Examples 
 
   ** Example 1:
     *** Input: arr = [2,3,4,7,11], k = 5
     *** Output: 9
     *** Explanation: The missing positive integers are [1,5,6,8,9,10,12,13,...]. The 5th missing positive integer is 9.
     
   ** Example 2:
     *** Input: arr = [1,2,3,4], k = 2
     *** Output: 6
     *** Explanation: The missing positive integers are [5,6,7,...]. The 2nd missing positive integer is 6.
 */
class Leetcode1539 {

    public int findKthPositive(int[] arr, int k) {
        //Since it is sorted, use bin search 
    }

    public int findKthPositiveBrute(int[] arr, int k) {
        int missingCount = 0, currentNum = 1, idx = 0;
        while (true) {
            if (idx < arr.length && arr[idx] == currentNum) {
                idx++;
            } else {
                missingCount++;
                if (missingCount == k) {
                    return currentNum;
                }
            }
            currentNum++;
        }
    }

    public int findKthPositiveBrute1(int[] arr, int k) {
        List<Integer> nums = IntStream.range(1, 10001).boxed().collect(Collectors.toList());
        for (int a : arr) {
            nums.remove(new Integer(a));
        }
        return nums.remove(k - 1);
    }
}
