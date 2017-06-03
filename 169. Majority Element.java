/*
// version 1: HashMap
public class Solution {
    public int majorityElement(int[] nums) {
        Map<Integer, Integer> dict = new HashMap<>();
        int l = nums.length;
        for( int i = 0; i < l; i++)
        {
            dict.put( nums[i], dict.getOrDefault(nums[i],0) + 1 );
        }
        
        int m = nums[0];
        int r = 0;
        for( int key : dict.keySet() )
        {
            int v = dict.get(key);
            if(v > l/2 ){
                return key;
            }
        }
        return r;
    }
}
*/

// version 2: Moore Voting Algorithm
public class Solution {
    public int majorityElement(int[] nums) {
        int cnt = 1;
        int pointer = nums[0];
        
        int l = nums.length;
        for( int i = 1; i < l; i++ ){
            if( cnt == 0 ){
                pointer = nums[i];
                cnt = 1;
            }else{
                if( pointer == nums[i] ) 
                    cnt++;
                else
                    cnt--;
            } 
        }
        return pointer;
    }
}