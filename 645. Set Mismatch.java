class Solution {
    public int[] findErrorNums(int[] nums) {
        

        int[] res = new int[2];
        
        // edge case
        if( nums==null || nums.length == 0)
            return new int[0];
        
        // bucket cnt
        int n = nums.length;
        int[] bucket = new int[n+1];
        
        for( int i = 0; i < n; i++ ){
            bucket[nums[i]]++;
            if( bucket[nums[i]] == 2 )
                res[0] = nums[i];
        }
        
        for( int i = 1; i <= n; i++ ){
            if( bucket[i] == 0 )
                res[1] = i;
        }
        
        return res;
    }
}