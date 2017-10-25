/*
class Solution {
    public int singleNumber(int[] nums) {
        
        if( nums == null || nums.length == 0 )
            return 0;
        int res = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        int n = nums.length;
        for( int i = 0; i < n; i++ ){
            map.put( nums[i], map.getOrDefault( nums[i], 0)+1 );
        }
        
        for( int key : map.keySet() ){
            if( map.get(key) == 1 ){
                res = key;
                break;
            }
        }
        
        return res;
    }
}
*/

//version 2

class Solution {
    public int singleNumber(int[] nums) {
        int ones = 0, twos = 0;
        for (int i = 0; i < nums.length; i++) {
            ones = (ones ^ nums[i]) & ~twos;
            twos = (twos ^ nums[i]) & ~ones;
        }
        return ones;
    }
}