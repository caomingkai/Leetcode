/*
    [1, 100, 1, 1, 1, 100, 1, 1, 100, 1]
    
    i-th step can be reached from i-2 or i-1
    how to choose which one?
        - choose the min cost one
        - dp[i] : reach i-th step, the min cost
        - dp[i] = min{ dp[i-1]+cost[i] , dp[i-2]+cost[i] }
            if i-1
            
        - we need find the min one between dp[n] and dp[n-1]

*/


class Solution {
    public int minCostClimbingStairs(int[] cost) {
        
        int l = cost.length;
        int[] dp = new int[l];   //  the min cost at i-th step, NOTE: must stay on it, so cann't skip it
        
        dp[0] = cost[0];
        dp[1] = cost[1];    // not min(cost[0], cost[1]), because dp[i] mean stay at it in the end
        
        for( int i = 2; i < l; i++ )
            dp[i] = Math.min( dp[i-2], dp[i-1] ) + cost[i];
        
        return Math.min( dp[l-1], dp[l-2] );
    }
}