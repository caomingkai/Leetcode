/*
class Solution {
    public int maxDistance(List<List<Integer>> arrays) {
        int max1 = Integer.MIN_VALUE;
        int min1 = Integer.MAX_VALUE;
        int max2 = Integer.MIN_VALUE;
        int min2 = Integer.MAX_VALUE;
        
        int maxIdx = -1;
        int minIdx = -1;
        
        int len = arrays.size();
        
        for( int i = 0; i < len; i++ ){
            List<Integer> list = arrays.get(i);
            int small = list.get(0);
            int large = list.get(list.size()-1);
            if( max1 < large ){
                max2 = max1;
                max1 = large;
                maxIdx = i;
            }else if( max2 < large ){
                max2 = large;
            }
            
            if( min1 > small ){
                min2 = min1;
                min1 = small;
                minIdx = i;
            }else if( min2 > small ){
                min2 = small;
            }
        }
        // System.out.println(maxIdx + ":" + minIdx);
        // System.out.println(max1 + ":" + min1);
        // System.out.println(max2 + ":" + min2);
        if( maxIdx == minIdx )
            return Math.max( max1 - min2 ,  max2 - min1 );
                
        return max1-min1;
    }
}
*/

//  version 2: 只用两个变量就行，关键在于“别自己跟自己比较”
//             而是“拿自己的最大，与之前的最小比较”  +  “拿自己的最小与之前的最大比较”
public class Solution {
    public int maxDistance(List<List<Integer>> arrays) {
        int result = Integer.MIN_VALUE;
        int max = arrays.get(0).get(arrays.get(0).size() - 1);
        int min = arrays.get(0).get(0);
        
        for (int i = 1; i < arrays.size(); i++) {
            result = Math.max(result, Math.abs(arrays.get(i).get(0) - max));
            result = Math.max(result, Math.abs(arrays.get(i).get(arrays.get(i).size() - 1) - min));
            max = Math.max(max, arrays.get(i).get(arrays.get(i).size() - 1));
            min = Math.min(min, arrays.get(i).get(0));
        }
        
        return result;
    }
}