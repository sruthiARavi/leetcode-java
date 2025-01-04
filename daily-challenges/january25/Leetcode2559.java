/*
 * 2559. Count Vowel Strings in Ranges
 * You are given a 0-indexed array of strings words and a 2D array of integers queries.
 * Each query queries[i] = [li, ri] asks us to find the number of strings present 
 * in the range li to ri (both inclusive) of words that start and end with a vowel.
 * Return an array ans of size queries.length, where ans[i] is the answer to the ith query.
 * Note that the vowel letters are 'a', 'e', 'i', 'o', and 'u'.
 */
class Leetcode2559 {
    public int[] vowelStrings(String[] words, int[][] queries) {
        int[] result = new int[queries.length];
        int[] prefixSum = new int[words.length];
        Set<Character> vowels = new HashSet<>(
                Arrays.asList('a', 'e', 'i', 'o', 'u'));
        // calculate prefix sum
        int sum = 0;
        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            if (vowels.contains(word.charAt(0)) &&
                    vowels.contains(word.charAt(word.length() - 1))) {
                sum++;
            }
            prefixSum[i] = sum;
        }
        // calculate result
        for (int i = 0; i < queries.length; i++) {
            int[] query = queries[i];
            result[i] = prefixSum[query[1]] -
                    (query[0] == 0 ? 0 : prefixSum[query[0] - 1]);
        }
        return result;
    }
}
