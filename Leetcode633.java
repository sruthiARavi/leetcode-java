/*
 * Sum of Square Numbers
 * Given a non-negative integer c, decide whether there're two integers a and b such that a2 + b2 = c.
 */
class Leetcode633 {
    public boolean judgeSquareSum(int c) {
        for(long a=0; a*a <=c; a++) {
            double b = Math.sqrt(c-a*a); 
            if(b == (int) b) {
                return true; 
            }
        }
        return false; 
    }
}
