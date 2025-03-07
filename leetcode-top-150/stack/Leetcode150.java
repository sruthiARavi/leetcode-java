/*
 * 150. Evaluate Reverse Polish Notation
 * You are given an array of strings tokens that represents an arithmetic expression in a Reverse Polish Notation.
 * Evaluate the expression. Return an integer that represents the value of the expression.
 * Note that:
   ** The valid operators are '+', '-', '*', and '/'.
   ** Each operand may be an integer or another expression.
   ** The division between two integers always truncates toward zero.
   ** There will not be any division by zero.
   ** The input represents a valid arithmetic expression in a reverse polish notation.
   ** The answer and all the intermediate calculations can be represented in a 32-bit integer.
 */
class Solution {
    private static final Map<String, BiFunction<Integer, Integer, Integer>> OPERATIONS = new HashMap<>();

    static {
        OPERATIONS.put("+", Integer::sum);
        OPERATIONS.put("-", (a, b) -> a - b);
        OPERATIONS.put("*", (a, b) -> a * b);
        OPERATIONS.put("/", (a, b) -> a / b);
    }

    public int evalRPN(String[] tokens) {
        Deque<Integer> dq = new ArrayDeque<>(); // use stack if thread-safety is required
        for (String token : tokens) {
            if (!OPERATIONS.containsKey(token)) {
                dq.offer(Integer.valueOf(token));
                continue;
            }
            int num2 = dq.removeLast();
            int num1 = dq.removeLast();
            BiFunction<Integer, Integer, Integer> operation = OPERATIONS.get(token); // token is the operand;
            int result = operation.apply(num1, num2);
            dq.addLast(result);
        }
        return dq.pop();
    }
}
