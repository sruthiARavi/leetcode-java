/*
 * Count the Number of Consistent Strings
 * You are given a string allowed consisting of distinct characters and an array of strings words. 
 * A string is consistent if all characters in the string appear in the string allowed.
 * Return the number of consistent strings in the array words.
 */
class Leetcode1684 {
    public int countConsistentStrings(String allowed, String[] words) {
        int consistentStringsCount = 0; 

        HashSet<Character> allowedLetters = new HashSet<>();
        for(int i=0; i<allowed.length(); i++) {
            allowedLetters.add(allowed.charAt(i)); 
        }

        for(String word : words) {
            boolean isConsistent = true; 

            for(int i=0; i<word.length(); i++) {
                Character ch = word.charAt(i);
                if(!allowedLetters.contains(ch)) {
                    isConsistent = false; 
                    break; 
                }                
            }

            if(isConsistent) {
                consistentStringsCount++; 
            }
        }
        return consistentStringsCount; 
    }
}
