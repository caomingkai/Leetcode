// try to simplify circle DP to linear DP
// the optimal result must exist in either of following three cases:
// 1 - rob 1st, but not rob last house, 
// 2 - rob last, but not rob 1st house, 
// 3 - neithr rob 1st nor rob last house,
// the 3rd case can be included by 1st case or 2nd case, so 3rd case needn't be considerated
class Solution {
    public int rob(int[] nums) {
        if( nums == null || nums.length == 0) return 0;
        if( nums.length == 1 ) return nums[0];
        return Math.max( robLinear( nums, 0, nums.length-2),  // rob 1st, but not rob last house, 
                         robLinear( nums, 1, nums.length-1) );// neithr rob 1st nor rob last house 
    }
    
    private int robLinear( int[] nums, int lo, int hi ){
        int robY = 0, robN = 0;                // max for robY and robN
        for( int i = lo; i <= hi; i++ ){
            int robCur = robN + nums[i];      // rob i: Yes
            robN = Math.max( robY, robN );     // rob i: No
            robY = robCur;
        }
    return Math.max(robY, robN);  
    }
    

}