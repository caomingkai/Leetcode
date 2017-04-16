public class Solution {
    public int removeDuplicates(int[] nums) {
        if(nums.length == 0){
            return 0;
        }
        
        int newIndex = 0;
        int cur = nums[0];
        int length = nums.length;
        for(int i = 1; i < length; i++){
            if( cur != nums[i] ){
                newIndex++;
                nums[newIndex] = nums[i];
                cur = nums[i];
            }
        }
        return newIndex+1;
    }
}