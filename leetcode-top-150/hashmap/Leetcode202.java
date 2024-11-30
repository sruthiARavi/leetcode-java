/*
 * 202. Happy Number 
 * Write an algorithm to determine if a number n is happy.
 * A happy number is a number defined by the following process:
   ** Starting with any positive integer, replace the number by the sum of the squares of its digits.
   ** Repeat the process until the number equals 1 (where it will stay), or it loops endlessly in a cycle which does not include 1.
   ** Those numbers for which this process ends in 1 are happy.
 * Return true if n is a happy number, and false if not.
 */
class Leetcode202 {
    public boolean isHappy(int n) {
        // Using Floyd's cycle finding algorithm
        // Alternatively, we can use a hash table and track
        int slow = n;
        int fast = n;
        do {
            slow = findSquareOfDigits(slow);
            fast = findSquareOfDigits(findSquareOfDigits(fast));
        } while (slow != fast); // initially they will be same, therefore do-while
        return slow == 1;
    }

    private int findSquareOfDigits(int n) {
        int ans = 0; // Since n is positive as per constraints
        while (n > 0) {
            int remainder = n % 10;
            ans += (remainder * remainder); // Since sum of SQUARE of digits
            n /= 10;
        }
        return ans;
    }
}
