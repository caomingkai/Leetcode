class Solution {
    public int minCost(int[][] costs) {
        
        if( costs == null || costs.length == 0 )
            return 0;
        
        int l = costs.length;
        
        int[][] min = new int[l][3];
        min[0][0] = costs[0][0];  // 1st house painted with color 0
        min[0][1] = costs[0][1];  // 1st house painted with color 1
        min[0][2] = costs[0][2];  // 1st house painted with color 2
        
        for( int i = 1; i < l; i++ ){
            min[i][0] = costs[i][0] +  Math.min( min[i-1][1], min[i-1][2] );
            min[i][1] = costs[i][1] +  Math.min( min[i-1][0], min[i-1][2] );
            min[i][2] = costs[i][2] +  Math.min( min[i-1][0], min[i-1][1] );
        }
        return Math.min( Math.min( min[l-1][0], min[l-1][1] ), min[l-1][2] );
    }
}