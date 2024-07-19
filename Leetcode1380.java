/*
 * Lucky Numbers in a Matrix
 * Given an m x n matrix of distinct numbers, return all lucky numbers in the matrix in any order.
 * A lucky number is an element of the matrix such that it is the minimum element in its row and maximum in its column.
 */
class Leetcode1380 {
    public List<Integer> luckyNumbers(int[][] matrix) {
        ArrayList<Integer> rowMin = new ArrayList<>();
        ArrayList<Integer> colMax = new ArrayList<>();

        for (int i = 0; i < matrix.length; i++) {
            int min = Integer.MAX_VALUE;
            for (int j = 0; j < matrix[0].length; j++) {
                int val = matrix[i][j];
                min = Math.min(min,val);
            }
            rowMin.add(min);
        }

        for (int j = 0; j < matrix[0].length; j++) {
            int max = Integer.MIN_VALUE; 
            for (int i = 0; i < matrix.length; i++) {
                int val = matrix[i][j]; 
                max = Math.max(max, val); 
            }
            colMax.add(max); 
        }

        List<Integer> luckyNumbers = new ArrayList<>();
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] == rowMin.get(i) && matrix[i][j] == colMax.get(j)) {
                    luckyNumbers.add(matrix[i][j]);
                }
            }
        }

        return luckyNumbers;
    }
}
