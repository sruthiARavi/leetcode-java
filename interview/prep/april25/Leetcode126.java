/*
 * 126. Word Ladder II
 * A transformation sequence from word beginWord to word endWord using a dictionary wordList is a sequence of words 
 * beginWord -> s1 -> s2 -> ... -> sk such that:
   ** Every adjacent pair of words differs by a single letter.
   ** Every si for 1 <= i <= k is in wordList. Note that beginWord does not need to be in wordList.
   ** sk == endWord
 * Given two words, beginWord and endWord, and a dictionary wordList, 
 * return all the shortest transformation sequences from beginWord to endWord, or an empty list if no such sequence exists. 
 * Each sequence should be returned as a list of the words [beginWord, s1, s2, ..., sk]. 
 */
import java.util.*;

class Leetcode126 {
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        List<List<String>> result = new ArrayList<>();
        Set<String> wordSet = new HashSet<>(wordList);
        if (!wordSet.contains(endWord))
            return result;

        Map<String, List<String>> allComboDict = new HashMap<>();
        int len = beginWord.length();

        // Build adjacency pattern map
        for (String word : wordSet) {
            for (int i = 0; i < len; i++) {
                String pattern = word.substring(0, i) + "*" + word.substring(i + 1);
                allComboDict.computeIfAbsent(pattern, k -> new ArrayList<>()).add(word);
            }
        }

        Map<String, List<String>> parents = new HashMap<>();
        Map<String, Integer> visitedLevel = new HashMap<>();
        Queue<AbstractMap.SimpleEntry<String, Integer>> queue = new LinkedList<>();

        queue.offer(new AbstractMap.SimpleEntry<>(beginWord, 1));
        visitedLevel.put(beginWord, 1);

        boolean found = false;
        int minDepth = Integer.MAX_VALUE;

        while (!queue.isEmpty()) {
            AbstractMap.SimpleEntry<String, Integer> entry = queue.poll();
            String currentWord = entry.getKey();
            int level = entry.getValue();

            if (level > minDepth)
                break; // Don't explore deeper levels once endWord is found

            for (int i = 0; i < len; i++) {
                String pattern = currentWord.substring(0, i) + "*" + currentWord.substring(i + 1);
                for (String neighbor : allComboDict.getOrDefault(pattern, new ArrayList<>())) {
                    if (!visitedLevel.containsKey(neighbor)) {
                        visitedLevel.put(neighbor, level + 1);
                        queue.offer(new AbstractMap.SimpleEntry<>(neighbor, level + 1));
                    }

                    if (visitedLevel.get(neighbor) == level + 1) {
                        parents.computeIfAbsent(neighbor, k -> new ArrayList<>()).add(currentWord);
                    }

                    if (neighbor.equals(endWord)) {
                        found = true;
                        minDepth = level + 1;
                    }
                }
            }
        }

        if (found) {
            LinkedList<String> path = new LinkedList<>();
            path.add(endWord);
            backtrack(beginWord, endWord, parents, path, result);
        }

        return result;
    }

    private void backtrack(String beginWord, String word, Map<String, List<String>> parents,
            LinkedList<String> path, List<List<String>> result) {
        if (word.equals(beginWord)) {
            LinkedList<String> validPath = new LinkedList<>(path);
            Collections.reverse(validPath);
            result.add(validPath);
            return;
        }

        for (String parent : parents.getOrDefault(word, new ArrayList<>())) {
            path.addLast(parent);
            backtrack(beginWord, parent, parents, path, result);
            path.removeLast();
        }
    }
}
