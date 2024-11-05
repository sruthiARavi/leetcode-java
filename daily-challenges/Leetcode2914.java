/*
 * 2914. Minimum Number of Changes to Make Binary String Beautiful
 * You are given a 0-indexed binary string s having an even length.
 * A string is beautiful if it's possible to partition it into one or more substrings such that:
   * Each substring has an even length.
   * Each substring contains only 1's or only 0's.
 * You can change any character in s to 0 or 1.
 * Return the minimum number of changes required to make the string s beautiful.
 */
class Leetcode2914 {
    public int minChanges(String s) {
        /*
         * We can decompose the whole string into disjoint blocks of size 2 and
         * find the minimum number of changes required to make those blocks beautiful
         */
        int minReqChanges = 0;
        char c1 = 0, c2 = 0;
        for (int i = 0; i < s.length(); i++) {
            if (i % 2 == 0) {
                c1 = s.charAt(i);
                continue;
            }
            c2 = s.charAt(i);
            if (c1 != c2) {
                minReqChanges++;
            }
        }
        return minReqChanges;
    }
}
