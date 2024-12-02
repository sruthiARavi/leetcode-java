/*
 * 242. Valid Anagram
 * Given two strings s and t, return true if t is an anagram of s, and false otherwise.
 */
class Leetcode242 {
    public boolean isAnagram(String s, String t) {
        if(s.length() != t.length()) {
            return false;
        }
        //For unicode, use the hashmap approach and store key vs frequency
        int[] chars = new int[26]; //Since s and t only have lowercase english letters
        for (char c : s.toCharArray()) {
            chars[c - 'a']++;
        }
        for (char c : t.toCharArray()) {
            if (chars[c - 'a'] <= 0) {
                return false;
            }
            chars[c - 'a']--;
        }
        return true;
        //Hashmap approach
        /*
        HashMap<Character, Integer> stringChars = new HashMap<>();
        for (char c : s.toCharArray()) {
            stringChars.put(c, stringChars.getOrDefault(c, 0)+1);
        }
        for (char c : t.toCharArray()) {
            if(!stringChars.containsKey(c)) {
                return false;
            }
            int frequency = stringChars.get(c);
            if (frequency < 1) {
                stringChars.remove(c);
                return false; 
            } else {
                stringChars.put(c, frequency-1);
            }
        }
        return true;        
         */
    }
}
