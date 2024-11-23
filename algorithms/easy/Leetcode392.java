/*
 * 392. Is Subsequence
 * Given two strings s and t, return true if s is a subsequence of t, or false otherwise.
 * A subsequence of a string is a new string that is formed from the original string 
 * by deleting some (can be none) of the characters without disturbing the relative positions of the remaining characters. 
 * (i.e., "ace" is a subsequence of "abcde" while "aec" is not).
 */
class Leetcode392 {
    public boolean isSubsequence(String s, String t) {
        if (t.length() < s.length() ||
                (s.length() == t.length() && !s.equalsIgnoreCase(t))) {
            return false;
        }
        if (s.isEmpty()) {
            return true;
        }
        // Two pointers
        int ptr1 = 0;
        // Alternative : int ptr2 = 0; Skip that part below
        // Alternative : convert both strings to char arrays
        int ptr2 = t.indexOf(s.charAt(ptr1));
        if (ptr2 == -1) {
            return false;
        }
        ptr1++;
        ptr2++;
        while (ptr1 < s.length() && ptr2 < t.length()) {
            if (s.charAt(ptr1) == t.charAt(ptr2)) {
                ptr1++;
                ptr2++;
                continue;
            }
            ptr2++;
        }
        System.out.println("pt : " + ptr1 + ", s len : " + s.length());
        return ptr1 == s.length();
    }
}
