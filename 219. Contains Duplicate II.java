public class Solution {
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        
        if( nums == null || nums.length == 0 ) return false;
        int l = nums.length;
        
        // find out same elements
        // check if elements between satisfy the criteria: <= k , so we need find out min and max
        Map<Integer, Integer> d = new HashMap<>();  // key: the num, value: its index
        for( int i = 0; i < l; i++){
            if( d.containsKey( nums[i]) ){
                if( i - d.get( nums[i] ) <= k )
                    return true;
            }
            // must not use 'else' expression, since if current case don't satisfy, maybe latter case can satisfy
            // we need to include this element in for later use.
            d.put( nums[i] , i );
        }
        return false;
    }
}