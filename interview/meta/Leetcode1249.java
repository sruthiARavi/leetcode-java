/*
 * 1249. Minimum Remove to Make Valid Parentheses
 * Given a string s of '(' , ')' and lowercase English characters.
 * Your task is to remove the minimum number of parentheses ( '(' or ')', in any positions ) so that the resulting parentheses string is valid and return any valid string.
 * Formally, a parentheses string is valid if and only if:
   ** It is the empty string, contains only lowercase characters, or
   ** It can be written as AB (A concatenated with B), where A and B are valid strings, or
   ** It can be written as (A), where A is a valid string.
 */
class Leetcode1249 {
    public String minRemoveToMakeValid(String s) {
        char[] sArray = s.toCharArray();
        int openParenthesisCount = 0;

        // First pass: mark excess closing parentheses with '*'
        for (int i = 0; i < sArray.length; i++) {
            if (sArray[i] == '(') {
                openParenthesisCount++;
            } else if (sArray[i] == ')') {
                if (openParenthesisCount == 0) {
                    sArray[i] = '*'; // marking for removal;
                } else {
                    --openParenthesisCount;
                }
            }
        }

        // Second pass: mark excess opening parentheses from the end
        // process from right to get a valid result
        // consider the example "())()(((" to understand why processing from right is
        // better
        for (int i = sArray.length - 1; i >= 0 && openParenthesisCount > 0; i--) {
            if (sArray[i] == '(') {
                sArray[i] = '*'; // Mark excess opening parentheses
                --openParenthesisCount;
            }
        }

        // Filter out marked characters and store the result in the character array
        int p = 0; // Pointer for updating the character array
        for (int i = 0; i < sArray.length; i++) {
            if (sArray[i] != '*') {
                sArray[p++] = sArray[i];
            }
        }

        return new String(sArray).substring(0, p);

    }

    public String minRemoveToMakeValid1(String s) {
        Deque<Integer> openParenthesisIdx = new ArrayDeque<>();
        Set<Integer> idxToBeRemoved = new HashSet<>();

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                openParenthesisIdx.push(i);
            } else if (s.charAt(i) == ')') {
                if (openParenthesisIdx.isEmpty()) {
                    idxToBeRemoved.add(i);
                } else {
                    openParenthesisIdx.pop();
                }
            }
        }

        while (!openParenthesisIdx.isEmpty()) {
            idxToBeRemoved.add(openParenthesisIdx.pop());
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            if (!idxToBeRemoved.contains(i)) {
                sb.append(s.charAt(i));
            }
        }

        return sb.toString();
    }
}
