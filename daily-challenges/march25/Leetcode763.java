/*
 * 763. Partition
 * You are given a string s. We want to partition the string into as many parts as possible so that each letter appears in at most one part. 
 * For example, the string "ababcc" can be partitioned into ["abab", "cc"], but 
 * partitions such as ["aba", "bcc"] or ["ab", "ab", "cc"] are invalid.
 * Note that the partition is done so that after concatenating all the parts in order, the resultant string should be s.
 * Return a list of integers representing the size of these parts.
 * Constraints:
   ** 1 <= s.length <= 500
   ** s consists of lowercase English letters.
 */
class Leetcode763 {
    //763. Partition
    public List<Integer> partitionLabels(String s) {
        int[] lastOccurrence = new int[26];
        // Record the last occurrence index for each character
        for (int i = 0; i < s.length(); i++) {
            int idx = s.charAt(i) - 'a';
            lastOccurrence[idx] = i;
        }

        List<Integer> partitions = new ArrayList<>();
        int currPartitionStart = 0, currPartitionEnd = 0;
        for (int i = 0; i < s.length(); i++) {
            int idx = s.charAt(i) - 'a';
            currPartitionEnd = Math.max(currPartitionEnd, lastOccurrence[idx]);
            // If the current position matches the partition end, add the partition
            if (i == currPartitionEnd) {
                partitions.add(currPartitionEnd - currPartitionStart + 1);
                currPartitionStart = i + 1; //Start a new partition
            }
        }
        return partitions;
    }

    public List<Integer> partitionLabels1(String s) {
        List<Integer> partitions = new ArrayList<>();
        int[] firstOccurrence = new int[26];
        int[] lastOccurrence = new int[26];
        Arrays.fill(firstOccurrence, -1);
        Arrays.fill(lastOccurrence, -1);
        for (int i = 0; i < s.length(); i++) {
            int idx = s.charAt(i) - 'a';
            if (firstOccurrence[idx] != -1) {
                firstOccurrence[idx] = i;
            }
            lastOccurrence[idx] = i;
        }
        int currPartitionStart = 0, currPartitionEnd = 0;
        for (int i = 0; i < s.length(); i++) {
            /*
             * 1. Check if current char is part of the current partition
             * This can be the case when the current char is a new char whose first occurrence is beyond the current partition's end
             * If so, then update the current partition details to the result and start processing the new partition
             * 2. If not, then just check the current character's last occurrence and update partition size as required
             */
            int idx = s.charAt(i) - 'a';
            if (currPartitionEnd < firstOccurrence[idx]) {
                partitions.add(currPartitionEnd - currPartitionStart + 1); //adding +1 since it is 0-indexed
                currPartitionStart = i;
                currPartitionEnd = i;
            }
            currPartitionEnd = Math.max(currPartitionEnd, lastOccurrence[idx]);
        }
        // Add the last partition if it exists
        // For example, single partition case
        if (currPartitionEnd - currPartitionStart + 1 > 0) {
            partitions.add(currPartitionEnd - currPartitionStart + 1);
        }
        return partitions;
    }    
}
