/*
 * 529. Minesweeper
 * Let's play the minesweeper game (Wikipedia, online game)!
 * You are given an m x n char matrix board representing the game board where:
   ** 'M' represents an unrevealed mine,
   ** 'E' represents an unrevealed empty square,
   ** 'B' represents a revealed blank square that has no adjacent mines (i.e., above, below, left, right, and all 4 diagonals), digit ('1' to '8') represents how many mines are adjacent to this revealed square, and
   ** 'X' represents a revealed mine.
* You are also given an integer array click where click = [clickr, clickc] represents the next click position among all the unrevealed squares ('M' or 'E').
* Return the board after revealing this position according to the following rules:
   ** If a mine 'M' is revealed, then the game is over. You should change it to 'X'.
   ** If an empty square 'E' with no adjacent mines is revealed, then change it to a revealed blank 'B' and all of its adjacent unrevealed squares should be revealed recursively.
   ** If an empty square 'E' with at least one adjacent mine is revealed, then change it to a digit ('1' to '8') representing the number of adjacent mines.
   ** Return the board when no more squares will be revealed.
 * Constraints:
   ** m == board.length
   ** n == board[i].length
   ** 1 <= m, n <= 50
   ** board[i][j] is either 'M', 'E', 'B', or a digit from '1' to '8'.
   ** click.length == 2
   ** 0 <= clickr < m
   ** 0 <= clickc < n
   ** board[clickr][clickc] is either 'M' or 'E'.
 */
class Leetcode529 {
    int[][] DIRECTIONS = new int[][] {
            { -1, -1 }, { -1, 0 }, { -1, 1 },
            { 0, -1 }, { 0, 1 },
            { 1, -1 }, { 1, 0 }, { 1, 1 }
    };

    public char[][] updateBoard(char[][] board, int[] click) {
        int row = click[0];
        int col = click[1];

        if (board[row][col] == 'M') {
            board[row][col] = 'X';
            return board;
        }

        dfs(board, row, col);
        return board;
    }

    void dfs(char[][] board, int row, int col) {
        if (!isValidPos(board, row, col)) {
            return;
        }

        if (board[row][col] != 'E') {
            //already processed 
            return;
        }

        int mineCount = countAdjacentMines(board, row, col);
        if (mineCount > 0) {
            board[row][col] = (char) (mineCount + '0');
        } else {
            board[row][col] = 'B'; //no mines, set blank
            //process neighbors 
            for (int[] dir : DIRECTIONS) {
                int newRow = row + dir[0];
                int newCol = col + dir[1];
                if (isValidPos(board, newRow, newCol) && board[newRow][newCol] == 'E') {
                    dfs(board, newRow, newCol);
                }
            }
        }
    }

    int countAdjacentMines(char[][] board, int row, int col) {
        int count = 0;
        for (int[] dir : DIRECTIONS) {
            int newRow = row + dir[0];
            int newCol = col + dir[1];
            if (isValidPos(board, newRow, newCol) && board[newRow][newCol] == 'M') {
                count++;
            }
        }
        return count;
    }

    boolean isValidPos(char[][] board, int row, int col) {
        return row >= 0 && row < board.length && col >= 0 && col < board[0].length;
    }
}
