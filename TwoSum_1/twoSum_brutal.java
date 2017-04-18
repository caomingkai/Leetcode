// author: Mingkai Cao

// brutal force: O(n2)


public class Solution {
    public int[] twoSum(int[] nums, int target) {
        
        int[] result = new int[2];
        
        for(int i = 0; i < nums.length; i++){
            int j = i+1; 
            while( ( j < nums.length )  && ( nums[i] + nums[j] != target )){
                 j++;
            }
            
            if(j < nums.length){
                result[0] = i;
                result[1] = j;
            }
        }
        return result;
    }
}