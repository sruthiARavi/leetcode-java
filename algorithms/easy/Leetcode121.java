/*
 * 121. Best Time to Buy and Sell Stock
 * You are given an array prices where prices[i] is the price of a given stock on the ith day.
 * You want to maximize your profit by choosing a single day to buy one stock and choosing a different day in the future to sell that stock.
 * Return the maximum profit you can achieve from this transaction. If you cannot achieve any profit, return 0.
 * Constraints:
   ** 1 <= prices.length <= 105
   ** 0 <= prices[i] <= 104
 */
class Leetcode121 {

    public int maxProfit(int[] prices) {
        int low = prices[0], profit = 0; 
        for(int price : prices) {
            profit = Math.max(profit, price-low); 
            low = Math.min(low, price); 
        }
        return profit; 
    }

    public int maxProfitTwoPtr(int[] prices) {
        //two pointer 
        if (prices.length == 1) {
            return 0;
        }

        int left = 0, right = 1, maxProfit = 0;

        while (left <= right && right < prices.length) {
            int currProfit = prices[right] - prices[left];

            if (prices[right] < prices[left]) {
                left = right;
            }
            right++;

            maxProfit = Math.max(maxProfit, currProfit);
        }

        return maxProfit;
    }

    public int maxProfitBruteForce(int[] prices) {
        int maxProfit = 0;
        for (int i = 0; i < prices.length; i++) {
            for (int j = i + 1; j < prices.length; j++) {
                maxProfit = Math.max(maxProfit, prices[j] - prices[i]);
            }
        }
        return maxProfit;
    }
}
