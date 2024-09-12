/*
 * Valid Palindrome
 * A phrase is a palindrome if, after converting all uppercase letters into lowercase letters and removing all non-alphanumeric characters, 
 * it reads the same forward and backward. 
 * Alphanumeric characters include letters and numbers.
 * Given a string s, return true if it is a palindrome, or false otherwise.
 */
class Leetcode125 {
    public boolean isPalindrome(String s) {

        if (s.length() == 0 || s.isEmpty()) {
            return true;
        }

        s = s.toLowerCase();
        int i = 0;
        int j = s.length() - 1;

        boolean isValid = true;

        while (i < j && i < s.length() && j >= 0) {
            Character firstPointer = s.charAt(i);
            if (!Character.isLetterOrDigit(firstPointer)) {
                i++;
                continue;
            }
            Character secondPointer = s.charAt(j);
            if (!Character.isLetterOrDigit(secondPointer)) {
                j--;
                continue;
            }

            if (firstPointer != secondPointer) {
                isValid = false;
                break;
            }

            i++;
            j--;
        }

        return isValid;
    }
}
