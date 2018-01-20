

/*
    [1, -1, 5, -2, 3], k = 3
[ 0, 1, 0 , 5,  3, 6]


*/
class Solution {
    public int maxSubArrayLen(int[] nums, int k) {
        if( nums == null || nums.length == 0 )
            return 0;
        
        int l = nums.length;
        int sum[] = new int[l+1];
        for( int i = 1; i < l+1; i++ )
            sum[i] = sum[i-1] + nums[i-1];
        
        int res = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for( int i = 0; i < l+1; i++ ){
            int item = sum[i];
            if( map.containsKey(item-k) )
                res = Math.max( res, i - map.get(item-k) );
            if( !map.containsKey(item) )
                map.put( item, i );
        }
        return res;
    }
}






/*
list   : [1, -1, 5, -2, 3] 
target : 3

      1:        1           .
     -1:     -1   .      0    .
      5:   5   . 5 .
     -2:  
      3:
*/
        
/*
// version 2: Misunderstanding, for another problem
// Misunderstanding:  this approach is suitable for Maximum Size 'SubSet' Sum Equals k
class Solution {
    int max = 0;
    public int maxSubArrayLen(int[] nums, int k) {
        int res = 0;
        
        maxSub( 0, nums, 0, k, 1);
        return max;
    }
    
    //  sum: last sum 
    //  cnt: last count
    private void maxSub( int idx, int[] nums, int sum, int target, int cnt ){
        
        
        if( idx == nums.length )
            return;
        
        // 1. use idx
        sum += nums[idx];
        if( sum == target )
             max = Math.max( max, cnt );
        maxSub( idx+1, nums, sum, target, cnt+1  );
         
        // 2. not use idx
        sum -= nums[idx];
        maxSub( idx+1, nums, sum, target, cnt );
        
        
    }
}
*/