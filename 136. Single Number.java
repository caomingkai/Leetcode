/*
// HashSet + O(n)
public class Solution {
    public int singleNumber(int[] nums) {
        Set< Integer > s = new HashSet<>();
        int l = nums.length;
        
        for( int i = 0; i < l; i++ ){
            if( s.contains( nums[i] ) ){
                s.remove( nums[i] );
            }else{
                s.add( nums[i] );
            }
        }
        return  s.toArray(new Integer[1] )[0] ;
    }
}
*/

// XOR --- A^A = 0      A^A^B = A^B^A = B
public class Solution {
    public int singleNumber(int[] nums) {
        
        int l = nums.length;
        int res = nums[0];
        for( int i = 1; i < l; i++ ){
            res ^= nums[i];
        }
        return  res;
    }
}
