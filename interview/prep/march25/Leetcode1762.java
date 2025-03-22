/*
 * 1762. Buildings With an Ocean View
 * There are n buildings in a line. 
 * You are given an integer array heights of size n that represents the heights of the buildings in the line.
 
 * The ocean is to the right of the buildings. 
 * A building has an ocean view if the building can see the ocean without obstructions. 
 * Formally, a building has an ocean view if all the buildings to its right have a smaller height.
 
 * Return a list of indices (0-indexed) of buildings that have an ocean view, sorted in increasing order.
 */
class Leetcode1762 {
    public int[] findBuildings(int[] heights) {
        Deque<Integer> buildingsWithAView = new ArrayDeque<>();
        int maxHeight = 0;
        for (int i = heights.length - 1; i >= 0; i--) {
            if (heights[i] > maxHeight) {
                buildingsWithAView.push(i); //same as addFirst, so that we can get it in an increasing order of indices 
            }
            maxHeight = Math.max(maxHeight, heights[i]);
        }
        return buildingsWithAView.stream()
                .mapToInt(obj -> ((Number) obj).intValue())
                .toArray();
    }
  /*
   * Note : A variation of this question is for a building with an ocean view on both sides 
   * Maintain leftMax, rightMax, leftPtr, rightPtr and insert accordingly in the dequeue (or concat results in the end or use treelist) 
   */
}
