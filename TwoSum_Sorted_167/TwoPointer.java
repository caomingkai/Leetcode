// author: Mingkai Cao

// Two Pointer O(n)
// sorted array

public class Solution {
    public int[] twoSum(int[] numbers, int target) {
       
       int len = numbers.length;
       int lo = 0;
       int hi = len - 1;
       
       while( lo < hi && hi < len ){
           if( numbers[lo] + numbers[hi] == target ){
               return new int[]{ lo+1, hi+1 };
           }else if( numbers[lo] + numbers[hi] < target ){
               lo++;
           }else{
               hi--;
           }
       }
       
       return new int[]{0,0};
    }
}