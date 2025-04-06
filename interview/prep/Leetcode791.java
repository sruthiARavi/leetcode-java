/*
 * 791. Custom Sort String
 * You are given two strings order and s. All the characters of order are unique and were sorted in some custom order previously.
 * Permute the characters of s so that they match the order that order was sorted. 
 * More specifically, if a character x occurs before a character y in order, then x should occur before y in the permuted string.
 * Return any permutation of s that satisfies this property.
 * Constraints:
   ** 1 <= order.length <= 26
   ** 1 <= s.length <= 200
   ** order and s consist of lowercase English letters.
   ** All the characters of order are unique.
 */
class Leetcode791 {
    public String customSortString(String order, String s) {
        /*
         *  Arrays.sort(sToCharArray, (c1, c2) -> {
            // The index of the character in order determines the value to be sorted by
            return order.indexOf(c1) - order.indexOf(c2);
            }
        });

         */
        Map<Character, Integer> charCount = new HashMap<>();
        for (char c : s.toCharArray()) {
            charCount.put(c, charCount.getOrDefault(c, 0) + 1);
        }
        StringBuilder sb = new StringBuilder();
        for (char c : order.toCharArray()) {
            if (charCount.containsKey(c)) {
                int count = charCount.get(c);
                sb.append(String.valueOf(c).repeat(count));
                charCount.remove(c); 
            }
        }
        for(char c : charCount.keySet()) {
            sb.append(String.valueOf(c).repeat(charCount.get(c)));
        }
        return sb.toString();
    }
}
