// version 0: two pointer
// i find non-zero elements
// j track the 1st zero element ( it would update its position as the non-zero item insert in front )

public void moveZeroes(int[] nums) {

    int j = 0;
    for(int i = 0; i < nums.length; i++) {
        if(nums[i] != 0) {
            int temp = nums[j];
            nums[j] = nums[i];
            nums[i] = temp;
            j++;
        }
    }
}

/*
// version 1: since the number of '0' is not gonna change, if we shift all non-zero elements to left, remaining have to be '0'
// Shift non-zero values as far forward as possible
// Fill remaining space with zeros
public void moveZeroes(int[] nums) {
    if (nums == null || nums.length == 0) return;        

    int insertPos = 0;
    for (int num: nums) {
        if (num != 0) nums[insertPos++] = num;
    }        

    while (insertPos < nums.length) {
        nums[insertPos++] = 0;
    }
}
*/


/*
//version 2: loop from end, swap zero and non-zero elements
public class Solution {
    public void moveZeroes(int[] nums) {
        int l = nums.length;
        for( int i = l -1 ; i >= 0; i--){
            if( nums[i] == 0 ){
                swap( i, nums );
            }
        }
    }
    
    private void swap( int index , int[] n ){
        int l = n.length;
        for( int i = index; i < l - 1 ; i++ ){
            int t = n[i];
            n[i] = n[i+1];
            n[i+1] = t;
        }
    }
 
}

*/