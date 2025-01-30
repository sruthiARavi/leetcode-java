/*
 * 2658. Maximum Number of Fish in a Grid
 * You are given a 0-indexed 2D matrix grid of size m x n, where (r, c) represents:
   ** A land cell if grid[r][c] = 0, or
   ** A water cell containing grid[r][c] fish, if grid[r][c] > 0.
 * A fisher can start at any water cell (r, c) and can do the following operations any number of times:
   ** Catch all the fish at cell (r, c), or
   ** Move to any adjacent water cell.   
 * Return the maximum number of fish the fisher can catch if he chooses his starting cell optimally, or 0 if no water cell exists.
 * An adjacent cell of the cell (r, c), is one of the cells (r, c + 1), (r, c - 1), (r + 1, c) or (r - 1, c) if it exists.
 */

class Leetcode2658 {
    private int countFishes(int[][] grid, boolean[][] visited, int row, int col) {
        int maxRows = grid.length;
        int maxCols = grid[0].length;
        if (row < 0 || row > maxRows - 1 ||
                col < 0 || col > maxCols - 1 ||
                grid[row][col] == 0 || visited[row][col]) {
            return 0;
        }
        visited[row][col] = true;
        return grid[row][col] + countFishes(grid, visited, row - 1, col) +
                countFishes(grid, visited, row + 1, col) +
                countFishes(grid, visited, row, col - 1) +
                countFishes(grid, visited, row, col + 1);
    }

    public int findMaxFish(int[][] grid) {
        /*
         * grid DFS with extra step for calculating the cell value (fish count)
         */
        int numRows = grid.length;
        int numCols = grid[0].length;
        boolean[][] visited = new boolean[numRows][numCols];
        int maxFishes = 0;

        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                if (grid[i][j] > 0 && !visited[i][j]) {
                    maxFishes = Math.max(maxFishes, countFishes(grid, visited, i, j));
                }
            }
        }

        return maxFishes;
    }
}
