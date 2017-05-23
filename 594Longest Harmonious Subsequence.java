
// HashTable
public class Solution {
    public int findLHS(int[] nums) {
        int l = nums.length;
        if( l < 2) return 0;
        
        Map<Integer, Integer> m = new TreeMap<>();
        for(int i = 0; i < l; i++){
            if( m.containsKey(nums[i]) ){
                m.put( nums[i], m.get(nums[i])+1 );
            }else{
                m.put( nums[i], 1 );
            }
        }
        
        Integer[] keyArr = m.keySet().toArray( new Integer[m.size()]);
        int len = keyArr.length;
        int i = 0;
        int max = 0;
        while( i + 1 < len ){
            if( keyArr[i+1] - keyArr[i] == 1 ){
                max = Math.max( m.get( keyArr[i+1] ) + m.get( keyArr[i] ),  max );
            }
            i++;
        }
        return max;
    }
}

/*
// two pointer
public class Solution {
    public int findLHS(int[] nums) {
        int l = nums.length;
        if( l < 2) return 0;
        
        Arrays.sort(nums);
        int s = 0; // slow pointer
        int f = 1; // fast pointer
        int max = 0;
        int dis = 1; // distance between fast pointer and slow pointer( actually it's inclusive, ie. dis + 1)
        boolean flag = false;// indicate we got a Harmonous Subsequence, not just identical nums, which should also be counted in HS
        while( f < l){
            if( nums[f] - nums[s] == 0 ){
                f++;
                if( f > s ) dis++;
            }else if( nums[f] - nums[s] == 1 ){
                flag = true;
                f++;
                dis++;
            }else{
                if( flag ) max = dis > max ? dis : max;
                flag = false;         
                s++;
                dis--;
            }
        }
        
        if( flag ) max = dis > max ? dis : max;
        return max;
    }
}
*/