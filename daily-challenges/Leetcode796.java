/*
 * 796 Rotate String
 * Given two strings s and goal, return true if and only if s can become goal after some number of shifts on s.
 * A shift on s consists of moving the leftmost character of s to the rightmost position.
 * For example, if s = "abcde", then it will be "bcdea" after one shift.
 */

class Leetcode796 {
    // 796 Rotate String
    public boolean rotateString(String s, String goal) {
        if (s.length() != goal.length()) {
            return false;
        }

        /*
         * Concatenation effectively creates all possible rotations
         * of the source string within it. Just check that to see if
         * goal is a substring.
         */
        String concatenatedString = s + s;
        if (concatenatedString.contains(goal)) {
            return true;
        }
        return false;
    }
}
