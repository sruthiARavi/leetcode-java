/*
 * 9. Palindrome Number
 * Given an integer x, return true if x is a palindrome, and false otherwise.
 */
class Leetcode9 {
    public boolean isPalindrome(int x) {
        if (x == 0) {
            return true;
        }
        if (x < 0 || x % 10 == 0) {
            return false;
        }
        // TODO : A better solution would be to find the reverse number
        String numAsString = String.valueOf(x);

        //Using two pointer method 
        int i = 0;
        int j = numAsString.length() - 1;
        
        while (i < j) {
            if (numAsString.charAt(i) != numAsString.charAt(j)) {
                return false;
            }

            i++;
            j--;
        }
        return true;
    }
}
