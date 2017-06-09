

public class Solution {
    public int removeElement(int[] nums, int val) {
        int sum = 0;
        int i = 0;              // head pointer
        int j = nums.length - 1;// tail pointer
         
        while( i <= j ){
            if( nums[i] != val )  i++;
            else if( nums[j] == val )  j--; 
            else if( nums[i] == val && nums[j] != val ){
                int temp = nums[i];
                nums[i] = nums[j];
                nums[j] = temp;
                i++;
                j--;
            }
        }
        return i;
    }
}


/*
public class Solution {
    public int removeElement(int[] nums, int val) {
        int sum = 0;
        int j = 0;
        int l = nums.length;
        for( int i = 0; i < l; i++ ){
            if( nums[i] != val ){
                nums[j++] = nums[i];
            }
        }
        return j;
    }
}

*/