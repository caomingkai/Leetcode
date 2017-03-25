/* 
 if nums == null || nums.length == 0, return 0
 min = 0;
 two pointer headPointer = 0 ,rearPointer = 0
 loop through to the end, while ( rp<=hp || hp<=length-1)
     if     sum== s, compare min and (hp-rp+1), update min, go to loop agian
     elseif sum > s, rp++, go to loop again
     else   sum < s, hp++, go to loop again

== why two pointer can tranverse throughly?
    -- rp start from 0, and the hp adjust its location trying to make sum == s
    -- since the sum become larger and larger as hp go forward step by step, there is only one value ==s if there is one
    -- if there is none such subarray, rp go forward by one step, and hp again adjust itself to find a proper location
    -- this way, rp go through from start to the end, it is complete!
*/ 

import java.util.Arrays;

public class Solution {
    public int minSubArrayLen(int s, int[] nums) {
        
        if(nums == null || nums.length == 0){
            return 0;
        }
        
        int sum = 0, hp = 0, rp = 0; // hp: headPointer, rp: rearPointer
        int min = Integer.MAX_VALUE; // the length of subarray could both be Integer.MAX_VALUE
        int length = nums.length;
        
        while( hp < length ){
            
            sum += nums[hp++];
            
            while( sum >= s){
                min = Math.min( min, ( hp - rp ) );
                sum -= nums[rp++];
            }
        }
        return min == Integer.MAX_VALUE ? 0 : min;
    }
}