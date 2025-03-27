/*
 * 227. Basic Calculator II
 * Given a string s which represents an expression, evaluate this expression and return its value.  
 * The integer division should truncate toward zero. 
 * You may assume that the given expression is always valid. All intermediate results will be in the range of [-231, 231 - 1]. 
 * Note: You are not allowed to use any built-in function which evaluates strings as mathematical expressions, such as eval().
 * 
 * Constraints:
   ** 1 <= s.length <= 3 * 105
   ** s consists of integers and operators ('+', '-', '*', '/') separated by some number of spaces.
   ** s represents a valid expression.
   ** All the integers in the expression are non-negative integers in the range [0, 231 - 1].
   ** The answer is guaranteed to fit in a 32-bit integer.
 */
class Leetcode227 {
    public int calculate(String s) {
        //TODO : Stack-less impl
        s = s.trim();
        if(s.matches("[0-9]+")) {
            return Integer.parseInt(s); 
        }
        Deque<Integer> stack = new ArrayDeque<>();
        char operation = '+';
        int currentNum = 0;
        for (int i = 0; i < s.length(); i++) {            
            char currentChar = s.charAt(i);

            if (Character.isDigit(currentChar)) {
                currentNum = (currentNum * 10) + (currentChar - '0');
            }
            if (!Character.isDigit(currentChar) && !Character.isWhitespace(currentChar) || i == s.length() - 1) {
                switch (operation) {
                    case '-':
                        stack.offer(-currentNum);// add last
                        break;
                    case '+':                        
                        stack.offer(currentNum);
                        break;
                    case '*':
                        stack.offer(stack.pollLast() * currentNum);
                        break;
                    case '/':
                        stack.offer(stack.pollLast() / currentNum);
                        break;
                }
                operation = currentChar;
                currentNum = 0;
            }
        }
        int result = 0;
        while (!stack.isEmpty()) {
            result += stack.pop();
        }
        return result;
    }
}
