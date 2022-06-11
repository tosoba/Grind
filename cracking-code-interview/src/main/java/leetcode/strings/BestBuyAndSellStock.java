package leetcode.strings;

public class BestBuyAndSellStock {
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0 || prices.length == 1) return 0;

        int[] mins = new int[prices.length];
        mins[1] = prices[0];
        for (var i = 2; i < prices.length; ++i) {
            mins[i] = Math.min(prices[i - 1], mins[i - 1]);
        }

        var max = 0;
        for (var i = 1; i < prices.length; ++i) {
            var diff = prices[i] - mins[i];
            if (diff > max) max = diff;
        }
        return max;
    }

    public static void main(String[] args) {
        System.out.println(new BestBuyAndSellStock().maxProfit(new int[]{7, 6, 4, 3, 1}));
    }
}
