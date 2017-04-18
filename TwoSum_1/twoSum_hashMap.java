
// author: Mingkai Cao

// HashMap: n + n = 2n  ---> O(n)

// sort of notion of 'Complement'
// since the result is guanranteed, loop from beginning, put each item's complement and item's index into a map.
// If along the loop, we find an item is in the map as a key, we can say: this item is the 'husband' of a previous 'wife' item, which we put as a complement
import java.util.HashMap;

public class Solution {
    public int[] twoSum(int[] nums, int target) {
        
        int len = nums.length;
        int[] result = new int[2];
        HashMap<Integer, Integer> mapNums = new HashMap<Integer, Integer> ();
        
        for(int i=0; i < len;  i++ ){
            if( mapNums.containsKey( nums[i] ) ){
                result[0] = i;
                result[1] = mapNums.get( nums[i] );
                return result;
            }else{
                mapNums.put( target - nums[i], i );
            }
        }
        
        return result;
    }
}