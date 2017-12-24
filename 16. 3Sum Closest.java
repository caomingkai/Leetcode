class Solution {
    public int threeSumClosest(int[] nums, int target) {
        
        // no need considering edge case
        
        // general case

        int l = nums.length;
        int res = nums[0] + nums[1] + nums[l-1];
        
        Arrays.sort(nums);
        
        for( int a = 0; a < l-1; a++ ){
            int b = a+1;
            int c = l-1;
            while( b < c ){
                int sum = nums[a] + nums[b] + nums[c];
                if( sum < target )
                    b++;
                else if( sum > target )
                    c--;
                else
                    return sum;
                
                if( Math.abs(res-target) > Math.abs(sum-target) )
                    res = sum;
            }
        }
        
        return res;
    }
}