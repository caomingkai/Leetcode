public class Solution {
    public int findUnsortedSubarray(int[] nums) {
        
        int len = nums.length;
        
        int copy[] = Arrays.copyOf( nums , len );
        
        Arrays.sort( copy );
        
        // find the first index with different elements
        int i = 0;
        while( i < len && nums[i] == copy[i] ){
            i++;
        }
        if( i == len ) return 0;
        
        // find the last index with different elements
        int j = len - 1;
        while( j > -1 && nums[j] == copy[j] ){
            j--;
        }
        if( j == -1 ) return 0;
        
        return j - i + 1;
    }
}