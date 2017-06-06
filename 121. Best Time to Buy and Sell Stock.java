/*
// version 1: in 1-loop, update min value, and max-diff
// as loop goes on, we update two things:
// 1. update the min value
// 2. updata maxProfit ( current value - min value )
// 2.1 since loop goes forward, selling time could surely latter than buying time, which is ( min-picking-point is earlier than cur-selling-point )
public class Solution {
    public int maxProfit(int[] prices) {
        if( prices == null || prices.length < 2 ) return 0;
        
        int min = prices[0];    // global
        int maxPro = 0;         // global
        int l = prices.length;
        
        for( int i = 0; i < l; i++ ){
            if( prices[i] < min) {
                min = prices[i];
            }
            if( prices[i] - min > maxPro ) maxPro = prices[i] - min;
        }
        return maxPro;
    }
}
*/

// version 2: Kadane's Algorithm - find max-sum subarray(contigious) from an array
// 1 - calculate diff of (current day price - previous day price) for each day
// 2 - find out the contigious subarray with max sum, using Kadane's Algorithm
public class Solution {
    public int maxProfit(int[] prices) {
        if( prices == null || prices.length < 2 ) return 0;
        
        int l = prices.length;
        int[] diff = new int[l-1];
        for( int i = 0; i < l-1; i++ ){
            diff[i] = prices[i+1]-prices[i];
        }
        
        int maxCur = diff[0];
        int maxGlo = diff[0];
        for( int i = 1; i < l-1; i++ ){
            maxCur = Math.max( diff[i], maxCur + diff[i] );// Kadane's Algorithm: maxSum- itself OR itself plus previous max-sum
            maxGlo = Math.max( maxCur, maxGlo );
        }
        return maxGlo < 0 ? 0 : maxGlo;
    }
}