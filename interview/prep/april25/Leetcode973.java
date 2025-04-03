/*
 * 973. K Closest Points to Origin
 * Given an array of points where points[i] = [xi, yi] represents a point on the X-Y plane and an integer k, 
 * return the k closest points to the origin (0, 0).
 * The distance between two points on the X-Y plane is the Euclidean distance (i.e., √(x1 - x2)2 + (y1 - y2)2).
 * You may return the answer in any order. The answer is guaranteed to be unique (except for the order that it is in).
 * Constraints:
   ** 1 <= k <= points.length <= 104
   ** -104 <= xi, yi <= 104
* Example :
   ** Input: points = [[1,3],[-2,2]], k = 1
   ** Output: [[-2,2]]
   ** Explanation:
     *** The distance between (1, 3) and the origin is sqrt(10).
     *** The distance between (-2, 2) and the origin is sqrt(8).
     *** Since sqrt(8) < sqrt(10), (-2, 2) is closer to the origin.
     ** We only want the closest k = 1 points from the origin, so the answer is just [[-2,2]].
 */
class Leetcode973 {
    public int[][] kClosest(int[][] points, int k) {
        //TODO : Quickselect implementation 
        //go thru points, maintain max heap and get k smallest square values
        //PQ poll gives you poll head i.e. the largest value in the queue, that's why you are maintaining a maxHeap
        //By default PQ is minHeap
        //int[] has square value, i val for retrieving the corresponding arr as points[i]
        PriorityQueue<int[]> maxHeap = new PriorityQueue<>((a, b) -> b[0] - a[0]);
        int[][] ans = new int[k][2];

        for (int i = 0; i < points.length; i++) {
            int[] arr = points[i];
            //x1-x2 -> x1-0 -> x1
            int squaredValue = arr[0] * arr[0] + arr[1] * arr[1];
            maxHeap.add(new int[]{squaredValue, i});
            if (maxHeap.size() > k) {
                maxHeap.poll();
            }
        }

        for (int i = 0; i < k; i++) {
            int idx = maxHeap.poll()[1];
            ans[i] = points[idx];
        }

        return ans;
    }
}
