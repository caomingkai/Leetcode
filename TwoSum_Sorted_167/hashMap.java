// author: Mingkai Cao

// HashMap O(n)
// Note: this synchronous way( put, query ) can make sure identical items will be found if they are the target result.
//      However, first put nums into HashMap, and then loop through nums to find if there exits items for the current item's 
//      comlement.
//      This way, if there exists identical values, and they are the target value. The HashMap will only contain different items
//      So identical num results wouldn't be found out. This way fails.

import java.util.HashMap;

public class Solution {
    public int[] twoSum(int[] numbers, int target) {
       
       HashMap<Integer, Integer> MapNums = new  HashMap<Integer, Integer>();
       int len = numbers.length;
       
       for(int i = 0; i < len; i++ ){
           if( MapNums.get( numbers[i] ) != null ){
               return new int[]{ MapNums.get( numbers[i] )+1 , i+1 };
           }else{
               MapNums.put( target - numbers[i], i );
           }
           
       }
       
       return new int[]{0,0};
    }
}