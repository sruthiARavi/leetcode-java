/*
 * 2843. Count Symmetric Integers
 * You are given two positive integers low and high.
 * An integer x consisting of 2 * n digits is symmetric if the sum of the first n digits of x is equal to the sum of the last n digits of x. 
 * Numbers with an odd number of digits are never symmetric.
 * Return the number of symmetric integers in the range [low, high].
 * Constraints:
   ** 1 <= low <= high <= 104
 */
class Leetcode2843 {
    public int countSymmetricIntegers(int low, int high) {
        int count = 0;
        for (; low <= high; low++) {
            String num = Integer.toString(low);
            int len = num.length();
            if (len % 2 == 0) {
                String n1 = num.substring(0, (len / 2));
                String n2 = num.substring((len / 2));
                if(n1.equals(n2)) {
                    count++; 
                    continue;
                }
                int ptr = n1.length() - 1;
                int sum1 = 0;
                int sum2 = 0;
                while (ptr >= 0) {
                    sum1 += n1.charAt(ptr);
                    sum2 += n2.charAt(ptr);
                    ptr--;
                }
                if (sum1 == sum2) {
                    count++;
                }
            }
        }
        return count;
    }
}
