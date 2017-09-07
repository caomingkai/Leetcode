// version 1: flood fill algorithm
// key point: whenever encounter a '1', 
// first, set it with '0',
// second, do the same thing for its surrounding cells
// until there is all adjacent '1' become '0'

class Solution {
    
    private int r;
    private int c;
    
    public int numIslands(char[][] grid) {
        r = grid.length;
        if( r == 0 ) return 0;
        c = grid[0].length;
        
        int cnt = 0;
        
        for(int i = 0; i < r; i++ ){
            for( int j = 0; j < c; j++ ){
                if( grid[i][j] == '1' ){
                    cnt++;
                    DFS_flood_with_zero( grid, i, j );
                }
            }
        }
        return cnt;
    }
    
    private void DFS_flood_with_zero( char[][] grid, int i, int j ){   
        if( i<0 || i>=r || j<0 || j>=c || grid[i][j]=='0' ){
            return;
        }else{
            grid[i][j] = '0';
            DFS_flood_with_zero( grid, i+1, j );
            DFS_flood_with_zero( grid, i-1, j );
            DFS_flood_with_zero( grid, i, j+1 );
            DFS_flood_with_zero( grid, i, j-1 );
        }
    }
}