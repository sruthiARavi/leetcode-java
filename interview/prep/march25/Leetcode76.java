/*
 * 76. Minimum Window Substring
 * Given two strings s and t of lengths m and n respectively, return the minimum window substring of s such that every character in t (including duplicates) is included in the window. 
 * If there is no such substring, return the empty string "".
 * The testcases will be generated such that the answer is unique.
 * Constraints:
   ** m == s.length
   ** n == t.length
   ** 1 <= m, n <= 105
   ** s and t consist of uppercase and lowercase English letters.
 * Follow up: Could you find an algorithm that runs in O(m + n) time?
 */
class Leetcode76 {
    public String minWindow(String s, String t) {
        //Sliding window approach
        //TODO : Optimized sliding window
        if (s.isBlank() || t.isBlank() || t.length() > s.length()) {
            return "";
        }

        Map<Character, Integer> charFreq = new HashMap<>();
        for (char c : t.toCharArray()) {
            charFreq.put(c, charFreq.getOrDefault(c, 0) + 1);
        }

        //ans arr has windowLen, leftPtr, rightPtr
        int[] ans = {-1, 0, 0};
        int leftPointer = 0, rightPointer = 0, formed = 0;
        Map<Character, Integer> windowCharFreq = new HashMap<>();
        while (leftPointer <= rightPointer && rightPointer < s.length()) {
            char c = s.charAt(rightPointer);
            windowCharFreq.put(c, windowCharFreq.getOrDefault(c, 0) + 1);

            if (charFreq.containsKey(c) && charFreq.get(c).intValue() == windowCharFreq.get(c).intValue()) {
                formed++;
            }

            //If formed, try reducing the window
            while (leftPointer <= rightPointer && formed == charFreq.size()) {

                //update ans array first, if required
                if (ans[0] == -1 || (ans[0] > (rightPointer - leftPointer + 1))) {
                    ans[0] = rightPointer - leftPointer + 1;
                    ans[1] = leftPointer;
                    ans[2] = rightPointer;
                }

                //try shrinking the window
                char removeChar = s.charAt(leftPointer);
                windowCharFreq.put(removeChar, windowCharFreq.get(removeChar) - 1);
                if (charFreq.containsKey(removeChar) && windowCharFreq.get(removeChar) < charFreq.get(removeChar)) {
                    formed--;
                }

                //Move the left pointer ahead 
                leftPointer++;
            }

            rightPointer++;
        }

        return ans[0] == -1 ? "" : s.substring(ans[1], ans[2] + 1);
    }
}
