/*
 * 56. Merge Intervals
 * Given an array of intervals where intervals[i] = [starti, endi], 
 * merge all overlapping intervals, and return an array of the non-overlapping intervals that cover all the intervals in the input.
 * Constraints:
   ** 1 <= intervals.length <= 104
   ** intervals[i].length == 2
   ** 0 <= starti <= endi <= 104
 */
class Leetcode56 {
    public int[][] merge(int[][] intervals) {
        //Sorting impl 
        //TODO : Connected components 
        // Sort intervals by start time
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));

        // Use a list to store merged intervals
        List<int[]> mergedIntervals = new ArrayList<>();

        for (int[] interval : intervals) {
            // If the list is empty or current interval doesn't overlap with the last merged interval
            if (mergedIntervals.isEmpty() || interval[0] > mergedIntervals.getLast()[1]) {
                mergedIntervals.add(interval);
            } else {
                // Merge intervals by updating the end of the last interval in the list
                mergedIntervals.getLast()[1] = Math.max(mergedIntervals.getLast()[1], interval[1]);
            }
        }

        // Convert the list to a 2D array and return
        return mergedIntervals.toArray(new int[mergedIntervals.size()][]);
    }
}
