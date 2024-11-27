/*
 * 58. Length of Last Word
 * Given a string s consisting of words and spaces, return the length of the last word in the string.
 * A word is a maximal substring consisting of non-space characters only.
 */
class Leetcode58 {
    public int lengthOfLastWord(String s) {
         s = s.trim();
        String lastWord = s.substring(s.lastIndexOf(" ") + 1);
        return  lastWord.length(); 
    }
}
