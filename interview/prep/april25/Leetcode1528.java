/*
 * 1528. Shuffle String
 * You are given a string s and an integer array indices of the same length. 
 * The string s will be shuffled such that the character at the ith position moves to indices[i] in the shuffled string.
 * Return the shuffled string.
 * Constraints:
   ** s.length == indices.length == n
   ** 1 <= n <= 100
   ** s consists of only lowercase English letters.
   ** 0 <= indices[i] < n
 * All values of indices are unique
 * Example :
   ** Input: s = "codeleet", indices = [4,5,6,7,0,2,1,3]
   ** Output: "leetcode"
   ** Explanation: As shown, "codeleet" becomes "leetcode" after shuffling.
 */
class Leetcode1528 {
    public String restoreString(String s, int[] indices) {

        int n =s.length();
         String restored = "";
      char [] result = new char[s.length()];

      for(int i=0;i<s.length();i++){
        result[indices[i]]=s.charAt(i);
      }

      return new String(result);
    }
}
