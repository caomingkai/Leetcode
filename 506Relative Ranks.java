// 1. when copy an array, NEVER use 'int[] a = b;', this way it will modify b when you manipulate a; USE Arrays.copyOf() instead
// 2. First sort, and then find( binary search )
// 3. time: O(nlogn)
// 4. space: O(n)
// 5. there could be other solutions, eg. HashMap , 2-d array, 
public class Solution {
    public String[] findRelativeRanks(int[] nums) {
        
        int l = nums.length;
        
        if( nums == null || l == 0){
            return new String[1];
        }
        
        int[] sort = Arrays.copyOf(nums, l); // NEVER use directly  int[] sort = nums, it will sort nums as well!!!
        Arrays.sort( sort );
        
        String[] r = new String[l];
        Find find = new Find();
        for( int i = 0; i < l; i++ ){
            r[i] = find.index( nums[i], sort );
        }
        
        return r;
    }
    
    
    // inner class: binary search
    class Find{
        String index( int a, int[] s ){
            int l = s.length;
            int h = 0;
            int t = l - 1;
            int m = ( h + t )/2;
            
            while( a != s[m] ){
                if( a < s[m] ){
                    t = m - 1;
                }else{
                    h = m + 1;
                }
                m = ( h + t )/2;
            }
            
            if( m == l - 1 ){
                return "Gold Medal";
            }else if( m == l - 2 ){
                return "Silver Medal";
            }else if ( m == l - 3 ){
                return "Bronze Medal";
            }
            
            return Integer.toString(l - m);
        }
    }
}