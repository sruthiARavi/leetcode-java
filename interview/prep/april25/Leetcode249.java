/*
 * 249. Group Shifted Strings
 *
 * Perform the following shift operations on a string:
   ** Right shift: Replace every letter with the successive letter of the English alphabet, where 'z' is replaced by 'a'. 
   For example, "abc" can be right-shifted to "bcd" or "xyz" can be right-shifted to "yza".
   
   ** Left shift: Replace every letter with the preceding letter of the English alphabet, where 'a' is replaced by 'z'. 
   For example, "bcd" can be left-shifted to "abc" or "yza" can be left-shifted to "xyz".
   
 * We can keep shifting the string in both directions to form an endless shifting sequence.
   ** For example, shift "abc" to form the sequence: ... <-> "abc" <-> "bcd" <-> ... <-> "xyz" <-> "yza" <-> .... <-> "zab" <-> "abc" <-> ...
 * You are given an array of strings strings, group together all strings[i] that belong to the same shifting sequence. 
 * You may return the answer in any order.
 * 
 * Constraints:
   ** 1 <= strings.length <= 200
   ** 1 <= strings[i].length <= 50
   ** strings[i] consists of lowercase English letters.
 */
class Leetcode249 {

    public String rotateStringByFactor(String s, int rotationFactor) {
        /*
         * Variation 1 : 
         * rotating a string with a specific rotational factor for different types of characters:
         * Lowercase letters ('a' to 'z') are rotated to the next letter, wrapping around to 'a' after 'z'.          
         * Uppercase letters ('A' to 'Z') are rotated similarly, wrapping around to 'A' after 'Z'.          
         * Numbers ('0' to '9') are rotated to the next digit, wrapping around to '0' after '9'.
         * Other characters (like spaces, punctuation, etc.) remain unchanged.
         */
        StringBuilder sb = new StringBuilder();
        for (char c : s.toCharArray()) {
            char newChar;
            if (Character.isLowerCase(c)) {
                //rotate
                newChar = (char) ((((c - 'a') + rotationFactor + 26) % 26) + 'a');
            } else if (Character.isUpperCase(c)) {
                //rotate
                newChar = (char) ((((c - 'A') + rotationFactor + 26) % 26) + 'A');
            } else if (Character.isDigit(c)) {
                //rotate
                newChar = (char) ((((c - '0') + rotationFactor + 10) % 10) + '0');
            } else {
                newChar = c;
            }
            sb.append(newChar);
        }
        return sb.toString();
    }
  
    public List<List<String>> groupStrings(String[] strings) {
        //Alternative : normalize all strings to start with a fixed char, say 'a'. 
        
        Map<String, List<String>> shiftPatternVsStrings = new HashMap<>();

        //Find the string which share a common shift pattern 
        for (String s : strings) {
            String key = getShiftPattern(s);
            shiftPatternVsStrings.computeIfAbsent(key, k -> new ArrayList<>()).add(s);
        }

        return new ArrayList<>(shiftPatternVsStrings.values());
    }

    String getShiftPattern(String s) {
        StringBuilder sb = new StringBuilder(); 
        char comma = ' '; 

        for(int i=1; i<s.length(); i++) {
            sb.append(comma); 
            int diff = ((s.charAt(i) - s.charAt(i-1)) + 26) % 26; //we add 26 before modulo to avoid negatives (a-z) and to handle z-a kind of scenatios
            sb.append(diff); 
            comma = ','; 
        }

        return sb.toString().trim(); 
    }
}
