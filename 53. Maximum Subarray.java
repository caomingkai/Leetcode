
// version 1: Kedane's Algorithm - maxCur = max{ maxPrev + curVal, curVal }
//  Optimization problem - could be solved by DP
public class Solution {
    public int maxSubArray(int[] nums) {
        int maxGlobal = nums[0];
        int maxCurrent = nums[0];
        
        for( int i = 1; i < nums.length; i++ ){
            maxCurrent = Math.max( maxCurrent+nums[i], nums[i] );
            maxGlobal =  Math.max( maxCurrent, maxGlobal );
        }
        return maxGlobal;
    }
}