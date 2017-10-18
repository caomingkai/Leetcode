class Solution {
    /*
    public boolean canPartition(int[] nums) {
        int n = nums.length;
        int sum = 0;
        
        for( int i = 0; i < n; i++ ){
            sum += nums[i];
        }
        
        if( sum % 2 != 0 ) return false;
        
        int t = sum/2;
        
        // dp[t][n] : for the number t, selecting from former n elements, see if they could add up to t.
        boolean[][] dp = new boolean[t+1][n+1];
        
        // Initialization: dp[]
        for( int i = 0; i < n; i++ ){ // for 0, selecting from former i elements, they could add up to t ( selecting 0 element )
            dp[0][i] = true;
        }
        
        for( int i = 1; i < t; i++ ){ // for i != 0, selecting from former 0 element, there is no way to add up to i.
            dp[i][0] = false;
        }
        
        for( int i = 0; i <= t; i++ ){
            for ( int j = 1; j <= n; j++ ){
                if( i >= nums[j-1] ){
                    dp[i][j] = dp[i][j-1] || dp[i-nums[j-1]][j-1];
                }else{
                    dp[i][j] = dp[i][j-1]; 
                }
            }
        }
        
        return dp[t][n];
    }
    */
    
    public boolean canPartition(int[] nums) {
        int sum = 0;

        for (int num : nums) {
            sum += num;
        }

        if ((sum & 1) == 1) {
            return false;
        }
        sum /= 2;

        int n = nums.length;
        boolean[] dp = new boolean[sum+1];  // means: till current iteration, the sum i can be satisfied or not
        Arrays.fill(dp, false);
        dp[0] = true;               // means: for former 0,1,2,3, ..,n elements, sum 0 can be selected from them( 0 element)

        for (int num : nums) {
            // for (int i = sum; i > 0; i--) {
            for (int i = 1; i <= sum; i++) {
            
                if (i >= num) {
                    dp[i] = dp[i] || dp[i-num];
                }
            }
        }

        return dp[sum];
    }
}