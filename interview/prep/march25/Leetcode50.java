/*
 * 50. Pow(x, n)
 * Implement pow(x, n), which calculates x raised to the power n (i.e., xn).
 * Constraints:
   ** -100.0 < x < 100.0
   ** -231 <= n <= 231-1
   ** n is an integer.
   ** Either x is not zero or n > 0.
   ** -104 <= xn <= 104
 */
class Leetcode50 {
     double binaryExpoIterative(double x, long n) {
        double result = 1;
        if (n == 0) {
            return 1;
        }

        if (n < 0) {
            n = -1 * n;
            x = 1.0 / x;

        }

        while (n != 0) {
            if (n % 2 == 1) {
                result *= x;
                n -= 1;
            }
            x *= x;
            n /= 2;
        }

        return result;
    }

    double binaryExpoRecursive(double x, long n) {
        if (n == 0) {
            return 1;
        }
        if (n < 0) {
            return 1 / binaryExpoRecursive(x, -1 * n);
        }
        if (n % 2 == 1) {
            return x * binaryExpoRecursive(x * x, (n - 1) / 2);
        }
        return binaryExpoRecursive(x * x, n / 2);
    }

    public double myPow(double x, int n) {
        //Math.pow(x,n)
        /*
         * x=2 n=-2147483648 testcase fails if you don't convert n into a long, why?
         * Because if you multiply Integer.MIN_VALUE * -1, you get an overflow,
         * as min value is -2^31, but Integer.MAX_VALUE is 2^31 - 1,
         * it's -1 because you gotta hold 0 that side of the int.
         */
        return binaryExpoRecursive(x, (long) n); 
    }
}
