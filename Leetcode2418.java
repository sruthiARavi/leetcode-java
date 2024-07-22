/*
 * Sort the People
 * You are given an array of strings names, and an array heights that consists of distinct positive integers. Both arrays are of length n.
 * For each index i, names[i] and heights[i] denote the name and height of the ith person.
 * Return names sorted in descending order by the people's heights.
 */
class Leetcode2418 {
    public String[] sortPeople(String[] names, int[] heights) {
        //All heights are distinct
        //Making use of treemap's existing sorting algorithm
        TreeMap<Integer, String> htVsName = new TreeMap<Integer, String>(Comparator.reverseOrder()); 
        for(int i=0; i<names.length; i++) {
            htVsName.put(heights[i], names[i]); 
        }
        return htVsName.values().toArray(new String[names.length]); 
    }

    private String[] mergeSortImplementation(String[] names, int[] heights) {
        //TODO
    }
}
