/*
 * 57. Insert Interval
 * You are given an array of non-overlapping intervals intervals where 
 * intervals[i] = [starti, endi] represent the start and the end of the ith interval and intervals is sorted in ascending order by starti. 
 * You are also given an interval newInterval = [start, end] that represents the start and end of another interval.
 * Insert newInterval into intervals such that intervals is still sorted in ascending order by starti and 
 * intervals still does not have any overlapping intervals (merge overlapping intervals if necessary).
 * Return intervals after the insertion.
 * Note that you don't need to modify intervals in-place. You can make a new array and return it.
 * Constraints:
   ** 0 <= intervals.length <= 104
   ** intervals[i].length == 2
   ** 0 <= starti <= endi <= 105
   ** intervals is sorted by starti in ascending order.
   ** newInterval.length == 2
   ** 0 <= start <= end <= 105
 */
class Leetcode57 {
    public int[][] insert(int[][] intervals, int[] newInterval) {
        //TODO : Binary search implementaion 
        List<int[]> result = new ArrayList<>();
        int i = 0;

        //Add all intervals which come before the new interval and don't clash with it i.e. intervals ending pt less than newInterval starting pt
        while (i < intervals.length && intervals[i][1] < newInterval[0]) {
            result.add(intervals[i]);
            i++;
        }

        //Handle clashes for cases where intervals starting pt less than or equal to newInterval's ending pt
        while (i < intervals.length && newInterval[1] >= intervals[i][0]) {
            newInterval[0] = Math.min(intervals[i][0], newInterval[0]);
            newInterval[1] = Math.max(intervals[i][1], newInterval[1]);
            i++;
        }
        result.add(newInterval);

        //Add all intervals which come after the new interval and don't clash with it i.e. intervals starting pt after newInterval ending pt
        while (i < intervals.length) {
            result.add(intervals[i]);
            i++;
        }

        return result.toArray(new int[result.size()][2]);
    }
}
