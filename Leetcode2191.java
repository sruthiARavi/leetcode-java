/*
 * Sort the Jumbled Numbers
 * You are given a 0-indexed integer array mapping which represents the mapping rule of a shuffled decimal system. mapping[i] = j means digit i should be mapped to digit j in this system.
 * The mapped value of an integer is the new integer obtained by replacing each occurrence of digit i in the integer with mapping[i] for all 0 <= i <= 9.
 * You are also given another integer array nums. Return the array nums sorted in non-decreasing order based on the mapped values of its elements.
 * Notes:
   ** Elements with the same mapped values should appear in the same relative order as in the input.
   ** The elements of nums should only be sorted based on their mapped values and not be replaced by them.
 */

class Leetcode2191 {
    // https://www.youtube.com/watch?v=AFis7t6U3Fk
    //TODO : Understand the Radix sort implementation 
    public int[] sortJumbled(int[] mapping, int[] nums) {
        NumMappingDetails[] numDetails = new NumMappingDetails[nums.length];

        for (int i = 0; i < nums.length; i++) {
            numDetails[i] = new NumMappingDetails(nums[i], i, findMappedNumber(nums[i], mapping));
        }

        Arrays.sort(numDetails, (a, b) -> {
            if (a.mappedNumber == b.mappedNumber) {
                return a.index - b.index;
            }
            return a.mappedNumber - b.mappedNumber;
        });

        int[] sorted = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            sorted[i] = numDetails[i].originalNumber;
        }
        return sorted;
    }

    private int findMappedNumber(int number, int[] mapping) {
        if (number == 0) {
            return mapping[0];
        }

        int place = 1;
        int result = 0;

        while (number > 0) {
            int temp = number % 10;
            result += mapping[temp] * place;
            number /= 10;
            place *= 10;
        }

        return result;
    }
}

class NumMappingDetails {
    int originalNumber;
    int index;
    int mappedNumber;

    public NumMappingDetails(int originalNumber, int index, int mappedNumber) {
        this.originalNumber = originalNumber;
        this.index = index;
        this.mappedNumber = mappedNumber;
    }
}
