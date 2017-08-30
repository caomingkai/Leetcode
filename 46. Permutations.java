class Solution {
    
    // Basically , it is a problem of Backtracking, with constraints that none of the three digits number should be same
    public List<List<Integer>> permute(int[] nums) {
        
        List<List<Integer>> res = new ArrayList<>();
        if( nums.length == 0 ) return res;
        
        LinkedList<Integer> prefix = new LinkedList<>();
        backtrack(nums, 0, prefix, res );
        return res;
    }
    
    // this method append current index's digit to prefix, update it, and do next index+1 calling backtrack() again
    private void backtrack( int[] nums, int index, LinkedList<Integer> prefix,  List<List<Integer>> res ){
        // base case:
        if( index == nums.length ){
            res.add(new LinkedList<>(prefix));
            return;
        }
        
        // recursive case:
        for( int i = 0; i < nums.length; i++){
            if( !prefix.contains(nums[i]) ){
                prefix.push(nums[i]);  // push num[i]
                backtrack( nums, index+1, prefix, res);
                prefix.pop();          // pop num[i] out; next iteration, push num[i+1]
            }
        }  
    }
}