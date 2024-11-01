/* 
 * 1957. Delete Characters to Make Fancy String
 * A fancy string is a string where no three consecutive characters are equal.
 * Given a string s, delete the minimum possible number of characters from s to make it fancy.
 * Return the final string after the deletion. It can be shown that the answer will always be unique.
 */

class Leetcode1957 {
    public String makeFancyString(String s) {
        if (s.length() < 3) {
            return s;
        }
        Character curr = null;
        int currCount = 0;
        StringBuilder sb = new StringBuilder();
        for (Character c : s.toCharArray()) {
            if (c.equals(curr)) {
                currCount++;
            } else {
                currCount = 1;
            }
            if (currCount < 3) {
                sb.append(c);
            }
            curr = c;
        }
        return sb.toString();
    }
}
