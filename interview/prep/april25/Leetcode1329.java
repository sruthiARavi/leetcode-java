/*
 * 1329. Sort the Matrix Diagonally
 * A matrix diagonal is a diagonal line of cells starting from some cell in either the topmost row or leftmost column and 
 * going in the bottom-right direction until reaching the matrix's end. 
 * For example, the matrix diagonal starting from mat[2][0], where mat is a 6 x 3 matrix, includes cells mat[2][0], mat[3][1], and mat[4][2].
 * Given an m x n matrix mat of integers, sort each matrix diagonal in ascending order and return the resulting matrix.
 * Constraints:
   ** m == mat.length
   ** n == mat[i].length
   ** 1 <= m, n <= 100
   ** 1 <= mat[i][j] <= 100
 */
class Leetcode1329 {
    public int[][] diagonalSort(int[][] mat) {
        int rows = mat.length;
        int cols = mat[0].length;
        int[][] result = new int[rows][cols];

        // Process diagonals starting from the bottom row and first column
        for (int i = rows - 1; i >= 0; i--) {
            // Collect diagonal positions starting from (i, 0)
            List<int[]> diagonalPos = getDiagonalIndices(i, 0, rows, cols);
            // Use a priority queue to store diagonal values
            PriorityQueue<Integer> pq = new PriorityQueue<>();

            for (int[] cell : diagonalPos) {
                pq.add(mat[cell[0]][cell[1]]);
            }

            // Fill the result matrix with sorted diagonal values
            int idx = 0;
            while (!pq.isEmpty()) {
                int[] cell = diagonalPos.get(idx);
                result[cell[0]][cell[1]] = pq.poll();
                idx++;
            }
        }

        // Process diagonals starting from the first column
        for (int i = 1; i < cols; i++) {
            // Collect diagonal positions starting from (0, i)
            List<int[]> diagonalPos = getDiagonalIndices(0, i, rows, cols);
            // Use a priority queue to store diagonal values
            PriorityQueue<Integer> pq = new PriorityQueue<>();

            for (int[] cell : diagonalPos) {
                pq.add(mat[cell[0]][cell[1]]);
            }

            // Fill the result matrix with sorted diagonal values
            int idx = 0;
            while (!pq.isEmpty()) {
                int[] cell = diagonalPos.get(idx);
                result[cell[0]][cell[1]] = pq.poll();
                idx++;
            }
        }
        return result;
    }

    List<int[]> getDiagonalIndices(int startRow, int startCol, int matRowLen, int matColLen) {
        List<int[]> diagonalPos = new ArrayList<>();
        while (startRow <= matRowLen - 1 && startCol <= matColLen - 1) {
            diagonalPos.add(new int[] { startRow, startCol });
            startRow++;
            startCol++;
        }
        return diagonalPos;
    }
}
