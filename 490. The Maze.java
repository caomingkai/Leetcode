// 注意： visited 只记录驻足点,并非路径上的所有点

/*
// DFS
class Solution {
    
    private int[][] m;
    private boolean[][] visited;
    private int[] d;
    private int c, r;
    
    public boolean hasPath(int[][] maze, int[] start, int[] destination) {
        m = maze;
        d = destination;
        c = maze.length;
        r = maze[0].length;
        visited = new boolean[c][r];
        return helper(start[0], start[1], 0);
    }
    
    private boolean helper( int si, int sj, int dir ){
        
        if( visited[si][sj] ) return false;
        if( si==d[0] && sj==d[1] ) return true;
        visited[si][sj] = true;  
        
        int i=0, j=0;
        
        // go down (dir==1)
        if( dir != 3 ){  
            for(j=sj; j>-1&&m[si][j]!=1; j--);
            j++;
            if( j != sj && helper( si, j, 1)) return true;
        }
        
        // go right (dir==2)
        if( dir != 4 ){
            for(i=si; i<c&&m[i][sj]!=1; i++);
            i--;
            if( i != si && helper( i, sj, 2)) return true;
        }
        
        // go up (dir==3)
        if( dir != 1 ){ 
            for(j=sj; j<r&&m[si][j]!=1; j++);
            j--;
            if( j != sj && helper( si, j, 3)) return true;
        }
        
        // go left (dir==4)
        if( dir != 2 ){ 
            for(i=si; i>-1&&m[i][sj]!=1; i--);
            i++;
            if( i != si && helper( i, sj, 4))  return true;
        }
        
        return false;
    }
}
*/


// version 2: BFS
class Solution { 
    public boolean hasPath(int[][] maze, int[] start, int[] destination) {
        int r = maze.length;
        int c = maze[0].length;
        boolean[][] visited = new boolean[r][c];
        
        Queue<int[]> q = new LinkedList<>();
        q.offer(start);
        visited[start[0]][start[1]] = true;
        
        int[][] dir = {{0,1}, {1,0}, {0,-1}, {-1,0}}; // right, down, left, up
        while( !q.isEmpty() ){
            int lvlSize = q.size();
            
            for( int m = 0; m < lvlSize; m++ ){
                int[] s = q.poll();
                for( int k = 0; k < 4; k++ ){
                    int i = s[0];
                    int j = s[1];
                    do{
                        i += dir[k][0];
                        j += dir[k][1];
                    }while( -1<i && i<r && -1<j && j<c &&  maze[i][j] == 0 );
                    int si = i-dir[k][0];
                    int sj = j-dir[k][1];
                    if( visited[si][sj] ) continue;
                    if( destination[0] == si && destination[1] == sj ) return true;
                    visited[si][sj] = true;
                    q.offer(new int[]{si, sj});
                }
            }
        }
        return false;
    }
}


