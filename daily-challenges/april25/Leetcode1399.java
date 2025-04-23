/*
 * 1399. Count Largest Group
 * You are given an integer n.
 * Each number from 1 to n is grouped according to the sum of its digits.
 * Return the number of groups that have the largest size.
 */

class Leetcode1399 {
    public int countLargestGroup(int n) {
        int[] digitSumCount = new int[40];
        int maxGroupSize = 0;
        int count = 0; 

        for (int i = 1; i <= n; i++) {
            int sum = findSumOfDigits(i);
            digitSumCount[sum]++;
            if(maxGroupSize < digitSumCount[sum]) {
                count = 1; 
                maxGroupSize = digitSumCount[sum];
            } else if(maxGroupSize == digitSumCount[sum]) {
                count++; 
            }
        }

        return count; 
    }

    public int countLargestGroupUsingArray(int n) {
        int[] digitSumCount = new int[40];
        int maxGroupSize = 0;

        for (int i = 1; i <= n; i++) {
            int sum = findSumOfDigits(i);
            digitSumCount[sum]++;
            maxGroupSize = Math.max(maxGroupSize, digitSumCount[sum]);
        }

        int count = 0;
        for (int size : digitSumCount) {
            if (size == maxGroupSize) {
                count++;
            }
        }

        return count;
    }

    public int countLargestGroupUsingMap(int n) {
        Map<Integer, Integer> digitSumVsCount = new HashMap<>();
        int maxGroupSize = 0;
        for (int i = 1; i <= n; i++) {
            int sum = findSumOfDigits(i);
            int groupSize = digitSumVsCount.getOrDefault(sum, 0) + 1;
            maxGroupSize = Math.max(maxGroupSize, groupSize);
            digitSumVsCount.put(sum, groupSize);
        }

        int count = 0;
        for (Integer key : digitSumVsCount.keySet()) {
            int value = digitSumVsCount.get(key);
            if (value == maxGroupSize) {
                count++;
            }
        }
        return count;
    }

    int findSumOfDigits(int num) {
        int sum = 0;
        while (num > 0) {
            sum += num % 10;
            num /= 10;
        }
        return sum;
    }
}
