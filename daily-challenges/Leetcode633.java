/*
 * Sum of Square Numbers
 * Given a non-negative integer c, decide whether there're two integers a and b such that a2 + b2 = c.
 */
class Leetcode633 {
    // binary search - same time complexity but worse space complexity 
    boolean doesNumExist(long min, long max, long b_square) {
        while (min <= max) {
            long mid = min + (max - min) / 2;
            long product = mid * mid;
            if (product == b_square) {
                return true;
            }
            if (product < b_square) {
                return doesNumExist(mid + 1, max, b_square);
            } else {
                return doesNumExist(min, mid - 1, b_square);
            }
        }
        return false;
    }

    public boolean judgeSquareSum(int c) {
        for (long a = 0; a * a <= c; a++) {
            long b_square = c - (a * a);
            // binary search if there exists b in [0,b_square] such that b^2 = b_square
            boolean numExists = doesNumExist(0, b_square, b_square);
            if (numExists) {
                return true;
            }
        }
        return false;

        // Math.sqrt approach - Has better space complexity
        /*
         * for (long a = 0; a * a <= c; a++) {
         * double b = Math.sqrt(c - a * a);
         * if (b == (int) b) {
         * return true;
         * }
         * }
         * return false;
         */
    }
}
