/*
 * 3163 String Compression III
 * Given a string word, compress it using the following algorithm:
   * Begin with an empty string comp. While word is not empty, use the following operation:
     * Remove a maximum length prefix of word made of a single character c repeating at most 9 times.
   * Append the length of the prefix followed by c to comp.
 * Return the string comp.
 */
class Leetcode3163 {
    public String compressedString(String word) {
        StringBuilder compressedString = new StringBuilder();
        int position = 0;
        while (position < word.length()) {
            int consecutiveCount = 0;
            char currentChar = word.charAt(position);
            while (position < word.length() &&
                    consecutiveCount < 9 &&
                    word.charAt(position) == currentChar) {
                consecutiveCount++;
                position++;
            }
            compressedString.append(consecutiveCount).append(currentChar);
        }
        return compressedString.toString();
    }
}
