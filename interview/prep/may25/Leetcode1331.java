/*
 * 1331. Rank Transform of an Array
 * Given an array of integers arr, replace each element with its rank.
 * The rank represents how large the element is. The rank has the following rules:
   ** Rank is an integer starting from 1.
   ** The larger the element, the larger the rank. If two elements are equal, their rank must be the same.
   ** Rank should be as small as possible.
 * Constraints:
   ** 0 <= arr.length <= 105
   ** -109 <= arr[i] <= 109
 */
class Leetcode1331 {
    public int[] arrayRankTransform(int[] arr) {
        int[] sorted = arr.clone();
        Arrays.sort(sorted);

        Map<Integer, Integer> rankMap = new HashMap<>();
        int rank = 1;
        for (int num : sorted) {
            if (!rankMap.containsKey(num)) {
                rankMap.put(num, rank++);
            }
        }

        int[] result = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            result[i] = rankMap.get(arr[i]);
        }

        return result;
    }
}
