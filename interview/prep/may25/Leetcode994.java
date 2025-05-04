/*
 * 994. Rotting Oranges
 * You are given an m x n grid where each cell can have one of three values:
   ** 0 representing an empty cell,
   ** 1 representing a fresh orange, or
   ** 2 representing a rotten orange.
 * Every minute, any fresh orange that is 4-directionally adjacent to a rotten orange becomes rotten.
 * Return the minimum number of minutes that must elapse until no cell has a fresh orange. If this is impossible, return -1.
 * 
 * Constraints:
   ** m == grid.length
   ** n == grid[i].length
   ** 1 <= m, n <= 10
   ** grid[i][j] is 0, 1, or 2.
 */
class Leetcode994 {
    public int orangesRotting(int[][] grid) {
        /*
         * The process of rotting could be explained perfectly with the BFS procedure, 
         * i.e. the rotten oranges will contaminate their neighbors first, 
         * before the contamination propagates to other fresh oranges that are farther away.
         */

        Queue<int[]> q = new LinkedList<>();
        int freshOranges = 0;
        int timeElapsed = -1;

        int rows = grid.length;
        int cols = grid[0].length;

        int[][] directions = new int[][] {
                { 1, 0 }, { -1, 0 },
                { 0, -1 }, { 0, 1 }
        };

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == 1) {
                    freshOranges++;
                } else if (grid[i][j] == 2) {
                    int[] co_ords = new int[] { i, j };
                    q.offer(co_ords);
                }
            }
        }

        q.offer(new int[] { -1, -1 }); //timestamp marker 

        while (!q.isEmpty()) {
            int[] co_ords = q.poll();
            int row = co_ords[0];
            int col = co_ords[1];

            if (row == -1) {
                timeElapsed++;
                if (!q.isEmpty()) {
                    q.offer(co_ords); //add the next marker  
                }
            } else {
                for (int[] dir : directions) {
                    int newRow = row + dir[0];
                    int newCol = col + dir[1];
                    if (newRow >= 0 && newRow < rows && newCol >= 0 && newCol < cols && grid[newRow][newCol] == 1) {
                        grid[newRow][newCol] = 2; //mark it rotten 
                        q.offer(new int[] { newRow, newCol });
                        freshOranges--;
                    }
                }
            }
        }

        return freshOranges > 0 ? -1 : timeElapsed;
    }

}
