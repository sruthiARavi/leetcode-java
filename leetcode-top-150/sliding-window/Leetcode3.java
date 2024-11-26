/*
 * 3. Longest Substring Without Repeating Characters
 * Given a string s, find the length of the longest substring without repeating characters.
 */
class Leetcode3 {
    public int lengthOfLongestSubstring(String s) {
        int maxLength = 0;
        int left = 0;
        Set<Character> windowChars = new HashSet<>();
        for (int right = 0; right < s.length(); right++) {
            while (windowChars.contains(s.charAt(right))) {
                windowChars.remove(s.charAt(left++));
                maxLength = Math.max(maxLength, windowChars.size());
            }
            windowChars.add(s.charAt(right));
            maxLength = Math.max(maxLength, windowChars.size());
        }
        return maxLength;
    }
}
