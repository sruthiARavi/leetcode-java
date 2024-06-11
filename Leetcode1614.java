/*
 * Maximum Nesting Depth of the Parentheses
 * Given a valid parentheses string s, return the nesting depth of s. The nesting depth is the maximum number of nested parentheses.
 */

class Leetcode1614 {
    public int maxDepth(String s) {
        int maxNestDepth = 0;
        int bracketCount = 0; 
       // for(char c : s.toCharArray()) {
        for(int i=0; i<s.length(); i++) {
            if(s.charAt(i) == '(') {
                bracketCount++; 
                maxNestDepth = maxNestDepth > bracketCount ? maxNestDepth : bracketCount; 
            } else if (s.charAt(i) == ')') {
                bracketCount--; 
            }
        }
        return maxNestDepth;  
    }
}
