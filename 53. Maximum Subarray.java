
/*
   [-1, 1, 5,-11, 4, -1, 2, 1,-5, 4]

[0  -1, 0, 5,-6 ,-2, -3,-1, 0,-5,-1 ]
              |             |
              i             j
*/
// key idea: find out (i,j) index whose subtraction is max ---- how????
// 关键点在于记录当前遇到的最小值，在该值之后的元素更新最大值。遇到最小值则更新，从新在新最小值之后更新最大值
class Solution {
    /*
    public int maxSubArray(int[] nums) {
        int res = nums[0];
        int l = nums.length;
        int[] sum = new int[l+1];
        for (int i = 1; i < l+1; i++ ) {
            sum[i] = sum[i-1] + nums[i-1];
        }
        
        int min = sum[0];
        int max = sum[0];
        for ( int maxIdx = 1; maxIdx < l+1; maxIdx++ ){
            max = sum[maxIdx];
            res = Math.max( res, max - min );
            if( max < min ) min = max;
        }
        return res;
    }
    */
     
    
    //version 2: DP --> TIME: O(n)   SPACE:O(n) 
    // dp[i] means the largest sum among the subarrays whose last element is A[i]
    // 
    public int maxSubArray(int[] nums) {
        int l = nums.length;
        int[] dp = new int[l];
        dp[0] = nums[0];
        int max = dp[0];
       
        for( int i = 1; i < l; i++ ){
            dp[i] = dp[i-1]>0 ? dp[i-1]+nums[i] : nums[i];
            max = max>dp[i] ? max : dp[i];
        }
        return max;
    }
    
    
    /*
    // Version 3:DP ---> TIME: O(n)   SPACE:O(1) 
    public int maxSubArray(int[] A) {
        int res = Integer.MIN_VALUE, sum = 0;
        for (int i = 0; i < A.length; i++) {
            sum = Math.max(sum, 0) + A[i];
            res = Math.max(res, sum);
        }
        return res;
    } 
    */
    
}