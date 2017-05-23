
// ArrayList<T>( Collections );

public class Solution {
    public List<Integer> findDisappearedNumbers(int[] nums) {
        int l = nums.length;
        Set<Integer> res = new HashSet<>();
        
        for( int i = 1; i <= l;  i++){
            res.add(i);
        }
        
        for( int i = 0; i < l; i++){
            if( res.contains(nums[i]) ){
                res.remove(nums[i]);
            }
        }
        
        return new ArrayList<Integer>(res);
    }
}