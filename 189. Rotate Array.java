/*
// version 1: TIME: O(n), SPACE: O(n) ---  too bad
public class Solution {
    public void rotate(int[] nums, int k) {
        int l = nums.length;
        k = k % l;
        int[] dummy = Arrays.copyOf( nums, l );
        
        for( int i = 0; i < l; i++){
            if( i < k ){
                nums[i] = dummy[l - k + i];
            }else{
                nums[i] = dummy[i - k];
            }
        }
        
    }
}

// version 2:  TIME: O(n), SPACE: O(1) ---  too bad
public class Solution {
    public void rotate(int[] nums, int k) {
        if(nums.length <= 1){
            return;
        }
        //step each time to move
        int step = k % nums.length;
        int[] tmp = new int[step];
        for(int i = 0; i < step; i++){
            tmp[i] = nums[nums.length - step + i];
        }
        for(int i = nums.length - step - 1; i >= 0; i--){
            nums[i + step] = nums[i];
        }
        for(int i = 0; i < step; i++){
            nums[i] = tmp[i];
        }
    }
}
*/

// version 3:  TIME: O(n), SPACE: O(1) ---  not too bad
// swap two variable  ---  make use of  ' a^b^b = a '
public class Solution {
    public void rotate(int[] nums, int k) {
        if(nums.length <= 1){
            return;
        }
        //step each time to move
        int step = k % nums.length;
        reverse(nums,0,nums.length - 1);
        reverse(nums,0,step - 1);
        reverse(nums,step,nums.length - 1);
    }
    
    //reverse int array from n to m
    public void reverse(int[] nums, int n, int m){
        while(n < m){
            nums[n] ^= nums[m];
            nums[m] ^= nums[n];
            nums[n] ^= nums[m];
            n++;
            m--;
        }
    }
}