/*
 * Reverse Substrings Between Each Pair of Parentheses
 * You are given a string s that consists of lower case English letters and brackets.
 * Reverse the strings in each pair of matching parentheses, starting from the innermost one.
 * Your result should not contain any brackets.
 */
class Leetcode1190{
    public String reverseParentheses(String s) {
        Stack<Integer> openParanthesesIndices = new Stack<>(); 
        StringBuilder sb = new StringBuilder(); 

        for(char c : s.toCharArray()) {
            if(c == '(') {
               openParanthesesIndices.push(sb.length());  
            } else if(c == ')') {
                int startIndex = openParanthesesIndices.pop(); 
                reverse(sb, startIndex, sb.length()-1); 
            } else {
                sb.append(c); 
            }
        }
        return sb.toString(); 
    }

    private void reverse(StringBuilder sb, int startIndex, int endIndex) {
        while(startIndex<endIndex) {
            char temp = sb.charAt(startIndex); 
            sb.setCharAt(startIndex++, sb.charAt(endIndex)); 
            sb.setCharAt(endIndex--, temp); 
        }
    }
}
