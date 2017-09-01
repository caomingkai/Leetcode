class Solution {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        if( nums.length == 0 ) return null;
        
        List<List<Integer>> res = new ArrayList<>();
        ArrayList<Integer> prefix = new ArrayList<>();
        
        Arrays.sort(nums);
        backtrack(nums, 0, prefix, res);
        return res;
    }
    
    private void backtrack( int[] nums, int level, ArrayList<Integer> prefix, List<List<Integer>> res){
        res.add( new ArrayList<>(prefix));
        
        for( int i = level; i < nums.length; i++ ){ // skip over the duplicate value
            if( i > level && nums[i] == nums[i-1] ) continue;
            prefix.add( nums[i] );
            backtrack( nums, i+1, prefix, res);
            prefix.remove( prefix.size() - 1 );
        }
    }
}