/*
 * 188. Best Time to Buy and Sell Stock IV
 * You are given an integer array prices where prices[i] is the price of a given stock on the ith day, and an integer k.
 * Find the maximum profit you can achieve. You may complete at most k transactions: i.e. you may buy at most k times and sell at most k times.
 * Note: You may not engage in multiple transactions simultaneously (i.e., you must sell the stock before you buy again).
 * 
 * Constraints:
   ** 1 <= k <= 100
   ** 1 <= prices.length <= 1000
   ** 0 <= prices[i] <= 1000
 */
class Leetcode188 {
    class Txn {
        int cost;
        int max_profit;

        public Txn(int c, int p) {
            cost = c;
            max_profit = p;
        }

    }

    public int maxProfit(int k, int[] prices) {
        //extending second solution 
        if (prices.length == 1) {
            return 0;
        }

        // Optimization for large k (equivalent to infinite transactions)
        if (k >= prices.length / 2) {
            //Now this problem becomes Best Time to Buy and Sell stock with no limitations on the transactions 
            int totalProfit = 0;
            for (int i = 1; i < prices.length; i++) {
                if (prices[i] > prices[i - 1])
                    totalProfit += prices[i] - prices[i - 1];
            }
            return totalProfit;
        }

        //Extending the solution for Best Time to Buy and Sell stock with a transaction limit of 2 
        List<Txn> txnDetails = new ArrayList<>();
        int l = prices.length;
        for (int i = 0; i < k; i++) {
            Txn t = new Txn(Integer.MAX_VALUE, 0);
            txnDetails.add(t);
        }

        for (int price : prices) {
            //Extending the solution for Best Time to Buy and Sell stock with a transaction limit of 1
            Txn t1 = txnDetails.get(0);
            t1.cost = Math.min(t1.cost, price);
            t1.max_profit = Math.max(t1.max_profit, price - t1.cost);

            int prevCost = t1.cost;
            int prevProfit = t1.max_profit;
            for (int i = 1; i < k; i++) {
                /* 
                 * Reinvest the profit gained from the previous transaction 
                 * so that you can use a single pass approach 
                 * instead of splitting the array into 2 at a point and getting max profit from each 
                 */ 
                Txn t2 = txnDetails.get(i);
                t2.cost = Math.min(t2.cost, price - prevProfit);
                t2.max_profit = Math.max(t2.max_profit, price - t2.cost);

                prevCost = t2.cost;
                prevProfit = t2.max_profit;
            }
        }

        return txnDetails.get(k - 1).max_profit;
    }
}
