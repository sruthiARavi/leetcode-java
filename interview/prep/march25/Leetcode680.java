/*
 * 680. Valid Palindrome II
 * Given a string s, return true if the s can be palindrome after deleting at most one character from it.
 */
class Leetcode680 {
    boolean checkValidPalindromeWithRemoval(int leftPointer, int rightPointer, String s, int removalCount,
            int maxAllowedRemoval) {
        while (leftPointer < rightPointer) {
            if (s.charAt(leftPointer) != s.charAt(rightPointer)) {
                if (removalCount < maxAllowedRemoval) {
                    return checkValidPalindromeWithRemoval(leftPointer + 1, rightPointer, s, removalCount + 1,
                            maxAllowedRemoval) ||
                            checkValidPalindromeWithRemoval(leftPointer, rightPointer - 1, s, removalCount + 1,
                                    maxAllowedRemoval);
                }
                return false;
            }
            leftPointer++;
            rightPointer--;
        }
        return true;
    }

    public boolean validPalindrome(String s) {
        //recursion solution, this also accounts for extensions to the problems 
        return checkValidPalindromeWithRemoval(0, s.length() - 1, s, 0, 1);
    }

    boolean checkValidPalindrome(int leftPtr, int rightPtr, String s) {
        while (leftPtr < rightPtr) {
            if (s.charAt(leftPtr) != s.charAt(rightPtr)) {
                return false;
            }
            leftPtr++;
            rightPtr--;
        }
        return true;
    }

    public boolean validPalindrome1(String s) {
        int left = 0;
        int right = s.length() - 1;
        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) {
                // should we avoid recursion since it is costlier? 
                // using another method to check validity for now, using only pointers  
                return (checkValidPalindrome(left + 1, right, s) || checkValidPalindrome(left, right - 1, s));
            }
            left++;
            right--;
        }
        return true;
    }
}
