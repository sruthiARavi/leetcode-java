/*
 * 675. Cut Off Trees for Golf Event
 * You are asked to cut off all the trees in a forest for a golf event. The forest is represented as an m x n matrix. In this matrix:
   ** 0 means the cell cannot be walked through.
   ** 1 represents an empty cell that can be walked through.
   ** A number greater than 1 represents a tree in a cell that can be walked through, and this number is the tree's height.
 * In one step, you can walk in any of the four directions: north, east, south, and west. 
 * If you are standing in a cell with a tree, you can choose whether to cut it off.
 * You must cut off the trees in order from shortest to tallest. 
 * When you cut off a tree, the value at its cell becomes 1 (an empty cell).
 * Starting from the point (0, 0), return the minimum steps you need to walk to cut off all the trees. 
 * If you cannot cut off all the trees, return -1.
 * Note: The input is generated such that no two trees have the same height, and there is at least one tree needs to be cut off.
 * 
 * Constraints:
   ** m == forest.length
   ** n == forest[i].length
   ** 1 <= m, n <= 50
   ** 0 <= forest[i][j] <= 109
   ** Heights of all trees are distinct.
 */
class Leetcode675 {
    public int cutOffTree(List<List<Integer>> forest) {
        //shortest path so bfs 
        List<int[]> trees = new ArrayList<>();
        int rows = forest.size();
        int cols = forest.get(0).size();

        //Find all trees 
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                int cellValue = forest.get(i).get(j);
                if (cellValue > 1) {
                    trees.add(new int[] { cellValue, i, j });
                }
            }
        }

        //Sort all trees since they have to be processed in order 
        trees.sort((a, b) -> a[0] - b[0]);

        //Set starting point 
        int currentRow = 0;
        int currentCol = 0;

        int totalSteps = 0;

        //Move to each tree to accumulate steps 
        for (int[] tree : trees) {
            int targetRow = tree[1];
            int targetCol = tree[2];
            int steps = findShortestPath(forest, currentRow, currentCol, targetRow, targetCol);
            if (steps == -1) {
                return -1;
            }
            totalSteps += steps;
            currentRow = targetRow;
            currentCol = targetCol;
        }

        return totalSteps;
    }

    int findShortestPath(List<List<Integer>> forest, int currentRow, int currentCol, int targetRow, int targetCol) {
        // If already at the target, no steps needed
        if (currentRow == targetRow && currentCol == targetCol) {
            return 0;
        }

        // Define the 4 possible directions: left, right, down, up
        int[][] directions = new int[][] {
                { 0, -1 }, { 0, 1 },
                { 1, 0 }, { -1, 0 }
        };

        int rows = forest.size();
        int cols = forest.get(0).size();

        // Track visited cells to avoid cycles
        boolean[][] visited = new boolean[rows][cols];

        // Initialize BFS queue: each element is [row, col, steps_from_start]
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[] { currentRow, currentCol, 0 });
        visited[currentRow][currentCol] = true;

        // Perform BFS
        while (!q.isEmpty()) {
            int[] cell = q.poll();
            int r = cell[0];
            int c = cell[1];
            int steps = cell[2];

            // Explore all 4 directions
            for (int[] dir : directions) {
                int nr = r + dir[0];
                int nc = c + dir[1];

                // Check bounds
                if (nr < 0 || nc < 0 || nr >= rows || nc >= cols) {
                    continue;
                }

                // Skip visited or blocked cells (0 = impassable)
                if (visited[nr][nc] || forest.get(nr).get(nc) == 0) {
                    continue;
                }

                // If target is reached, return total steps
                if (nr == targetRow && nc == targetCol) {
                    return steps + 1;
                }

                // Mark as visited and add to queue with incremented steps
                visited[nr][nc] = true;
                q.offer(new int[] { nr, nc, steps + 1 });
            }
        }

        // Target is unreachable
        return -1;
    }

}
