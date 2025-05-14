/*
 * 1768. Merge Strings Alternately
 * You are given two strings word1 and word2. Merge the strings by adding letters in alternating order, starting with word1. 
 * If a string is longer than the other, append the additional letters onto the end of the merged string.
 * Return the merged string.
 * 
 * Constraints:
   ** 1 <= word1.length, word2.length <= 100
   ** word1 and word2 consist of lowercase English letters.
 */
class Leetcode1768 {
    public String mergeAlternately(String word1, String word2) {
        int ptr1 = 0, ptr2 = 0;
        StringBuilder sb = new StringBuilder();
        while (ptr1 < word1.length() && ptr2 < word2.length()) {
            sb.append(word1.charAt(ptr1));
            ptr1++;
            sb.append(word2.charAt(ptr2));
            ptr2++;

        }
        if (ptr1 != word1.length())
        {
            sb.append(word1.substring(ptr1));
        }
        if (ptr2 != word2.length()) {
            sb.append(word2.substring(ptr2));
        }
        return sb.toString();
    }
}
