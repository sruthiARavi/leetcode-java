/*
 * 127. Word Ladder
 * A transformation sequence from word beginWord to word endWord using a dictionary wordList is a sequence of words 
 * beginWord -> s1 -> s2 -> ... -> sk such that:
   ** Every adjacent pair of words differs by a single letter.
   ** Every si for 1 <= i <= k is in wordList. Note that beginWord does not need to be in wordList.
   ** sk == endWord
 * Given two words, beginWord and endWord, and a dictionary wordList, return the number of words in the shortest transformation sequence from beginWord to endWord, or 0 if no such sequence exists.
 * Constraints:
   ** 1 <= beginWord.length <= 10
   ** endWord.length == beginWord.length
   ** 1 <= wordList.length <= 5000
   ** wordList[i].length == beginWord.length
   ** beginWord, endWord, and wordList[i] consist of lowercase English letters.
   ** beginWord != endWord
   ** All the words in wordList are unique.
 * 
 * Examples 
 * Example 1:
   ** Input: beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log","cog"]
   ** Output: 5
   ** Explanation: One shortest transformation sequence is "hit" -> "hot" -> "dot" -> "dog" -> cog", which is 5 words long.
 * 
 * Example 2:
   ** Input: beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log"]
   ** Output: 0
   ** Explanation: The endWord "cog" is not in wordList, therefore there is no valid transformation sequence.
 * 
 */
class Leetcode127 {

    Map<String, List<String>> allCombinationsDict = new HashMap<>();

    private int traverseQueue(Queue<String[]> queue, Map<String, Integer> qVisited,
            Map<String, Integer> otherQVisited) {
        for (int j = queue.size(); j > 0; j--) { //traverse through all elts in current level only - for bfs 
            String[] qElts = queue.poll();
            String word = qElts[0];
            Integer level = Integer.parseInt(qElts[1]);
            for (int i = 0; i < word.length(); i++) {
                String newWord = word.substring(0, i) + '*' + word.substring(i + 1, word.length());

                for (String adjacentWord : allCombinationsDict.getOrDefault(newWord, new ArrayList<String>())) {
                    if (otherQVisited.containsKey(adjacentWord)) {
                        return level + otherQVisited.get(adjacentWord);
                    }
                    if (!qVisited.containsKey(adjacentWord)) {
                        qVisited.put(adjacentWord, level + 1);
                        queue.add(new String[] { adjacentWord, (level + 1) + "" });
                    }
                }
            }
        }
        return -1;
    }

    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        if (!wordList.contains(endWord)) {
            return 0;
        }

        //Treat as graph and build adjacency lists 
        //use bidirectional bfs for shortest path 

        allCombinationsDict.clear();

        //Build adjacency list 
        int length = beginWord.length();
        for (String word : wordList) {
            for (int i = 0; i < length; i++) {
                //replace letters one by one by replacing every i-th letter in the word 
                String newWord = word.substring(0, i) + '*' + word.substring(i + 1, length);
                List<String> adjacencyList = allCombinationsDict.getOrDefault(newWord, new ArrayList<String>());
                adjacencyList.add(word);
                allCombinationsDict.put(newWord, adjacencyList);
            }
        }

        //Queue for bfs 
        Queue<String[]> beginWordQ = new LinkedList<>();
        Queue<String[]> endWordQ = new LinkedList<>();
        //Visited map - visited word vs level 
        Map<String, Integer> beginQVisited = new HashMap<>();
        Map<String, Integer> endQVisited = new HashMap<>();
        //initialise queue elts 
        String[] beginElt = new String[] { beginWord, "1" };
        String[] endElt = new String[] { endWord, "1" };
        //initialise queue 
        beginWordQ.add(beginElt);
        endWordQ.add(endElt);
        //initialise visited map
        beginQVisited.put(beginWord, 1);
        endQVisited.put(endWord, 1);

        int ans = -1;
        while (!beginWordQ.isEmpty() && !endWordQ.isEmpty()) {
            if (beginWordQ.size() <= endWordQ.size()) {
                ans = traverseQueue(beginWordQ, beginQVisited, endQVisited);
            } else {
                ans = traverseQueue(endWordQ, endQVisited, beginQVisited);
            }

            if (ans > -1) {
                return ans;
            }

        }

        return 0;
    }
}
