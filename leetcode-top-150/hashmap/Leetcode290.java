/*
 * 290. Word Pattern
 * Given a pattern and a string s, find if s follows the same pattern.
 * Here follow means a full match, such that there is a bijection between a letter in pattern and a non-empty word in s. Specifically:
   ** Each letter in pattern maps to exactly one unique word in s.
   ** Each unique word in s maps to exactly one letter in pattern.
   ** No two letters map to the same word, and no two words map to the same letter.
 */
class Leetcode290 {
    public boolean wordPattern(String pattern, String s) {
        char[] patternChars = pattern.toCharArray(); 
        String[] words = s.split(" ");
        if (patternChars.length != words.length) {
            return false;
        }
        Set<String> valueSet = new HashSet<>();
        HashMap<Character, String> mappedWords = new HashMap<>();
        for (int i = 0; i < pattern.length(); i++) {
            char patternChar = patternChars[i]; 
            String word = words[i];
            String mappedWord = mappedWords.get(patternChar);
            if (mappedWord == null) {
                if (valueSet.contains(word)) {
                    return false;
                }
                mappedWords.put(patternChar, word);
                valueSet.add(word);
            } else if (!mappedWord.equals(word)) {
                return false;
            }
        }
        return true;
    }
}
