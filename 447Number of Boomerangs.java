// HashMap Version:
// 1. calculate distance from one point to all others
// 2. store distance in hashmap
// 3. find out commone distance number(n), means existing n! pairs of boomerang
// 4. sum up all num for each point

public class Solution {
    public int numberOfBoomerangs(int[][] points) {
        // edge cases
        if( points == null || points.length < 3 || points[0].length < 2) return 0;
        
        int l = points.length;
        int cnt = 0;
        Map<Integer, Integer> disMap = new HashMap<>();
        
        for( int i = 0; i < l; i++){
            // put < distance , freq > in hashMap
            for( int j = 0; j < l; j++){
                if( i!=j ){
                    int d = dis(points[i], points[j]);
                    disMap.put( d, disMap.getOrDefault(d,0) + 1 ) ;
                }
            }
            
            // cal num of boomerang for current point
            for( int n : disMap.values() ){
                if( n > 1){
                    cnt += n*(n-1);
                }
            }
            
            disMap.clear();
        }
        return cnt;
        
    }
    // no need for Math.sqrt()
    private int dis( int[]p1, int[] p2 ){
        return (p1[0]-p2[0]) * (p1[0]-p2[0]) + (p1[1]-p2[1]) * (p1[1]-p2[1]);
    }
    
    
}


/*
// brutal force --- bummer "Time Limit Exceeded"
public class Solution {
    public int numberOfBoomerangs(int[][] points) {
        // edge cases
        if( points == null || points.length < 3 || points[0].length < 2) return 0;
        
        int l = points.length;
        int cnt = 0;
        for( int i = 0; i < l; i++){
            for( int j = 0; j < l; j++){
                for( int k = 0; k < l; k++){
                    if( i!=j && i!=k && j!=k && dis(points[i], points[j]) == dis(points[i], points[k]) ){
                        cnt++;
                    }
                }
            }
        }
        return cnt;
    }
    
    private double dis( int[]p1, int[] p2 ){
        return Math.sqrt( (p1[0]-p2[0]) * (p1[0]-p2[0]) + (p1[1]-p2[1]) * (p1[1]-p2[1]) );
    }
}
*/