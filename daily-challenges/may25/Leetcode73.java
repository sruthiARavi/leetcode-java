/*
 * 73. Set Matrix Zeroes
 * Given an m x n integer matrix matrix, if an element is 0, set its entire row and column to 0's.
 * You must do it in place.
 */
class Leetcode73 {
    record Location(int row, int col) {
    };

    public void setZeroesBrute(int[][] matrix) {
        Set<Location> s = new HashSet<>();

        int rows = matrix.length;
        int cols = matrix[0].length;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (matrix[i][j] == 0) {
                    Location l = new Location(i, j);
                    s.add(l);
                }
            }
        }

        for (Location l : s) {
            int row = l.row;
            int col = l.col;
            for (int i = 0; i < cols; i++) {
                matrix[row][i] = 0;
            }
            for (int i = 0; i < rows; i++) {
                matrix[i][col] = 0;
            }
        }
    }
}
