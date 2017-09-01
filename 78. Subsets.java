class Solution {
    
    //  version 1: subset is : use 3 digits to create all the set ( In fact, set is a kind of combination )
    //  [] [1] [2] [3] [1,2] [1,3] [2,3] [1,2,3]
    public List<List<Integer>> subsets(int[] nums) {
        if( nums.length == 0 ) return null;
        
        List<List<Integer>> res = new ArrayList<>();
        ArrayList<Integer> prefix = new ArrayList<>();
        backtrack(nums, prefix, 0, res );
        return res;
    }
    
    private void backtrack( int[] nums, ArrayList<Integer> prefix, int start, List<List<Integer>> res ){
        
        // backtrap stopping case (base cases) : store valid result
        res.add(new ArrayList<>(prefix));
            
        // recursion case:
        for( int i = start; i < nums.length; i++ ){
            prefix.add(nums[i]);
            backtrack( nums, prefix, i+1, res );
            prefix.remove( prefix.size() - 1 );
        }
    }
}