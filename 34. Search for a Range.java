/*
    [5, 7, 7, 8, 8, 8, 8, 8, 10]

    find the first occurence of target value
    n[m] < t  : l=m+1
    n[m] >= t : h=m
    
    l < h : two element left
    
    m=(l+h)/2
    
    return right;
    

*/
// version 1: find out the startIdx using BS; then keep finding its right elements
class Solution {
    public int[] searchRange(int[] nums, int target) {
        
        if( nums == null || nums.length == 0 )
            return new int[] {-1, -1 };
        
        int leftIdx = BinarySearch( nums, target );
        
        if( leftIdx == -1 )
            return new int[] {-1, -1 };
        
        int rightIdx = leftIdx;
        while( rightIdx < nums.length && nums[rightIdx] == nums[leftIdx] ){
            rightIdx++;
        }
        
        return new int[]{leftIdx, rightIdx-1 };
    }
    
    
    // find out the first elements <= target
    private int BinarySearch( int[] nums, int target ){
        int l = 0;
        int r = nums.length-1;
        
        while( l < r ){
            int m = l + ( r - l )/2;
            if( nums[m] < target )
                l = m+1;
            else
                r = m;
        }
        
        return nums[r]==target ? r : -1;
    }
    
}
*/


// version 2:  call Binary search twice 
//             1. find index for target 
//             2. find index for target+1, and then decrement by 1 is the right index of the range
class Solution {
    public int[] searchRange(int[] nums, int target) {
    	int[] result = new int[2];

    	int lo = searchStart(nums, target);
    	int hi = searchStart(nums, target + 1) - 1;

    	if (lo == nums.length || nums[lo] != target)
    		return new int[]{-1, -1};

    	result[0] = lo;
    	result[1] = hi;

    	return result;
    }
    
    public int searchStart(int[] nums, int target) {
    	int lo = 0, hi = nums.length, mid;
    	while (lo < hi) {
    		mid = (lo + hi)/2;
    		if (nums[mid] < target) {
    			lo = mid + 1;
    		}
    		else {
    			hi = mid;
    		}
    	}
    	return lo;
    }
}