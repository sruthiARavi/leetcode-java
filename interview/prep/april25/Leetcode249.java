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
