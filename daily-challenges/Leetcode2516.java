/*
 * 2516. Take K of Each Character From Left and Right
 * You are given a string s consisting of the characters 'a', 'b', and 'c' and a non-negative integer k. 
 * Each minute, you may take either the leftmost character of s, or the rightmost character of s.
 * Return the minimum number of minutes needed for you to take at least k of each character, 
 * or return -1 if it is not possible to take k of each character.
 */
class Leetcode2516 {
    public int takeCharacters(String s, int k) {
        // Using sliding window
        if (k == 0) {
            return 0;
        }
        if (s.length() < 3 || // if abc not present
                s.length() < (3 * k) || // if k times abc string not present
                !s.contains("a") || // if chars not present
                !s.contains("b") ||
                !s.contains("c")) {
            return -1;
        }

        int[] count = new int[3]; // track abc count in string
        for (char c : s.toCharArray()) {
            count[c - 'a']++;
        }
        for (int i : count) {
            if (i < k) {
                return -1;
            }
        }

        int[] window = new int[3]; // track abc count within window
        int maxWindowSize = 0;
        int leftPointer = 0;
        // Find the longest window that leaves k of each character outside
        for (int rightPointer = 0; rightPointer < s.length() && leftPointer <= rightPointer; rightPointer++) {
            window[s.charAt(rightPointer) - 'a']++;
            // Shrink window if we take too many characters
            while (leftPointer <= rightPointer &&
                    (count[0] - window[0] < k ||
                            count[1] - window[1] < k ||
                            count[2] - window[2] < k

                    )) {
                window[s.charAt(leftPointer) - 'a']--;
                leftPointer++;
            }
            maxWindowSize = Math.max(maxWindowSize, rightPointer - leftPointer + 1);
        }
        return s.length() - maxWindowSize;
    }
}
