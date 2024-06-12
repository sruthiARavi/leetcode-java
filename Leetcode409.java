/*
 * Longest Palindrome
 * Given a string s which consists of lowercase or uppercase letters, return the length of the longest palindrome that can be built with those letters.
 * Letters are case sensitive, for example, "Aa" is not considered a palindrome.
 */
class Leetcode409 {
    public int longestPalindrome(String s) {
        Set<Character> oddFrequencyChars = new HashSet<>();
        int result = 0;
        for (char c : s.toCharArray()) {
            if (oddFrequencyChars.contains(c)) {
                result += 2;
                oddFrequencyChars.remove(c);
            } else {
                oddFrequencyChars.add(c);
            }
        }
        return oddFrequencyChars.isEmpty() ? result : result + 1;
    }
}
