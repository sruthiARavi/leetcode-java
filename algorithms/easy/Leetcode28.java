/*
 * 28. Find the Index of the First Occurrence in a String
 * Given two strings needle and haystack, return the index of the first occurrence of needle in haystack, 
 * or -1 if needle is not part of haystack.
 */
class Leetcode28 {
    public int strStr(String haystack, String needle) {
        if (haystack.length() < needle.length()) {
            return -1;
        }
        if (haystack.length() == needle.length()) {
            return haystack.equalsIgnoreCase(needle) ? 0 : -1;
        }
        for (int i = 0; i < (haystack.length() - needle.length() + 1); i++) {
            if (haystack.charAt(i) == needle.charAt(0) &&
                    haystack.substring(i, i + needle.length()).equalsIgnoreCase(needle)) {
                return i;
            }
        }
        return -1;
    }
}
