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
