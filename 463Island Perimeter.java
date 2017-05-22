// deduct from whole -  learn from others

public class Solution {
    public int islandPerimeter(int[][] grid) {
        int island = 0, nei = 0;
        int len1 = grid.length, len2 = grid[0].length;
        for(int i = 0; i < len1; ++i) {
            for(int j = 0; j < len2; ++j) {
                if(grid[i][j] == 1) {
                    island++;
                    if(i < len1 - 1 && grid[i + 1][j] == 1) nei++;// have neighbor land 
                    if(j < len2 - 1 && grid[i][j + 1] == 1) nei++;// have neighbor land 
                }
            }
        }
        return island * 4 - nei * 2;
    }
}


/*
// check four direction - my version
public class Solution {
    public int islandPerimeter(int[][] grid) {
        if( grid == null || grid.length == 0 ) return 0;
        
        int rowN = grid.length;
        int colN = grid[0].length;
        
        int cnt = 0;
        for( int r = 0; r < rowN; r++){
            for( int c = 0; c < colN; c++){
                if( grid[r][c] == 1){
                    cnt += isShore(r-1, c, grid);
                    cnt += isShore(r+1, c, grid);
                    cnt += isShore(r, c-1, grid);
                    cnt += isShore(r, c+1, grid);
                }
            }
        }
        return cnt;
    }
    
    private int isShore( int r, int c, int[][]grid){
        int rowN = grid.length;
        int colN = grid[0].length;
        if( r == -1  ||  c == -1) return 1;
        else if( r == rowN || c == colN) return 1;
        else if( grid[r][c] == 0 ) return 1;
        else return 0;
    }
}

*/