/*
 * 1662. Check If Two String Arrays are Equivalent
 * Given two string arrays word1 and word2, return true if the two arrays represent the same string, and false otherwise.
 * A string is represented by an array if the array elements concatenated in order forms the string.
 *
 * Example 1:
   ** Input: word1 = ["ab", "c"], word2 = ["a", "bc"]
   ** Output: true
   ** Explanation:
     *** word1 represents string "ab" + "c" -> "abc"
     *** word2 represents string "a" + "bc" -> "abc"
     *** The strings are the same, so return true.
 * 
 * Constraints:
   ** 1 <= word1.length, word2.length <= 103
   ** 1 <= word1[i].length, word2[i].length <= 103
   ** 1 <= sum(word1[i].length), sum(word2[i].length) <= 103
   ** word1[i] and word2[i] consist of lowercase letters.
 */
class Leetcode1662 {
    public boolean arrayStringsAreEqual(String[] word1, String[] word2) {
        //return (String.join("", word1)).equals(String.join("", word2));
      
        int idx1 = 0, idx2 = 0, p1 = 0, p2 = 0; 

        while(idx1 < word1.length && idx2 < word2.length) {
            // Compare current characters
            if(word1[idx1].charAt(p1) != word2[idx2].charAt(p2)) {
                return false; 
            }

            // Move to next character in word1[idx1]
            p1++; 
            if(p1 == word1[idx1].length()) {
                idx1++; 
                p1=0; 
            }

            // Move to next character in word2[idx2]
            p2++; 
            if(p2 == word2[idx2].length()) {
                idx2++; 
                p2=0; 
            }            

        }

        return idx1 == word1.length && idx2 == word2.length; //both completely traversed 
    }
}
