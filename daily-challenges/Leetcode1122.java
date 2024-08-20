/*
 * Relative sort array 
 * Given two arrays arr1 and arr2, the elements of arr2 are distinct, and all elements in arr2 are also in arr1.
 * Sort the elements of arr1 such that the relative ordering of items in arr1 are the same as in arr2. 
 * Elements that do not appear in arr2 should be placed at the end of arr1 in ascending order.
 */
class Leetcode1122 {
    public int[] relativeSortArray(int[] arr1, int[] arr2) {
        Map<Integer, Integer> countMap = new HashMap<>(); 
        List<Integer> remaining = new ArrayList<>(); 
        List<Integer> result = new ArrayList<>(); 

        for(int value : arr2) {
            countMap.put(value, 0); 
        }

        for(int value : arr1) {
            if(countMap.containsKey(value)) {
                countMap.put(value, countMap.get(value)+1); 
            } else {
                remaining.add(value); 
            }
        }

        for(int value : arr2) {
            for(int j=0; j<countMap.get(value); j++) {
                result.add(value); 
            }
        }

        Collections.sort(remaining); 
        result.addAll(remaining); 

        return result.stream().mapToInt(Integer :: intValue).toArray(); 
    }
}
