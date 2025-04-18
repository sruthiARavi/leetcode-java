/*
 * 38. Count and Say
 * The count-and-say sequence is a sequence of digit strings defined by the recursive formula:
   ** countAndSay(1) = "1"
   ** countAndSay(n) is the run-length encoding of countAndSay(n - 1).
 * Run-length encoding (RLE) is a string compression method that works by replacing consecutive identical characters (repeated 2 or more times) 
 * with the concatenation of the character and the number marking the count of the characters (length of the run). 
 * For example, to compress the string "3322251" 
 * we replace "33" with "23", replace "222" with "32", replace "5" with "15" and replace "1" with "11". 
 * Thus the compressed string becomes "23321511".
 * Given a positive integer n, return the nth element of the count-and-say sequence.
 * Constraints:
   ** 1 <= n <= 30
 */
class Leetcode38 {
    public String countAndSay(int n) {
        String num = "1";
        for (int i = 1; i < n; i++) {
            num = countFrequency(num);
        }
        return num;
    }

    String countFrequency(String num) {
        char prev = num.charAt(0);
        int count = 1;
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < num.length(); i++) {
            char curr = num.charAt(i);
            if (prev == curr) {
                count++;
            } else {
                sb.append(count).append(prev);
                prev = curr;
                count = 1;
            }
        }
        sb.append(count).append(prev);
        /*if (sb.length() == 0) {
           sb.append(count).append(prev);  
        } else {
            sb.append(count).append(curr); 
        }*/
        return sb.toString();
    }

    String countFrequency1(String num) {
        List<List<Integer>> list = new ArrayList<>();
        char prev = num.charAt(0);
        int count = 1;
        char curr = '\0';
        for (int i = 1; i < num.length(); i++) {
            curr = num.charAt(i);
            if (prev == curr) {
                count++;
            } else {
                addToList(prev - '0', count, list);
                prev = curr;
                count = 1;
            }
        }
        if (list.isEmpty()) {
            addToList(prev - '0', count, list);
        } else {
            addToList(curr - '0', count, list);
        }
        return createStringFromList(list);
    }

    void addToList(int num, int count, List<List<Integer>> list) {
        list.add(new ArrayList<Integer>(List.of(num, count)));
    }

    String createStringFromList(List<List<Integer>> list) {
        StringBuilder sb = new StringBuilder();
        for (List<Integer> l : list) {
            sb.append(l.get(1)).append(l.get(0));
        }
        return sb.toString();
    }
}
