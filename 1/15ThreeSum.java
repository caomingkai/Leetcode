/*
    quadratic time : O(n2)
    one loop + two pointer
    loop from the 1st element to the last: temp=nums[i]
        - use two pointer scheme to find if there exit two 'other' element add up to (-temp)
            
        - if there exit two item ( j ,k )add up to (-temp)
            - if ( j != i && k != i) result.add(i,j,k)
            - else go to outer loop
        - else go to outer loop
        
    ==keypoint: how to eliminate duplicate result?
    - for i ( j, k  )
    - if value of num[i] has already been calculated this time, 
    - no need to visit adjacent items with the same value, skip over to next diffrent value
                                                 
*/
import java.util.ArrayList;
import java.util.Arrays;

public class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        
        List<List<Integer>> result = new ArrayList<List<Integer>>();  //   ????
        
        if( nums == null || nums.length < 3){
            return result;
        }
        
        Arrays.sort( nums );

        int length = nums.length;
        
        for(int i = 0; i < length - 2; i++){
            
            if (i == 0 || (i > 0 && nums[i] != nums[i-1])) {// if duplicate value, go back to for loop
                
                int j = i + 1;
                int k = length - 1;
                int sum = nums[j] + nums[k]; 
                int targetSum = -nums[i];
                
                while(j < k){
                    if(sum < targetSum){
                        j++;
                    }else if(sum > targetSum){
                        k--;
                    }else{      //sum == targetSum
                        List<Integer> triplet = Arrays.asList(nums[i], nums[j], nums[k]);
                        result.add(triplet);
                        while( j < k && nums[j] == nums[j+1] ) j++;//if duplicate val, go while loop, till encouter diffrent val
                        while( j < k && nums[k] == nums[k-1] ) k--;//if duplicate val, go while loop, till encouter diffrent val
                        j++;    //  step to next diffrent val
                        k--;    //  step to next diffrent val
                    }
                    if(j < k && k < length ){
                        sum = nums[j] + nums[k];
                    }
                } 
            }
        }
       return result; 
    }
}
