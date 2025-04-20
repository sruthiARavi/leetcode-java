/*
 * 781. Rabbits in Forest
 * There is a forest with an unknown number of rabbits. 
 * We asked n rabbits "How many rabbits have the same color as you?" and 
 * collected the answers in an integer array answers where answers[i] is the answer of the ith rabbit.
 * Given the array answers, return the minimum number of rabbits that could be in the forest.
 * Constraints:
   ** 1 <= answers.length <= 1000
   ** 0 <= answers[i] < 1000
 */
class Leetcode781 {
    public int numRabbits(int[] answers) {
        /*
         * a / b + (a % b == 0 ? 0 : 1)
         * pad a just enough to simulate that +1 when needed
         * (a + b - 1) / b
         */

        // Map to store how many rabbits reported the same group size
        Map<Integer, Integer> groupCount = new HashMap<>();

        // Fill the map with the number of rabbits who reported each group size
        for (int answer : answers) {
            int groupSize = answer + 1; // Each answer indicates how many other rabbits have the same color, so we add 1 to get the group size
            groupCount.put(groupSize, groupCount.getOrDefault(groupSize, 0) + 1);
        }

        int totalRabbits = 0;

        // For each group size in the map, calculate the total number of rabbits needed
        for (Integer groupSize : groupCount.keySet()) {
            int numRabbitsInGroup = groupCount.get(groupSize);// Number of rabbits who gave this answer
            // Calculate how many full groups are required to accommodate these rabbits
            int numGroups = (numRabbitsInGroup + groupSize - 1) / groupSize;
            // Add the total number of rabbits in these full groups
            totalRabbits += numGroups * groupSize;
        }

        return totalRabbits;
    }
}
