// Version 1: HashMap
public class Solution {
    public int[] intersection(int[] nums1, int[] nums2) {
        
        int l1 = nums1.length;
        int l2 = nums2.length;
        int l = l1>l2 ? l2 : l1;
        int[] res = new int[l];
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        
        for( int i = 0; i < l1; i++){
            map.put( nums1[i], 1 );
        }
        
        int j = 0;
        for( int i = 0; i < l2; i++){
            if( j == l) break;
            if( map.get( nums2[i] ) != null ){
                res[j] = nums2[i];
                j++;
                map.remove( nums2[i] );
            } 
        }
        
        return Arrays.copyOfRange(res, 0, j);
    }
}

// Version 2: sort one of them, and binary search items of the other array in the former array