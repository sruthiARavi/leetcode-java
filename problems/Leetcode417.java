/*
 * 417. Pacific Atlantic Water Flow 
 * There is an m x n rectangular island that borders both the Pacific Ocean and Atlantic Ocean. 
 * The Pacific Ocean touches the island's left and top edges, and the Atlantic Ocean touches the island's right and bottom edges.
 * The island is partitioned into a grid of square cells. 
 * You are given an m x n integer matrix heights where heights[r][c] represents the height above sea level of the cell at coordinate (r, c).
 * The island receives a lot of rain, and the rain water can flow to neighboring cells directly north, south, east, and west if the neighboring cell's height is less than or equal to the current cell's height. 
 * Water can flow from any cell adjacent to an ocean into the ocean.
 * Return a 2D list of grid coordinates result where result[i] = [ri, ci] denotes that rain water can flow from cell (ri, ci) to both the Pacific and Atlantic oceans.
 */
class Leetcode417 {
    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        List<List<Integer>> pacificAtlantic = new ArrayList<>();

        int rows = heights.length;
        int columns = heights[0].length;

        boolean[][] pacific = new boolean[rows][columns];
        boolean[][] atlantic = new boolean[rows][columns];

        for (int row = 0; row < rows; row++) {
            dfs(heights, row, 0, pacific, heights[row][0]);
            dfs(heights, row, columns - 1, atlantic, heights[row][columns - 1]);
        }
        for (int col = 0; col < columns; col++) {
            dfs(heights, 0, col, pacific, heights[0][col]);
            dfs(heights, rows - 1, col, atlantic, heights[rows - 1][col]);
        }

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (pacific[i][j] && atlantic[i][j]) {
                    List<Integer> gridCoordinates = new ArrayList<>();
                    gridCoordinates.add(i);
                    gridCoordinates.add(j);
                    pacificAtlantic.add(gridCoordinates);
                }
            }
        }
        return pacificAtlantic;
    }

    private void dfs(int[][] heights, int currentRow, int currentColumn, boolean[][] visited, int previousValue) {
        int rows = heights.length;
        int columns = heights[0].length;
        if (currentRow < 0 ||
                currentRow > rows - 1 ||
                currentColumn < 0 ||
                currentColumn > columns - 1 ||
                visited[currentRow][currentColumn] ||
                previousValue > heights[currentRow][currentColumn]) {
            return;
        }
        visited[currentRow][currentColumn] = true;
        int currentValue = heights[currentRow][currentColumn];
        dfs(heights, currentRow, currentColumn - 1, visited, currentValue);
        dfs(heights, currentRow, currentColumn + 1, visited, currentValue);
        dfs(heights, currentRow - 1, currentColumn, visited, currentValue);
        dfs(heights, currentRow + 1, currentColumn, visited, currentValue);
    }
}
