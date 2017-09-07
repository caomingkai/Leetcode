/*
// version 1: DFS
class Solution {
    
    private final int[][] DIR = {{0,1},{0,-1},{-1,0},{1,0},{-1,1},{1,1},{-1,-1},{1,-1} };
    public char[][] updateBoard(char[][] board, int[] click) {
        
        int r = board.length;
        int c = board[0].length;
        
        int[][] passed = new int[r][c];  
        
        
        dfs( click, board, passed );
        return board;
        
        
        
    }
    
    private boolean dfs( int[] click, char[][]board, int[][] passed){
        int r = board.length;
        int c = board[0].length;
        int x = click[0];
        int y = click[1];

        if( 0<=x && x<r && 0<=y && y<c && passed[x][y]==0 ) {
            passed[x][y] = 1;
            // case 1: if it is a mine: return true
            if( board[x][y] == 'M' ){
                board[x][y] = 'X';
                return true;
            // case 2: if it is not a mine
            }else{
                // check 8 adjacent cell, see if exist mine, count num and record it to board[x][y]
                int cnt = 0;
                for( int i = 0; i < DIR.length; i++){
                    int xx = x + DIR[i][0];
                    int yy = y + DIR[i][1];
                    if(0<=xx && xx<r && 0<=yy && yy<c && board[xx][yy] == 'M' ) {
                        cnt++;
                    }
                }
                

                if( cnt == 0 ){  // If it is a 'B', continue further DFS.
                    board[x][y] = 'B';
                    for(int i = 0; i < DIR.length; i++){
                        int xx = x + DIR[i][0];
                        int yy = y + DIR[i][1];
                        click[0] = xx;
                        click[1] = yy;
                        if(dfs( click, board, passed )) return true;
                    }
                }else            // If it is not a 'B', stop further DFS.
                    board[x][y] = (char)(count + '0');
            }
        }
        return false;
    }
}

*/

public class Solution {
    public char[][] updateBoard(char[][] board, int[] click) {
        int m = board.length, n = board[0].length;
        Queue<int[]> queue = new LinkedList<>();
        queue.add(click);
        
        while (!queue.isEmpty()) {
            int[] cell = queue.poll();
            int row = cell[0], col = cell[1];
            
            if (board[row][col] == 'M') { // Mine
                board[row][col] = 'X';
            }
            else { // Empty
                // Get number of mines first.
                int count = 0;
                for (int i = -1; i < 2; i++) {
                    for (int j = -1; j < 2; j++) {
                        if (i == 0 && j == 0) continue;
                        int r = row + i, c = col + j;
                        if (r < 0 || r >= m || c < 0 || c < 0 || c >= n) continue;
                        if (board[r][c] == 'M' || board[r][c] == 'X') count++;
                    }
                }
                
                if (count > 0) { // If it is not a 'B', stop further BFS.
                    board[row][col] = (char)(count + '0');
                }
                else { // Continue BFS to adjacent cells.
                    board[row][col] = 'B';
                    for (int i = -1; i < 2; i++) {
                        for (int j = -1; j < 2; j++) {
                            if (i == 0 && j == 0) continue;
                            int r = row + i, c = col + j;
                            if (r < 0 || r >= m || c < 0 || c < 0 || c >= n) continue;
                            if (board[r][c] == 'E') {
                                queue.add(new int[] {r, c});
                                board[r][c] = 'T'; // Temporary: Avoid to be added again.
                            }
                        }
                    }
                }
            }
        }
        
        return board;
    }
}