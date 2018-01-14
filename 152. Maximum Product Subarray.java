
/*

KET IDEA: keep record of both max and min value for current index, because there exist 
    cases: negative*negative = positive

   [2, 3, -2,  4,  -3,   4]
p   2  6  -2,  4   3*48  3*4*48
n   2  3  -12 -48  -3    -12

p[i]: is the largest product ending with ith element
f[i]: is the smallest product ending with ith element


max[i] = Math.max( Math.max(max[i-1] * nums[i] , min[i-1] * nums[i] ), nums[i] );
min[i] = Math.min( Math.min(max[i-1] * nums[i] , min[i-1] * nums[i] ), nums[i] );
            
*/


class Solution {
    public int maxProduct(int[] nums) {
        if( nums == null || nums.length == 0 ) return 0;
        
        int res = nums[0];
        int l = nums.length;
        
        int[] max = new int[l];  
        int[] min = new int[l];
        max[0] = nums[0];
        min[0] = nums[0];
        
        for( int i = 1; i < l; i++ ){
            max[i] = Math.max( Math.max(max[i-1] * nums[i] , min[i-1] * nums[i] ), nums[i] );
            min[i] = Math.min( Math.min(max[i-1] * nums[i] , min[i-1] * nums[i] ), nums[i] );
            res = Math.max( res, max[i] );
        }
        return res;
    }
}