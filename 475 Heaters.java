//
// my version - find worst case, namely find out a certain house, whose bear the longest distance among all houses
public class Solution {
    public int findRadius(int[] houses, int[] heaters) {
        
        Arrays.sort(heaters);
        
        int maxDist = 0;
        int dist = 0;
        int l = houses.length;
        
        for( int i = 0; i < l; i++){
            dist = shortestDist( houses[i], heaters );
            if( dist > maxDist){
                maxDist = dist;
            }
        }
        
        return maxDist;
    }
    
    
    // binary search
    private int shortestDist( int house, int[]heaters){
        int l = heaters.length;
        if( l == 1){
            return Math.abs( house - heaters[0] );
        }
        int h = 0;
        int t = l - 1;
        int m = (h+t)/2;
        
        while( house != heaters[m] ){
            
            if( house < heaters[m] ){
                t = m - 1;
            }else if( house > heaters[m] ){
                h = m + 1;  
            }
            
            // have to be in between
            if( h > t ){
                int dist1 = Integer.MAX_VALUE, dist2 = Integer.MAX_VALUE;
                if( m - 1 >= 0){
                    dist1 = Math.abs( heaters[m-1] - house );
                }
                if( m + 1 < l ){
                    dist2 = Math.abs( heaters[m+1] - house );
                }
                int dist = Math.abs( heaters[m] - house );
                
                dist = dist < dist1 ? dist : dist1;
                return  dist < dist2 ? dist : dist2;
            }
            
            m = (h+t)/2;
        }
        return 0; // there is a heater in the house position
    }
}




