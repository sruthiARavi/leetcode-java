/*
 * 1422. Maximum Score After Splitting a String
 * Given a string s of zeros and ones, 
 * return the maximum score after splitting the string into two non-empty substrings (i.e. left substring and right substring).
 * The score after splitting a string is the number of zeros in the left substring plus the number of ones in the right substring.
 */
class Leetcode1422 {
    public int maxScore(String s) {
        int no_of_ones = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '1') {
                no_of_ones++;
            }
        }

        int ans = 0;
        int no_of_zeroes = 0;
        for (int i = 0; i < s.length() - 1; i++) {
            // Since right part of len 1 is the min allowed value, s.len - 1 is used in the conditional
            if (s.charAt(i) == '0') {
                no_of_zeroes++;
            } else {
                no_of_ones--;
            }
            ans = Math.max(ans, no_of_zeroes + no_of_ones);
        }
        return ans;
    }
}
