/*
 * Kth Distinct String in an Array
 * A distinct string is a string that is present only once in an array.
 * Given an array of strings arr, and an integer k, return the kth distinct string present in arr. 
 * If there are fewer than k distinct strings, return an empty string "".
 * Note that the strings are considered in the order in which they appear in the array.
 */

class Leetcode2053 {
    public String kthDistinct(String[] arr, int k) {

        /*
         * We can also maintain 2 sets and solve this problem -
         * one for distinct strings and one for duplicate strings
         */

        // Maintain a frequency map for each string in the array
        Map<String, Integer> stringFreq = new HashMap<>();

        // Populate frequency map
        for (String s : arr) {
            stringFreq.put(s, stringFreq.getOrDefault(s, 0) + 1);
        }

        // Find kth distinct string
        for (String s : arr) {
            if (stringFreq.get(s) == 1) { // Check if String is distinct
                k--;
            }

            /*
             * Check if it is the kth String
             * Order is maintained as we are looping in the array's order of elements
             */
            if (k == 0) {
                return s;
            }
        }
        return "";
    }
}
