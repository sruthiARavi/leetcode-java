/*
 * Find the Encrypted String
 * You are given a string s and an integer k. Encrypt the string using the following algorithm:
   ** For each character c in s, replace c with the kth character after c in the string (in a cyclic manner).
 * Return the encrypted string.
 */
class Leetcode3210 {
    public String getEncryptedString(String s, int k) {      
        int strLen = s.length(); 
        StringBuilder sb = new StringBuilder(); 
        for(int i=0; i<strLen; i++) {
            char c = s.charAt(i); 
            int replPos = (i+k)%strLen; 
            /*while(replPos>(strLen-1)) {
                replPos = Math.abs(strLen-replPos); 
            }*/
            char replacement = s.charAt(replPos); 
            sb.append(replacement); 
        }
        return sb.toString(); 
    }
}
