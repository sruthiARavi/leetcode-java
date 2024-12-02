/*
 * 1455. Check If a Word Occurs As a Prefix of Any Word in a Sentence
 * 
 * Given a sentence that consists of some words separated by a single space, and a searchWord, 
 * check if searchWord is a prefix of any word in sentence.
 * 
 * Return the index of the word in sentence (1-indexed) where searchWord is a prefix of this word. ']
 * If searchWord is a prefix of more than one word, return the index of the first word (minimum index). 
 * If there is no such word return -1.
 * 
 * A prefix of a string s is any leading contiguous substring of s.
 */
class Leetcode1455 {
    public int isPrefixOfWord(String sentence, String searchWord) {
        String[] words = sentence.split(" ");
        for(int i=0; i<words.length; i++) {
            String word = words[i];
            if(word.indexOf(searchWord) == 0) {
                return i+1;
            }
        }
        return -1;
    }
}
