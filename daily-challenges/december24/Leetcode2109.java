/*
 * 
 * You are given a 0-indexed string s and a 0-indexed integer array spaces that describes the indices in the original string where spaces will be added. 
 * Each space should be inserted before the character at the given index.
   ** For example, given s = "EnjoyYourCoffee" and spaces = [5, 9], 
   ** we place spaces before 'Y' and 'C', which are at indices 5 and 9 respectively. 
   ** Thus, we obtain "Enjoy Your Coffee".
 * Return the modified string after the spaces have been added.
 */
class Leetcode2109 {
    public String addSpaces(String s, int[] spaces) {
        int spacesIdx = 0;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            if (spaces[spacesIdx] == i) {
                sb.append(" ").append(s.charAt(i));
                spacesIdx++;
                if (spacesIdx >= spaces.length) {
                    sb.append(s.substring(i + 1));
                    break;
                }
                continue;
            }
            sb.append(s.charAt(i));
        }
        return sb.toString();
    }
}
