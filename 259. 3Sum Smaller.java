class Solution {
    public int threeSumSmaller(int[] nums, int target) {
        
        int cnt = 0;
        // edge case
        if( nums == null || nums.length < 3 )
            return cnt;
        
        // general case
        int l = nums.length;
        Arrays.sort(nums);
        for( int i = 0; i < l; i++ ){
            int j = i+1;
            int k = l-1;
            while( j < k ){
                int sum = nums[i] + nums[j] + nums[k];
                if( sum >= target ){
                    k--;
                }else{
                    cnt += k-j;
                    j++;
                }
            }
        }
        return cnt;
    }
}