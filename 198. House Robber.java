/*
// version 1: DP with memoized table

// for each house, we could "rob" OR "don't rob" it. 
// we cannot tell which result would bigger: rob / not rob. So we Math.max( robWith(i), robWithout(i) )

// let say now we have find the optimal value for [0 ~ i-1], now we have robW(i-1) and robWo(i-1)
// then, 
// robW(i) = robWo(i-1) + nums[i]
// robWo(i) = Math.max( robW(i-1), robWo(i-1) )
// last,
// res = Math.max( robW(i), robWo(i) );
class Solution {
    public int rob(int[] nums) {
        // edge case
        if( nums == null || nums.length == 0 ) return 0;
        
        // general case
        int l = nums.length;
        int[] robW =  new int[l+1];  // memo
        int[] robWo = new int[l+1];  // robW i is corresponding to nums i-1 house
        
        for( int i = 0; i < l; i++ ){  // here rob
            robW[i+1]  = robWo[i] + nums[i];
            robWo[i+1] = Math.max( robW[i], robWo[i] );
        }
        
        return Math.max( robW[l], robWo[l] );
    }
}
*/
    
// version 2: DP with only two variables
// from recursive formula, we could easily tell that current value only depends on previous two value
// and since we only care about the last pair, not all the value pair, so we only need two variables
class Solution {
    public int rob(int[] num) {
        if( num == null || num.length == 0)
            return 0;

        int l = num.length;
        int robW = 0;
        int robWo = 0;

        for( int i = 0; i < l; i++ ){
            int robWnew = robWo + num[i];
            robWo = Math.max( robW, robWo);
            robW = robWnew;
        }
        return Math.max( robW, robWo );
    }
}