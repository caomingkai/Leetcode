public class Solution {
    public int findMaxConsecutiveOnes(int[] nums) {
        if( nums == null || nums.length == 0){
            return 0;
        }
        
        int l = nums.length;
        int i = 0, max = 0, cnt = 0;
        
        while( i < l ){
            if( nums[i] == 1 ){
                cnt++;
            }else{
                max = cnt > max ? cnt : max;
                cnt = 0;
            }
            i++;
        }
        
        return cnt > max ? cnt : max;
    }
}