/*
 * 2405. Optimal Partition of String
 * Given a string s, partition the string into one or more substrings such that the characters in each substring are unique. 
 * That is, no letter appears in a single substring more than once.
 * Return the minimum number of substrings in such a partition.
 * Note that each character should belong to exactly one substring in a partition.
 * Constraints:
   ** 1 <= s.length <= 105
   ** s consists of only English lowercase letters.
 */
class Leetcode2405 {
    public int partitionString(String s) {
        boolean[] partitionChars = new boolean[26];
        int partitionCount = 1;
        for (char c : s.toCharArray()) {
            if (partitionChars[c - 'a']) {
                partitionCount++;
                partitionChars = new boolean[26];
            }
            partitionChars[c - 'a'] = true;
        }
        return partitionCount;
    }

    public int partitionString(String s) {
        int partitions = 0;
        Set<Character> charsInPartition = new HashSet<>();
        for (int i = 0; i < s.length(); i++) {
            char currentChar = s.charAt(i);
            if (charsInPartition.contains(currentChar)) {
                partitions++;
                charsInPartition.clear();
            }
            charsInPartition.add(currentChar);
        }
        if (!charsInPartition.isEmpty()) {
            partitions++;
        }
        return partitions;
    }
}
