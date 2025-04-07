/*
 * 13. Roman to Integer
 * Roman numerals are represented by seven different symbols: I, V, X, L, C, D and M.
 
                      Symbol       Value
                      I             1
                      V             5
                      X             10
                      L             50
                      C             100
                      D             500
                      M             1000
                      
 * For example, 2 is written as II in Roman numeral, just two ones added together. 
 * 12 is written as XII, which is simply X + II. The number 27 is written as XXVII, which is XX + V + II.
 * Roman numerals are usually written largest to smallest from left to right. 
 * However, the numeral for four is not IIII. Instead, the number four is written as IV. 
 * Because the one is before the five we subtract it making four. 
 * The same principle applies to the number nine, which is written as IX. 
 * There are six instances where subtraction is used:
   ** I can be placed before V (5) and X (10) to make 4 and 9. 
   ** X can be placed before L (50) and C (100) to make 40 and 90. 
   ** C can be placed before D (500) and M (1000) to make 400 and 900.
 * Given a roman numeral, convert it to an integer.
 * Constraints:
   ** 1 <= s.length <= 15
   ** s contains only the characters ('I', 'V', 'X', 'L', 'C', 'D', 'M').
   ** It is guaranteed that s is a valid roman numeral in the range [1, 3999].
 */
class Leetcode13 {
    HashMap<Character, Integer> romanCharVsIntValue = new HashMap<>(){{
        put('I', 1);
        put('V', 5);
        put('X', 10);
        put('L', 50);
        put('C', 100);
        put('D', 500);
        put('M', 1000); 
    }};
    
    public int romanToInt(String s) {
        int ans = 0; 
        for(int i=0; i<s.length(); i++) {
            int num = romanCharVsIntValue.get(s.charAt(i)); 
            int rightNum = 0; 
            if(i+1<s.length()) {
                rightNum = romanCharVsIntValue.get(s.charAt(i+1)); 
                if(num<rightNum) {
                    num = rightNum - num; 
                    i += 1; 
                }
            }
            ans += num; 
        }
        return ans; 
    }
}
