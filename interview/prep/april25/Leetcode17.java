/*
 * 17. Letter Combinations of a Phone Number
 * Given a string containing digits from 2-9 inclusive, return all possible letter combinations that the number could represent. 
 * Return the answer in any order.
 * A mapping of digits to letters (just like on the telephone buttons) is given below. Note that 1 does not map to any letters.
 * Constraints:
   *** 0 <= digits.length <= 4
   *** digits[i] is a digit in the range ['2', '9'].
 */
class Leetcode17 {
    //dfs backtracking :
    /*
        add something 
        call dfs method 
        delete what you added 
     */

    Map<Character, String> map = Map.of(
            '2',
            "abc",
            '3',
            "def",
            '4',
            "ghi",
            '5',
            "jkl",
            '6',
            "mno",
            '7',
            "pqrs",
            '8',
            "tuv",
            '9',
            "wxyz");

    public List<String> letterCombinations(String digits) {
        List<String> res = new ArrayList<>();
        if (digits.length() > 0) {
            backtracking(digits, 0, res, new StringBuilder());
        }
        return res;
    }

    void backtracking(String digits, int idx, List<String> res, StringBuilder sb) {
        if (sb.length() == digits.length()) {
            res.add(sb.toString());
            return;
        }

        for (char c : map.get(digits.charAt(idx)).toCharArray()) {
            sb.append(c);
            backtracking(digits, idx + 1, res, sb);
            sb.deleteCharAt(sb.length() - 1);
        }
    }
}
