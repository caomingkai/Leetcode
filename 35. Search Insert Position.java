// h <= t , return h, which is the expected position to be inserted
public class Solution {
    public int searchInsert(int[] nums, int target) {
        int h = 0;
        int t = nums.length - 1;
        int m = ( h + t ) / 2;
        
        while( h <= t ){
            m = ( h + t ) / 2;
            if( nums[m] == target ){
                return m;
            }else if( nums[m] < target ){
                h = m + 1;
            }else{
                t = m - 1;
            }
        }
        return h;
        // return target > nums[m]  ? m+1 : m;
    }
}
