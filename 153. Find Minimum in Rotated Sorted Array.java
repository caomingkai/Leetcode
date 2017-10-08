//     3 4 5 6 7 8 9 1 2 

class Solution {
    public int findMin(int[] nums) {
        
        // return val:

        // edge case: no
        
        // general case:
        int n = nums.length;
        int left = 0;
        int right = n - 1;
        int mid = 0;
        
        while( left < right ){
            mid = left + ( right - left ) / 2;
            if( nums[mid] >= nums[left] && nums[mid] >  nums[right]){
                left = mid + 1;
            }else{
                right = mid;
            }
            System.out.println( left + ":" + right + ":" + mid);
        }
        return nums[left];
    }
}