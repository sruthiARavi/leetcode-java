/*
 * 767. Reorganize String
 * Given a string s, rearrange the characters of s so that any two adjacent characters are not the same.
 * Return any possible rearrangement of s or return "" if not possible.
 * 
 * Example 1:
   ** Input: s = "aab"
   ** Output: "aba"
 * Example 2:
   ** Input: s = "aaab"
   ** Output: ""
 * 
 * Constraints:
   ** 1 <= s.length <= 500
   ** s consists of lowercase English letters.
 */
class Leetcode767 {
    public String reorganizeString(String s) {
        if (s.length() == 1) {
            return s;
        }

        Map<Character, Integer> freq = new HashMap<>();
        for (char c : s.toCharArray()) {
            freq.put(c, freq.getOrDefault(c, 0) + 1);
        }

        PriorityQueue<Map.Entry<Character, Integer>> pq = new PriorityQueue<>((a, b) -> b.getValue() - a.getValue());
        pq.addAll(freq.entrySet());

        StringBuilder sb = new StringBuilder();
        Map.Entry<Character, Integer> prev = null;
        while (!pq.isEmpty()) {
            Map.Entry<Character, Integer> curr = pq.poll();
            sb.append(curr.getKey());
            if (prev != null && prev.getValue() != 0) {
                pq.offer(prev); //add prev entry only if val > 0 
            }
            curr.setValue(curr.getValue() - 1);
            prev = curr;
        }
        return sb.length() == s.length() ? sb.toString() : "";
    }
}
