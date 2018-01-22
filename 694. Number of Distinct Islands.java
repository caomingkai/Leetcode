/**

    1. find all the islands: using dfs
    2. encode island pattern: 
        (1) to int   
        (2) to string 
    3. HashSet to get rid of duplicates shape pattern


NOTICE 1 : (i-i0)*C + j-j0 :  serializing matrix doesn't work!!, must in the form of 2-d arr
    [1,1,1,1],   0 1 2 3 4 6
    [1,0,1,0],
    [0,0,0,0],
    [0,1,1,1],   13 14 15 16 17 19 = 0 1 2 3 4 6
    [1,1,0,1]

NOTICE 2 :  (i-i0)*2*C + j-j0 can work !!!!!! 可以是比1大的任何数，但不能为1
            因为如果用(i-i0)*C + j-j0，相当与按顺序取数字，
            而 (i-i0)*2*C + j-j0，相应的数字组合如下：
            造成的直接结果是： 列与列直接不可以直接相连了！！！这样上述情况就是不同的组合了
            
            0  1   2  3     0 1 2 3 8 10
            8  9  10 11 
            16 17 18 19     17 18 19 24 25 27 = 0 1 2 7 8 10
            24 25 26 27
            

**/


class Solution {

  
    // version 1: use int number as the pattern to differentiate each other
    // Key Point: 
    public int numDistinctIslands(int[][] grid) {
        
        if( grid == null || grid.length == 0 )
            return 0;
        int r = grid.length;
        int c = grid[0].length;
        
        Set<Set<Integer>> patterns = new HashSet<>();
        boolean[][] visited = new boolean[r][c];
        
        for( int i = 0; i < r; i++ ){
            for( int j = 0; j < c; j++ ){
                if( grid[i][j] == 1 && !visited[i][j]  ){
                    Set<Integer> ptn = new HashSet<>();
                    findIslands( i, j, i, j, grid, visited, ptn ); // first i,j is used for normalization
                    patterns.add(ptn);
                }
            }
        }
        System.out.println(patterns);
        return patterns.size();
    }
    
    private void findIslands( int i0, int j0, int i, int j, int[][] grid, boolean[][] visited, Set<Integer> ptn ){
        int R = grid.length;
        int C = grid[0].length;
        if( 0<=i && i<R && 0<=j && j<C && !visited[i][j] && grid[i][j]==1 ){
            ptn.add( (i-i0)*2*C + j-j0 );
            visited[i][j] = true;
            findIslands( i0, j0, i-1, j, grid, visited, ptn );
            findIslands( i0, j0, i+1, j, grid, visited, ptn );
            findIslands( i0, j0, i, j-1, grid, visited, ptn );
            findIslands( i0, j0, i, j+1, grid, visited, ptn );
        }
    }
    

    
    
/*  
    // version 2: use String as pattern token
    // also no need to use visited[][], because we just set the currenly visiting cell val to '0'
    // next time, we won't visit it, since it's '0' now
    public int numDistinctIslands(int[][] grid) {
        Set<String> set = new HashSet<>();
        for(int i = 0; i < grid.length; i++) {
            for(int j = 0; j < grid[i].length; j++) {
                if(grid[i][j] != 0) {
                    StringBuilder sb = new StringBuilder();
                    dfs(grid, i, j, sb, "o"); // origin
                    grid[i][j] = 0;
                    set.add(sb.toString());
                }
            }
        }
        System.out.println(set);
        return set.size();
    }
    private void dfs(int[][] grid, int i, int j, StringBuilder sb, String dir) {
        if(i < 0 || i == grid.length || j < 0 || j == grid[i].length 
           || grid[i][j] == 0) return;
        sb.append(dir);
        grid[i][j] = 0;
        dfs(grid, i-1, j, sb, "u");
        dfs(grid, i+1, j, sb, "d");
        dfs(grid, i, j-1, sb, "l");
        dfs(grid, i, j+1, sb, "r");
        sb.append("b"); // back
    }
    
    */
}


