/*
 * IPO 
 * Suppose LeetCode will start its IPO soon. 
 * In order to sell a good price of its shares to Venture Capital, LeetCode would like to work on some projects to increase its capital before the IPO. 
 * Since it has limited resources, it can only finish at most k distinct projects before the IPO. 
 * Help LeetCode design the best way to maximize its total capital after finishing at most k distinct projects.
 * You are given n projects where the ith project has a pure profit profits[i] and a minimum capital of capital[i] is needed to start it.
 * Initially, you have w capital. When you finish a project, you will obtain its pure profit and the profit will be added to your total capital.
 * Pick a list of at most k distinct projects from given projects to maximize your final capital, and return the final maximized capital.
 * The answer is guaranteed to fit in a 32-bit signed integer.
 */
class Leetcode502 {
    public int findMaximizedCapital(int k, int w, int[] profits, int[] capital) {
        PriorityQueue<int[]> pq_capital = new PriorityQueue<>((a, b) -> a[0] - b[0]); // min heap
        PriorityQueue<int[]> pq_profit = new PriorityQueue<>((a, b) -> b[1] - a[1]); // max heap

        for (int i = 0; i < capital.length; i++) { // capital or profits array - they are equal length
            pq_capital.add(new int[] { capital[i], profits[i] });
        }

        int minCapital = pq_capital.peek()[0];
        if (w < minCapital) {
            return 0;
        }

        //TODO : Find max capital and compare with w. If w>=max_capital, then invest in the highest profit project directly. 

        for (int i = 0; i < k; i++) {
            while (!pq_capital.isEmpty() && pq_capital.peek()[0] <= w) {
                pq_profit.add(pq_capital.poll());
            }

            if (pq_profit.isEmpty()) {
                break;
            }

            w += pq_profit.poll()[1]; // The profit specified is already the net profit so we just need to add it directly to the capital and NOT profit-capital
        }
        return w;
    }
}
