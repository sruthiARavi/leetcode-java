/*
 * 73. Set Matrix Zeroes
 * Given an m x n integer matrix matrix, if an element is 0, set its entire row and column to 0's.
 * You must do it in place.
 */
class Leetcode73 {

        public void setZeroes(int[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;

        boolean firstRowZero = false, firstColZero = false;
        for (int i = 0; i < rows; i++) {//check first col
            if (matrix[i][0] == 0) {
                firstColZero = true;
            }
        }

        for (int i = 0; i < cols; i++) {//check first row 
            if (matrix[0][i] == 0) {
                firstRowZero = true;
            }
        }

        //preserve the first row and col. You've already checked in the prev step and now you only need to set the flags there
        for (int i = 1; i < rows; i++) {
            for (int j = 1; j < cols; j++) {
                if (matrix[i][j] == 0) {
                    matrix[i][0] = 0;
                    matrix[0][j] = 0;
                }
            }
        }

        for (int i = 1; i < rows; i++) {//check rows
            if (matrix[i][0] == 0) {
                for (int j = 0; j < cols; j++) {
                    matrix[i][j] = 0;
                }
            }
        }

        for (int i = 1; i < cols; i++) {//check cols 
            if (matrix[0][i] == 0) {
                for (int j = 0; j < rows; j++) {
                    matrix[j][i] = 0;
                }
            }
        }

        //Handle first row and col 
        if (firstRowZero) {
            for (int i = 0; i < cols; i++) {
                matrix[0][i] = 0;
            }
        }
        if (firstColZero) {
            for (int i = 0; i < rows; i++) {
                matrix[i][0] = 0;
            }
        }
    }
    
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
