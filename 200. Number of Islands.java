// version 0: flood pour

class Solution {
    
    // 1. scan from top-left to bottom-right, 
    // 2. whenever we encouter a cell have '1', we begin flood, devastate all the '1' into '0'
    // 3. till there is no adjacent cells with '1', we back from recursion call. keep looping
    
    public int numIslands(char[][] grid) {
        if( grid == null || grid.length == 0 )
            return 0;
        int res = 0;
        int r = grid.length;
        int c = grid[0].length;
        
        for( int i = 0; i < r; i++ ){
            for( int j = 0; j < c; j++ ){
                if( grid[i][j] == '1' ){
                    floodWithZero( i, j, grid );
                    res++; 
                }
            }
        }
        return res;
    }
    
    private void floodWithZero( int i, int j, char[][] grid ){
        int r = grid.length;
        int c = grid[0].length;
        
        if( 0<=i&&i<r && 0<=j&&j<c && grid[i][j] == '1' ){
            grid[i][j] = '0';
            floodWithZero( i+1, j, grid );
            floodWithZero( i-1, j, grid );
            floodWithZero( i, j+1, grid );
            floodWithZero( i, j-1, grid );
        }
    }
}


/*
// Version 1:  union-find
class Solution {
    
    // Part 1:  create inner class UF 
    class UF{
        private int cnt;
        private int[] weight;
        private int[] parent;
        
        public UF( int N ){
            cnt = N;
            weight = new int[N];
            parent = new int[N];
            for( int i = 0; i < N; i++ )
                parent[i] = i;
            
        }
        
        public int find( int p ){
            while( p != parent[p] ){
                parent[p] = parent[ parent[p] ];
                p = parent[p];
            }
            return p;
        }
        
        public void union ( int p, int q ){
            int rootP = find( p );
            int rootQ = find( q );
            if( rootP == rootQ ) return;
            
            int weightP = weight[p];
            int weightQ = weight[q];
            if( weightP < weightQ ){
                parent[rootP] = rootQ;
                weight[rootQ] += weight[rootP];
            }else{
                parent[rootQ] = rootP;
                weight[rootP] += weight[rootQ];
            }
            cnt--;
        }
        
        public int count(){
            return cnt;
        }
    }
    
    
    // Part 2: given method
    private int r;
    private int c;
    
    public int numIslands(char[][] grid) {
        r = grid.length;
        if( r == 0 ) return 0;
        c = grid[0].length;
        
        int N = c * r ;
        UF unionFind = new UF( N );
        
        for( int i = 0; i < r; i++ ){
            for( int j = 0; j < c; j++ ){
                if( grid[i][j] == '1' )
                    putInUnion( i, j , grid, unionFind );
            }
        }
        
        int waterNum = 0;
        for( int i = 0; i < r; i++ ){
            for( int j = 0; j < c; j++ ){
                if( grid[i][j] == '0' ) waterNum++ ;                 
            }
        }
        return unionFind.count() - waterNum;
    }
    
    
    // Part 3: helper method
    private void putInUnion( int i, int j, char[][] grid, UF unionFind ){
        
        int[][] dirArr = { {0,1}, {0,-1}, {1,0}, {-1,0} };
        for( int k = 0; k < dirArr.length; k++ ){
            int[] dir = dirArr[k];
            int x = dir[0];
            int y = dir[1];
            int iNew = x + i;
            int jNew = y + j;
            
            if( iNew > -1 && iNew < r && jNew > -1 && jNew < c &&  grid[iNew][jNew] == '1' ){
                int index = i * c + j;
                int indexNew = iNew * c + jNew;
                unionFind.union( index, indexNew );
            }
        }
    }
    
}
*/



/*
// version 2: flood fill algorithm
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

*/