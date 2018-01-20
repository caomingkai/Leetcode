 /*
 [3, 2，3], k = 2
 
 [1, 2, 3, 3, 3, 4, 5], avg =7
  
t = sum/k  // 1 - check sum%k == 0
max < t    // 2 - max < t


1. using a hashmap to store window content, deciding the window moving ( or bucket array )
2. using two pointers to limit the window

*/
class Solution {

    public boolean canPartitionKSubsets(int[] nums, int k) {    
    
        int sum = 0;
        for(int item: nums)
            sum += item;
        
        // if cannot divided by k, then cannot partition
        if( sum % k != 0 ) 
            return false;
        
        int subSum = sum/k;
        Arrays.sort(nums);
        
        // since already sorted, the solo value will be at the end
        int idx = nums.length-1;
        while( idx > 0 && nums[idx] == subSum ){
            idx--;   // delete the solo value which itself is subSem
            k--;     // corresponding number of groups decrement by 1
        }
        for( int i: nums )
            System.out.print(i + " ");
        System.out.println();
        // idx - we traverse the idx from back to front
        return canPartition( new int[k], idx, nums, subSum );
        
    }
    
    
    
    /*
        try all the k groups for the selected element.
        see if the following elements could satisfy with this try
            - if others satisfy, return true
            - if don't satisfy, keep trying other group for this selected element
    
    */
    private boolean canPartition(  int[] groups, int idx, int[] nums, int target ){
        if( idx < 0 )
            return true;
        for( int item: groups ){
            System.out.print(item + " ");
        }            
        System.out.println();
        int selected = nums[idx];
        for( int i = 0; i < groups.length; i++ ){
            if( groups[i] + selected <= target ){
                groups[i] += selected;
                if( canPartition(groups, idx-1, nums, target) )
                    return true;
                else
                    groups[i] -= selected;
            }
        }
        
        return false;
    }
}
