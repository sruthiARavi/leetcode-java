class Leetcode1346 {
    public boolean checkIfExist(int[] arr) {
        /*
         * We can use two pointer so that we check in place but worst time complexity
         *
         * Here it's extra space, but better time with Hashmap
         * We could use a frequency array instead of a map, which could be more
         * memory-efficient if the constraint were >= 0.
         * However, since this problem allows negative numbers, a frequency map is the
         * better choice.
         *
         * Alternatively, we could sort and search the array
         */
        HashMap<Float, Integer> valVsFrequency = new HashMap<>();
        for (int j : arr) {
            float num = (float) j;
            // Additional check simply to speed up the process
            if (checkInFrequencyMap(j, valVsFrequency)) {
                return true;
            }
            valVsFrequency.put(num, valVsFrequency.getOrDefault(num, 0) + 1);
        }
        for (int j : arr) {
            if (checkInFrequencyMap(j, valVsFrequency)) {
                return true;
            }
        }
        return false;
    }

    private boolean checkInFrequencyMap(int num, HashMap<Float, Integer> frequencyMap) {
        float num_float = (float) num;
        float num_double = (float) 2 * num_float;
        float num_half = (float) num / 2;
        // Handle zero case (ensure there are at least two zeros)
        if (num_float == 0.0f && frequencyMap.containsKey(num_float)) {
            return frequencyMap.get(num_float) > 1;
        }
        if (frequencyMap.containsKey(num_double) || frequencyMap.containsKey(num_half)) {
            return true;
        }
        return false;
    }
}
