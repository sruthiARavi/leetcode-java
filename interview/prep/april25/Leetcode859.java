/*
 * 859. Buddy Strings
 * Given two strings s and goal, return true if you can swap two letters in s so the result is equal to goal, otherwise, return false.
 * Swapping letters is defined as taking two indices i and j (0-indexed) such that i != j and swapping the characters at s[i] and s[j].
 * For example, swapping at indices 0 and 2 in "abcd" results in "cbad".
 * Constraints:
   ** 1 <= s.length, goal.length <= 2 * 104
   ** s and goal consist of lowercase letters.
 */
class Leetcode859 {
    public boolean buddyStrings(String s, String goal) {
        if(s.length() != goal.length()) {
            return false; 
        }
        
        //If both are equal, check if atleast one duplicate char exists
        if(s.equals(goal)) {
            Set<Character> chars = new HashSet<>(); 
            for(char c : s.toCharArray()) {
                if(chars.contains(c)) {
                    return true; 
                }
                chars.add(c); 
            }
            return false; 
        }
        //Get the mismatched indices 
        //If only 2 mismatched indices, see if flip makes it equal 
        
        TreeMap<Character, Integer> sFrequencyMap = new TreeMap<>();
        TreeMap<Character, Integer> goalFrequencyMap = new TreeMap<>(); 
        Map<Character, List<Integer>> charLocations = new HashMap<>(); 
        
        for(char c : s.toCharArray()) {
            sFrequencyMap.put(c, sFrequencyMap.getOrDefault(c, 0)+1); 
        }
        
        for(int i=0; i<goal.length(); i++) {
            char c = goal.charAt(i); 
            goalFrequencyMap.put(c, goalFrequencyMap.getOrDefault(c, 0)+1);             
            charLocations.computeIfAbsent(c, k -> new ArrayList<>()).add(i);
            //charLocations.computeIfAbsent(c, new ArrayList<Integer>()).add(i); 
        }
        
        if(!sFrequencyMap.equals(goalFrequencyMap)) {
            return false; 
        }
        
        if(s.equals(goal)) {
            return sFrequencyMap.size() == 1;                            
        }
                
        //2 pointers        
        int i = 0; 
        char[] sChars = s.toCharArray(); 
        char[] goalChars = goal.toCharArray(); 
        while(i<sChars.length) {
            if(sChars[i] != goalChars[i]) {                              
                char toSwap = sChars[i]; 
                char currChar = goalChars[i];                     
                //int newCharPos = charLocations.get(toSwap).get(0);
                List<Integer> newCharPos = charLocations.get(toSwap);
                for(int idx : newCharPos) {
                    if(sChars[idx] != currChar) {
                        continue; 
                    }
                    goalChars[i] = toSwap; 
                    goalChars[idx] = currChar;                 
                    String sCharsStr = new String(sChars); 
                    String gCharsStr = new String(goalChars);
                    return sCharsStr.equals(gCharsStr); 
                }                
                return false; 
            }
            i++; 
        }
        return true; 
    }
}
