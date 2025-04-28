/*
 * 54. Spiral Matrix
 * Given an m x n matrix, return all elements of the matrix in spiral order.
 * Constraints:
   ** m == matrix.length
   ** n == matrix[i].length
   ** 1 <= m, n <= 10
   ** -100 <= matrix[i][j] <= 100
 */

class Leetcode54 {

    enum Direction {
        LEFT, RIGHT, UP, DOWN
    }

      public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> ans = new ArrayList<>();

        int leftBoundary = 0, rightBoundary = matrix[0].length - 1;
        int topBoundary = 0, bottomBoundary = matrix.length - 1;

        while (leftBoundary <= rightBoundary && topBoundary <= bottomBoundary) {
            //do right traversal 
            for (int i = leftBoundary; i <= rightBoundary; i++) {
                ans.add(matrix[topBoundary][i]);
            }
            topBoundary++;

            //do down traversal 
            for (int i = topBoundary; i <= bottomBoundary; i++) {
                ans.add(matrix[i][rightBoundary]);
            }
            rightBoundary--;

            //do left traversal 
            if (topBoundary <= bottomBoundary) {
                for (int i = rightBoundary; i >= leftBoundary; i--) {
                    ans.add(matrix[bottomBoundary][i]);
                }
                bottomBoundary--;
            }

            //do up traversal 
            if (leftBoundary <= rightBoundary) {
                for (int i = bottomBoundary; i >= topBoundary; i--) {
                    ans.add(matrix[i][leftBoundary]);
                }
                leftBoundary++;
            }
        }
        return ans;
    }

    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> ans = new ArrayList<>();

        Direction dir = Direction.RIGHT;
        int row = 0, col = 0;

        int rowLen = matrix.length;
        int colLen = matrix[0].length;
        boolean[][] visited = new boolean[rowLen][colLen];
        
        while (true) {
            //mark visited as true 
            visited[row][col] = true;
            ans.add(matrix[row][col]);

            int loopCnt = 0;
            boolean canMoveOn = false;

            while (loopCnt < 4) { //try all four directions
                if (dir == Direction.RIGHT) {
                    int tempCol = col + 1;
                    if (tempCol == colLen || visited[row][tempCol]) {
                        //change direction; 
                        dir = Direction.DOWN;
                        loopCnt++;
                    } else {
                        col = tempCol;
                        canMoveOn = true;
                        break;
                    }
                }

                if (dir == Direction.LEFT) {
                    int tempCol = col - 1;
                    if (tempCol < 0 || visited[row][tempCol]) {
                        dir = Direction.UP;
                        loopCnt++;
                    } else {
                        col--;
                        canMoveOn = true;
                        break;
                    }
                }

                if (dir == Direction.DOWN) {
                    int tempRow = row + 1;
                    if (tempRow == rowLen || visited[tempRow][col]) {
                        dir = Direction.LEFT;
                        loopCnt++;
                    } else {
                        row++;
                        canMoveOn = true;
                        break;
                    }
                }

                if (dir == Direction.UP) {
                    int tempRow = row - 1;
                    if (tempRow < 0 || visited[tempRow][col]) {
                        dir = Direction.RIGHT;
                        loopCnt++;
                    } else {
                        row--;
                        canMoveOn = true;
                        break;
                    }
                }
            }
            if (!canMoveOn) {
                break;
            }
        }
        return ans;
    }

}
