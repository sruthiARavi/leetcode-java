/*
 * Append Characters to String to Make Subsequence
 * You are given two strings s and t consisting of only lowercase English letters.
 * Return the minimum number of characters that need to be appended to the end of s so that t becomes a subsequence of s.
 * A subsequence is a string that can be derived from another string by deleting some or no characters without changing the order of the remaining characters.
 */
class Leetcode2486 {
    public int appendCharacters(String s, String t) {
        int longestPrefix = 0;
        int first = 0;
        while (first < s.length() && longestPrefix < t.length()) {
            if (s.charAt(first) == t.charAt(longestPrefix)) {
                longestPrefix++;
            }
            first++;
        }
        return t.length() - longestPrefix;
    }
}
