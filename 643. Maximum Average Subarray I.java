class Solution {
    public double findMaxAverage(int[] nums, int k) {
        
        // edge case
        if( nums == null || nums.length == 0)
            return 0;
        
        // two pointers
        int l = nums.length;
        int lo = 0;
        int hi = lo + k - 1;
        int sum = 0;
        
        for( int i = lo; i <= hi && i < l; i++ ){
            sum += nums[i];
        }
        
        int max = sum;
        
        for( lo = 1; lo <= l - k; lo++ ){
            hi = lo + k - 1;
            sum += nums[hi] - nums[lo-1];  
            max = max < sum ? sum : max;
        }
        return (double)max/k;
    


    }
}