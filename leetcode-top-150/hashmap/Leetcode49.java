/*
 * 49. Group Anagrams
 * Given an array of strings strs, group the anagrams together. You can return the answer in any order.
 */
class Leetcode49 {
    public List<List<String>> groupAnagrams(String[] strs) {
        HashMap<String, List<String>> anagrams = new HashMap<>();
        for (String s : strs) {
            char[] word = s.toCharArray();
            Arrays.sort(word);
            String sortedString = new String(word);
            List<String> groups = anagrams.get(sortedString);
            if (groups == null) {
                groups = new ArrayList<>();
            }
            groups.add(s);
            anagrams.put(sortedString, groups);
        }
        return new ArrayList<>(anagrams.values()); 
    }
}
