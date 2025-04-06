/*
 * 1091. Shortest Path in Binary Matrix
 * Given an n x n binary matrix grid, return the length of the shortest clear path in the matrix. 
 * If there is no clear path, return -1.
 * A clear path in a binary matrix is a path from the top-left cell (i.e., (0, 0)) to the bottom-right cell (i.e., (n - 1, n - 1)) such that:
   ** All the visited cells of the path are 0.
   ** All the adjacent cells of the path are 8-directionally connected (i.e., they are different and they share an edge or a corner).
   ** The length of a clear path is the number of visited cells of this path.
 * Constraints:
   ** n == grid.length
   ** n == grid[i].length
   ** 1 <= n <= 100
   ** grid[i][j] is 0 or 1
 */
class Leetcode1091 {
    public int shortestPathBinaryMatrix(int[][] grid) {
        int n = grid.length;

        // Edge case: if the start or end is blocked
        if (grid[0][0] != 0 || grid[n - 1][n - 1] != 0) {
            return -1;
        }

        // Directions for 8 possible moves: n, s, w, e, nw, ne, sw, se
        int[][] directions = {
                { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 }, // n, s, w, e
                { -1, -1 }, { -1, 1 }, { 1, -1 }, { 1, 1 } // nw, ne, sw, se
        };

        boolean[][] visited = new boolean[n][n];
        Queue<int[]> queue = new LinkedList<>();

        // Start BFS from (0, 0) with distance 1
        queue.offer(new int[] { 0, 0, 1 });
        visited[0][0] = true;

        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            int row = curr[0], col = curr[1], distance = curr[2];

            // Check if we have reached the target
            if (row == n - 1 && col == n - 1) {
                return distance;
            }

            // Explore all 8 neighbors
            for (int[] dir : directions) {
                int newRow = row + dir[0];
                int newCol = col + dir[1];

                // Check if the new cell is within bounds, not blocked, and not visited
                if (newRow >= 0 && newRow < n && newCol >= 0 && newCol < n
                        && grid[newRow][newCol] == 0 && !visited[newRow][newCol]) {

                    // Mark the new cell as visited
                    visited[newRow][newCol] = true;

                    // Add the neighbor to the queue with the updated distance
                    queue.offer(new int[] { newRow, newCol, distance + 1 });
                }
            }
        }

        return -1; // If no path is found
    }
}
