// Version 1: HashMap
public class Solution {
    public int[] intersect(int[] nums1, int[] nums2) {
        
        int l1 = nums1.length;
        int l2 = nums2.length;
        int l = l1>l2 ? l2 : l1;
        int[] res = new int[l];
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        
        for( int i = 0; i < l1; i++){
            map.put( nums1[i], map.getOrDefault(nums1[i], 0)+1 );
        }
        
        int j = 0;
        for( int i = 0; i < l2; i++){
            if( map.get( nums2[i] ) != null  ){
                int f = map.get( nums2[i] ) ;
                if( f > 0 ){
                    res[j] = nums2[i];
                    j++;
                    map.put( nums2[i], f-1 );
                }else{
                    map.remove( nums2[i] );
                }
            }
            if( j == l) break;
        }
        
        return Arrays.copyOfRange(res, 0, j);
    }
}

/*
// version 2:  two pointer
public class Solution {
    public int[] intersect(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        
        int ind1=0, ind2=0;
        
        ArrayList<Integer> list = new ArrayList<Integer>();
        
        while((ind1 < nums1.length) && (ind2 < nums2.length)){
            if(nums1[ind1] < nums2[ind2]){
                ind1++;
            }
            else if(nums1[ind1] > nums2[ind2]){
                ind2++;
            }
            else if(nums1[ind1] == nums2[ind2]){
                list.add(nums1[ind1]);
                ind1++;
                ind2++;
            }
        }
        int[] res= new int[list.size()];
        for(int i=0; i<res.length; i++){
            res[i] = list.get(i);
        }
        return res;
        
    }
}
