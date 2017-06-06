public class Solution {
    public int maxProfit(int[] prices) {
        if( prices == null || prices.length < 2 ) return 0;
        
        int l = prices.length;
        int res = 0;
        for( int i = 1; i < l; i++ ){
            if( prices[i] - prices[i-1] > 0 ){
                res += prices[i] - prices[i-1];
            }
        }
        return res;
    }
}