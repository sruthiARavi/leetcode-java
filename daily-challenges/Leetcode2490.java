/*
 * 2490. Circular Sentence
 * A sentence is a list of words that are separated by a single space with no leading or trailing spaces.
 * For example, "Hello World", "HELLO", "hello world hello world" are all sentences.
 * Words consist of only uppercase and lowercase English letters. 
 * Uppercase and lowercase English letters are considered different.
 * A sentence is circular if:
   * The last character of a word is equal to the first character of the next word.
   * The last character of the last word is equal to the first character of the first word.
   * For example, "leetcode exercises sound delightful", "eetcode", "leetcode eats soul" are all circular sentences. 
   * However, "Leetcode is cool", "happy Leetcode", "Leetcode" and "I like Leetcode" are not circular sentences.
 * Given a string sentence, return true if it is circular. Otherwise, return false.
 */

class Leetcode2490 {
    private boolean compareTrailingAndLeadingLetters(String trailWord, String leadWord) {
        System.out.println(trailWord + " : " + leadWord);
        String trailLetter = trailWord.substring(trailWord.length() - 1);
        String leadLetter = leadWord.substring(0, 1);
        System.out.println(trailLetter + " : " + leadLetter);
        return trailLetter.equals(leadLetter);
    }

    // 2490 Circular Sentence
    public boolean isCircularSentence(String sentence) {
        String[] words = sentence.split(" ");

        if (words.length == 1) {
            return compareTrailingAndLeadingLetters(sentence, sentence);
        }

        String prev = words[0];
        for (int i = 1; i < words.length; i++) {
            String curr = words[i];
            if (!compareTrailingAndLeadingLetters(prev, curr)) {
                return false;
            }
            prev = curr;
        }

        return compareTrailingAndLeadingLetters(prev, words[0]);
    }
}
