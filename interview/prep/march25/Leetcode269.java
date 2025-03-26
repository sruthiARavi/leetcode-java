/*
 * 269. Alien Dictionary
 * There is a new alien language that uses the English alphabet. However, the order of the letters is unknown to you.
 * 
 * You are given a list of strings words from the alien language's dictionary. 
 * Now it is claimed that the strings in words are sorted lexicographically by the rules of this new language.
 * 
 * If this claim is incorrect, and the given arrangement of string in words cannot correspond to any order of letters, return "".
 * 
 * Otherwise, return a string of the unique letters in the new alien language sorted in lexicographically increasing order by the new language's rules. 
 * If there are multiple solutions, return any of them.
 * Constraints:
   ** 1 <= words.length <= 100
   ** 1 <= words[i].length <= 100
   ** words[i] consists of only lowercase English letters.
 */
class Leetcode269 {
    public String alienOrder(String[] words) {
        // TODO : dfs impl

        // bfs impl-

        // 1. create and build an adjacency list and indegree map
        // 2. retrieve 0 in degree elements first and then do subsequent elements (this
        // is bfs) - use a q

        // Edge 1 : word 2 (small len) being the prefix of word 1 (large len)
        // Edge 2 : elts left in the indegree map post bfs, meaning there are cycles

        Map<Character, ArrayList<Character>> adjacencyMap = new HashMap<>();
        Map<Character, Integer> indegree_counts = new HashMap<>();

        for (String word : words) {
            // Character first_letter = word.charAt(0);
            for (char c : word.toCharArray()) {
                adjacencyMap.put(c, new ArrayList<>());
                indegree_counts.put(c, 0);
            }
        }

        for (int i = 0; i < words.length - 1; i++) {
            String word1 = words[i];
            String word2 = words[i + 1];
            if (word1.length() > word2.length() && word1.startsWith(word2)) {
                return "";
            }
            for (int j = 0; j < Math.min(word1.length(), word2.length()); j++) {
                if (word1.charAt(j) != word2.charAt(j)) {
                    adjacencyMap.get(word1.charAt(j))
                            .add(word2.charAt(j));
                    indegree_counts.put(word2.charAt(j),
                            indegree_counts.get(word2.charAt(j)) + 1);
                    break;
                }
            }
        }

        StringBuilder alien_alphabet_order = new StringBuilder();
        Queue<Character> processing_queue = new LinkedList<>();// FIFO

        // Adding all chars with in-degree 0
        for (Character c : indegree_counts.keySet()) {
            if (indegree_counts.get(c).equals(0)) {
                processing_queue.add(c);
            }
        }

        // bfs
        while (!processing_queue.isEmpty()) {
            Character curr_char = processing_queue.remove();
            alien_alphabet_order.append(curr_char);
            for (Character next_char : adjacencyMap.get(curr_char)) {
                indegree_counts.put(next_char,
                        indegree_counts.get(next_char) - 1);
                if (indegree_counts.get(next_char).equals(0)) {
                    processing_queue.add(next_char);
                    // should we just remove from map and at the end just check map size ?
                }
            }
        }

        if (alien_alphabet_order.length() < indegree_counts.size()) {
            return "";
        }

        return alien_alphabet_order.toString();
    }
}
