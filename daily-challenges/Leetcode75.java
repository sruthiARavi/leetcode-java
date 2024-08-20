/*
 * Sort Colors
 * Given an array nums with n objects colored red, white, or blue, sort them in-place so that objects of the same color are adjacent, with the colors in the order red, white, and blue.
 * We will use the integers 0, 1, and 2 to represent the color red, white, and blue, respectively.
 * You must solve this problem without using the library's sort function.
 */
class Leetcode75 {
    public void sortColors(int[] nums) {
        // Algo : Counting sort
        // Counting
        int[] colorCount = new int[3]; // 0=red, 1=white, 2=blue and in that order
        for (int i = 0; i < nums.length; i++) {
            colorCount[nums[i]]++;
        }
        // end index finding
        int previous = 0;
        for (int i = 0; i < colorCount.length; i++) {
            colorCount[i] += previous;
            previous = colorCount[i];
        }
        // start index assignment
        for (int i = colorCount.length - 1; i > 0; i--) {
            colorCount[i] = colorCount[i - 1];
        }
        colorCount[0] = 0;
        // input array rearrangement
        int index = nums.length - 1;
        for (int i = colorCount.length - 1; i >= 0; i--) {
            while (index >= colorCount[i]) {
                nums[index] = i;
                index--;
            }
            ;
        }
    }  
}
