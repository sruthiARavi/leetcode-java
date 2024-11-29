/*
 * 383. Ransom Note
 * Given two strings ransomNote and magazine, 
 * return true if ransomNote can be constructed by using the letters from magazine and false otherwise.
 * Each letter in magazine can only be used once in ransomNote.
 */
class Leetcode383 {
    public boolean canConstruct(String ransomNote, String magazine) {
        if(ransomNote.length()>magazine.length()) {
            return false;
        }
        //array solution
        int[] charFrequency = new int[26];
        for (char c : magazine.toCharArray()) {
            charFrequency[c - 'a']++;
        }
        for (char c : ransomNote.toCharArray()) {
            if (charFrequency[c - 'a'] <= 0) {
                return false;
            }
            charFrequency[c - 'a']--;
        }
        return true; 
        //Map solution
        /*
        HashMap<Character, Integer> magazineLetters = new HashMap<>();
        for (char c : magazine.toCharArray()) {
            magazineLetters.put(c, magazineLetters.getOrDefault(c, 0) + 1);
        }
        for (char c : ransomNote.toCharArray()) {
            if (magazineLetters.containsKey(c)) {
                int letterFrequency = magazineLetters.get(c);
                if (letterFrequency > 1) {
                    magazineLetters.put(c, letterFrequency - 1);
                } else {
                    magazineLetters.remove(c);
                }
            } else {
                return false;
            }
        }
        return true;
         */
    }
}
