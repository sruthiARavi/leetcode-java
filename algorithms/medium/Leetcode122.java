/*
 * 122. Best Time to Buy and Sell Stock II
 * You are given an integer array prices where prices[i] is the price of a given stock on the ith day.
 * On each day, you may decide to buy and/or sell the stock. 
 * You can only hold at most one share of the stock at any time. 
 * However, you can buy it then immediately sell it on the same day.
 * Find and return the maximum profit you can achieve.
 * Constraints:
   ** 1 <= prices.length <= 3 * 104
   ** 0 <= prices[i] <= 104
 */
class Leetcode122 {

    public int maxProfit(int[] prices) {
        if (prices.length == 1) {
            return 0;
        }
        //greedy 
        /*
         * Summing up all increases from one day to the next guarantees the same or better profit than trying to "time" the market manually, 
         * because you never miss an opportunity to capture upward movement — and you’re allowed unlimited transactions.
         * Same time and space complexity as the other solution but cleaner 
         */
        int profit = 0;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i - 1] < prices[i]) {
                profit += prices[i] - prices[i - 1];
            }
        }
        return profit;
    }

    public int maxProfitNotGreedy(int[] prices) {
        if (prices.length == 1) {
            return 0;
        }

        int currPrice = -1;
        int profit = 0;
        boolean readyToSell = false;
        for (int i = 0; i < prices.length; i++) {
            if (readyToSell && prices[i] > currPrice) {
                boolean shouldSell = false;
                if (i == prices.length - 1) {
                    shouldSell = true;
                } else {
                    if (prices[i + 1] < prices[i]) {
                        shouldSell = true;
                    } //hold otherwise 
                }
                if (shouldSell) {
                    profit += prices[i] - currPrice;
                    currPrice = -1;
                    readyToSell = false;
                }
            }
            if (!readyToSell) {
                //check if we can buy 
                if (i != prices.length - 1) {
                    if (prices[i + 1] > prices[i]) {
                        //buy
                        currPrice = prices[i];
                        readyToSell = true;
                    } //hold otherwise 
                }
            }
        }
        return profit;
    }
}
