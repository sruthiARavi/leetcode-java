/*
 * 1267. Count servers that communicate
 * 
 * You are given a map of a server center, represented as a m * n integer matrix grid, 
 * where 1 means that on that cell there is a server and 0 means that it is no server. 
 * Two servers are said to communicate if they are on the same row or on the same column.
 * Return the number of servers that communicate with any other server.
 */
class Leetcode1267 {
    public int countServers(int[][] grid) {
         /*
         * Alternatives : Find occurrence of last server in row and process only until then (or)
         * Find occurrence of first server in row and based on that, increment count for all subsequent servers encountered
         * These are yet to be implemented and verified. Not sure if the latter would work
         * For now, I am using 2 arrays to track server count in each row / column and then in the second pass,
         * incrementing count based on the arrays' values
         */
        if (grid == null || grid.length == 0) {
            return 0;
        }

        int row_length = grid.length;
        int col_length = grid[0].length;
        int communicable_server_count = 0;

        int[] rowCounts = new int[col_length]; //because we need every element in the row
        int[] colCounts = new int[row_length]; //because every element in current col, i.e. each row of the current col, hsa to be checked

        //Count rows and cols
        for (int row = 0; row < row_length; ++row) {
            for (int col = 0; col < col_length; ++col) {
                if (grid[row][col] == 1) {
                    rowCounts[col]++;
                    colCounts[row]++;
                }
            }
        }

        for (int row = 0; row < row_length; ++row) {
            for (int col = 0; col < col_length; ++col) {
                if (grid[row][col] == 1) {
                    if (rowCounts[col] > 1 || colCounts[row] > 1) {
                        communicable_server_count++;
                    }
                }
            }
        }

        return communicable_server_count;
    }

    //Alternate 
    public int countServers1(int[][] grid) {
        //This is slow as it is partially brute force
        int row_length = grid.length;
        int col_length = row_length > 0 ? grid[0].length : 0;
        int communicable_server_count = 0;

        for (int row = 0; row < row_length; ++row) {
            boolean hasAServer = false;
            boolean canCommunicate = false;
            for (int col = 0; col < col_length; ++col) {
                if (grid[row][col] == 1) {
                    if (hasAServer) {
                        ++communicable_server_count;
                        continue;
                    }
                    //check the rest of the row
                    for (int other_col = col + 1; other_col < col_length; ++other_col) {
                        if (grid[row][other_col] == 1) {
                            hasAServer = true;
                            canCommunicate = true;
                            break;
                        }
                    }

                    if (canCommunicate) {
                        ++communicable_server_count;
                    } else {
                        //check the column
                        //how to optimize this further ?
                        for (int other_row = 0; other_row < row_length; ++other_row) {
                            if (other_row != row && grid[other_row][col] == 1) {
                                canCommunicate = true;
                                break;
                            }
                        }
                        if (canCommunicate) {
                            ++communicable_server_count;
                        }
                    }

                    hasAServer = true;
                }
            }
        }
        return communicable_server_count;
    }
}
