/*
 * 5. Longest Palindromic Substring
 * Given a string s, return the longest palindromic substring in s.
 * Constraints:
   ** 1 <= s.length <= 1000
   ** s consist of only digits and English letters.
 */
class Leetcode5 {
    public String longestPalindrome(String s) {
        if(s == null || s.isBlank()) {
            return ""; 
        }
        
        int left = 0, right = 0; 
        for(int i=0; i<s.length(); i++) {
            int evenPalindrome = expandAroundCentre(s, i, i); 
            int oddPalindrome = expandAroundCentre(s, i, i+1); 
            
            int len = Math.max(evenPalindrome, oddPalindrome); 
            if(len > (right - left)) {
                //account for both odd and even length palindrome
                left = i - (len-1)/2; 
                right = i + len/2; 
            }
        }
        return s.substring(left, right+1); 
    }   
        
    int expandAroundCentre(String s, int left, int right) {
        while(left>=0 && right<s.length() && s.charAt(left) == s.charAt(right)) {
            left--; 
            right++; 
        }
        return right-left-1; //-1 because left and right have moved one step too far; 
    }
}
