
/*

1 2 3 4 5
1 2 3 5 4
1 2 4 3 5
1 2 4 5 3
1 2 5 3 4
1 2 5 4 3
1 3 2 4 5
1 3 2 5 4
1 3 4 2 5

5 4 3 1 2
5 1 1 3 2 1
    i
5 1 2 3 1 1

i         l-1
0 1 2 3 4 5
7 6 5 3 2 1 


1 2  6 7
3 2 1 4
2 1 3 4
1 2 3 4

*/

class Solution {
    public void nextPermutation(int[] nums) {
        if( nums == null || nums.length <= 1 )
            return;
            
        int l = nums.length;  
        int i = l-2;
        while( i >= 0){
            if( nums[i] < nums[i+1] )
                break;
            i-- ;
        }
        
        if( i >= 0){
            int j = l-1;
            while( j > i){
                if( nums[j] > nums[i] )
                    break;
                j-- ;
            } 
            swap( nums, i, j);
        }
        reverseOrder(nums, i+1);
    }
    
    
    
    private void swap( int[] nums, int i, int j ){
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
    
    private void reverseOrder( int[] nums, int i ){ // i is the starting index need to be handle
        int l = nums.length;
        int mid = i+(l-1-i)/2;
        for( int k = i; k <= mid; k++ ){
            int temp = nums[k];
            nums[k] = nums[l-1+i-k];
            nums[l-1+i-k] = temp;
        }
    }

}