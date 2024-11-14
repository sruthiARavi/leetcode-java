/*
 * 11. Container With Most Water
 * You are given an integer array height of length n. 
 * There are n vertical lines drawn such that the two endpoints of the ith line are (i, 0) and (i, height[i]).
 * Find two lines that together with the x-axis form a container, such that the container contains the most water.
 * Return the maximum amount of water a container can store.
 * Notice that you may not slant the container.
 */
class Leetcode11 {
    public int maxArea(int[] height) {
        int maxArea = 0;
        int leftPointer = 0;
        int rightPointer = height.length - 1;
        while (leftPointer < rightPointer) {
            int area = (rightPointer - leftPointer) * Math.min(height[leftPointer], height[rightPointer]);
            maxArea = Math.max(maxArea, area);
            if (height[leftPointer] > height[rightPointer]) {
                //What happens when both pointers come across the same / equal height ?
                //What if moving the other pointer serves you better then ? 
                rightPointer--;
            } else {
                leftPointer++;
            }
        }
        return maxArea;
    }
}
