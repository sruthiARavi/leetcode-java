/*
 * 827. Making A Large Island
 * You are given an n x n binary matrix grid. You are allowed to change at most one 0 to be 1.
 * Return the size of the largest island in grid after applying this operation.
 * An island is a 4-directionally connected group of 1s.
 */
class Leetcode827 {
    private int markIsland(int[][]grid, int currRow, int currCol, int islandId){
        
        //Mark current node with island Id
        grid[currRow][currCol] = islandId;

        //Consider current island size to be 1
        int islandSize = 1;

        int numRows = grid.length;
        int numCols = grid[0].length;

        int[] rowDirections = {1, -1, 0, 0};
        int[] colDirections = {0, 0, 1, -1};

        //Find neighbours, mark with island id and update island size
        for (int i = 0; i < 4; i++) {
            int newRow = currRow + rowDirections[i];
            int newCol = currCol + colDirections[i];
            if (newRow >= 0 && newRow < numRows && newCol >= 0 && newCol < numCols && grid[newRow][newCol] == 1) {
                islandSize += markIsland(grid, newRow, newCol, islandId);
            }
        }

        return islandSize;
    }
    public int largestIsland(int[][] grid) {
        /*
         * Checkout Disjoint Set Union (DSU) in the Editorial.
         * It's the better (faster) and more elegant solution.
         */

        /*
         * Simple DFS would lead to TLE if we want the code to run under 1s -> O(n^4)
         * Set Unions is the way to go -> O(n^2)
         *
         * Step 1 : Island marking and finding the corresponding area
            * a. Start with every node marked 1 and use DFS to find island group
            * b. Mark each island group with its unique Id.
            * c. Update island size while doing so.
            * d. Track if zeroes are present in the grid
         * Step 2 : If no elements is 0, then grid size^2 is the size of the island
         * Step 3 : If there is at least one zero, then try to convert each 0 to 1, sequentially to find the max island size
            * a. Find all neighbouring islands and add to set
            * b. Iterate and find total island size if currNode value set to 1
            * c. Update maxSize with Math.max
            * <Do we need to track 0 positions and only visit those nodes instead to save some time ?>
         */
        int largestIslandSize = 0;

        //Problem constraint says this is a square matrix, so finding n may be enough. This is just cautionary.
        int numRows = grid.length;
        int numCols = grid[0].length;

        int[] rowDirections = {1, -1, 0, 0};
        int[] colDirections = {0, 0, 1, -1};

        int islandId = 2;
        Map<Integer, Integer> islandVsSize = new HashMap<>();

        boolean hasZero = false;

        //Step 1 : Island marking and finding the corresponding area
        for (int row = 0; row < numRows; row++) {
            for (int col = 0; col < numCols; col++) {
                int gridVal = grid[row][col];
                if (gridVal == 0) {
                    hasZero = true;
                    continue;
                }
                if(gridVal == 1) {
                    islandVsSize.put(islandId, markIsland(grid, row, col, islandId));
                    islandId++;
                }
            }
        }

        //If no elements is 0, then grid size^2 is the size of the island
        if (!hasZero) {
            return (numRows * numCols);
        }

        //Step 2 : Try to convert each 0 to 1, sequentially to find the max island size
        for (int row = 0; row < numRows; row++) {
            for (int col = 0; col < numCols; col++) {
                int gridVal = grid[row][col];
                if (gridVal == 0) {
                    Set<Integer> neighbours = new HashSet<>();
                    for (int i = 0; i < 4; i++) {
                        int newRow = row + rowDirections[i];
                        int newCol = col + colDirections[i];
                        if (newRow >= 0 && newRow < numRows && newCol >= 0 && newCol < numCols) {
                            neighbours.add(grid[newRow][newCol]);
                        }
                    }
                    int currIslandSize = 1; //For current flipped element
                    for (int island : neighbours) {
                        currIslandSize += islandVsSize.getOrDefault(island, 0);
                    }
                    largestIslandSize = Math.max(largestIslandSize, currIslandSize);
                }
            }
        }
        return largestIslandSize;
    }
}
