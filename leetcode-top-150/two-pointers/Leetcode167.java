/*
 * 167. Two Sum II - Input Array Is Sorted
 * Given a 1-indexed array of integers numbers that is already sorted in non-decreasing order, find two numbers such that they add up to a specific target number. 
 * Let these two numbers be numbers[index1] and numbers[index2] where 1 <= index1 < index2 <= numbers.length.
 * Return the indices of the two numbers, index1 and index2, added by one as an integer array [index1, index2] of length 2.
 * The tests are generated such that there is exactly one solution. You may not use the same element twice.
 * Your solution must use only constant extra space.
 */
class Leetcode167 {
    public int[] twoSum(int[] numbers, int target) {
        int[] result = new int[2];
        // Sorted, use two pointer
        int left = 0;
        int right = numbers.length - 1;
        while (left <= right) {
            int sum = numbers[left] + numbers[right];
            if (sum == target) {
                result[0] = left + 1;
                result[1] = right + 1;
                break;
            }
            if (sum > target) {
                right--;
            } else {
                left++;
            }
        }
        return result;
        //Brute force
        /*
        for(int i=0; i<numbers.length; i++){
            for(int j=i+1; j<numbers.length; j++){
                int sum = numbers[i] + numbers[j];
                if(sum==target){
                    result[0] = i + 1; //Since 1-indexed
                    result[1] = j + 1;
                    break;
                }
                if(sum > target) {
                    break;
                }
            }
        }
        return result;
         */
    }
}
