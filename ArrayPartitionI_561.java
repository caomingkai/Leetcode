
public class Solution {
    public int arrayPairSum(int[] nums) {
        

        // bucket sort
        int len = nums.length;
        final int MAX = 10000;
        int n[] = new int[2 * MAX + 1];
        for( int i = 0; i < len; i++){
            n[ nums[i] +  MAX ]++;
        }
        
        // get sorted array
        int j = 0;
        for(int i = 0; i < MAX*2+1; i++){
            for( int k = 0; k < n[i]; k++){
                nums[j++] = i - MAX;
            }
        }
        
        
        //get the min pair
        int minSum = 0;
        for(int i = 0; i < len/2 ; i++){
            minSum += nums[2*i];
        }
        return minSum;
    }
}