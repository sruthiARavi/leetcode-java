/*
 * 200. Number of Islands
 * 
 * Given an m x n 2D binary grid grid which represents a map of '1's (land) and '0's (water), return the number of islands.
 * An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. 
 * You may assume all four edges of the grid are all surrounded by water.
 * 
 * Constraints:
   ** m == grid.length
   ** n == grid[i].length
   ** 1 <= m, n <= 300
   ** grid[i][j] is '0' or '1'.
 */
class Leetcode200 {
    public int numIslands(char[][] grid) {
        int ans = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == '1') {
                    ans++;
                    dfs(grid, i, j);
                }
            }
        }
        return ans;
    }

    void dfs(char[][] grid, int x, int y) {
        if (x < 0 || y < 0 || x >= grid.length || y >= grid[0].length ||
                grid[x][y] == '0' || grid[x][y] == '2') {
            return;
        }

        grid[x][y] = '2'; // Mark as visited

        dfs(grid, x - 1, y); // Up
        dfs(grid, x + 1, y); // Down
        dfs(grid, x, y - 1); // Left
        dfs(grid, x, y + 1); // Right
    }
}
