/*
 * Valid Parentheses
 * Given a string s containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.
 * An input string is valid if:
 * 1. Open brackets must be closed by the same type of brackets.
 * 2. Open brackets must be closed in the correct order.
 * 3. Every close bracket has a corresponding open bracket of the same type.
 */

import static java.util.Map.entry;

class Leetcode20 {
    static final Map<Character, Character> BRACES = Map.ofEntries(
            entry('(', ')'),
            entry('{', '}'),
            entry('[', ']'));

    public boolean isValid(String s) {
        if (s.length() % 2 != 0) {
            return false;
        }

        Stack<Character> stringOpenBraces = new Stack<>();
        for (Character c : s.toCharArray()) {
            if (BRACES.containsKey(c)) {
                stringOpenBraces.push(c);
            } else {
                if (stringOpenBraces.isEmpty()) {
                    return false;
                }
                Character lastOpenBrace = stringOpenBraces.peek();
                Character expectedBrace = BRACES.get(lastOpenBrace);
                if (c.equals(expectedBrace)) {
                    stringOpenBraces.pop();
                } else {
                    return false;
                }
            }
        }
        return stringOpenBraces.isEmpty();
    }

    boolean betterSolution(String s) {// best space complexity
        Stack<Character> stack = new Stack();
        for (char x : s.toCharArray()) {
            if (x == '(') {
                stack.push(')');
            } else if (x == '[') {
                stack.push(']');
            } else if (x == '{') {
                stack.push('}');
            } else if (stack.isEmpty() || stack.pop() != x) {
                return false;
            }

        }
        return stack.isEmpty();
    }

    boolean betterTimeComplexity(String s) {
        Stack<Character> stack = new Stack<>();

        for (char c : s.toCharArray()) {
            if (c == '(' || c == '{' || c == '[')
                stack.push(c);
            else {
                if (stack.isEmpty())
                    return false;
                char d = stack.pop();
                if ((c == ')' && d != '(') || (c == '}' && d != '{') || (c == ']' && d != '['))
                    return false;

            }
        }
        if (stack.isEmpty())
            return true;
        else
            return false;

    }
}
