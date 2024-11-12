/*
 * 3159. Find Occurrences of an Element in an Array
 * You are given an integer array nums, an integer array queries, and an integer x.
 * For each queries[i], you need to find the index of the queries[i]th occurrence of x in the nums array. 
 * If there are fewer than queries[i] occurrences of x, the answer should be -1 for that query.
 * Return an integer array answer containing the answers to all queries.
 */
class Leetcode3159 {
    public int[] occurrencesOfElement(int[] nums, int[] queries, int x) {
        List<Integer> queriesList = Arrays.stream(queries).boxed().toList();
        int maxQuery = Collections.max(queriesList);
        int availableX = 0;
        Map<Integer, Integer> xOccurrenceIndex = new HashMap<Integer, Integer>();

        for(int i=0; i<nums.length; i++) {
            if(nums[i] == x) {
                availableX++;
                xOccurrenceIndex.put(availableX, i);
                if(availableX == maxQuery) {
                    break;
                }
            }
        }

        if(xOccurrenceIndex.isEmpty()) {
            return IntStream.generate(()->-1).limit(queries.length).toArray();
        }

        int[] result = new int[queries.length];
        for(int i=0; i<queries.length; i++) {
            result[i] = xOccurrenceIndex.getOrDefault(queries[i], -1);
        }
        return result;
    }
}
