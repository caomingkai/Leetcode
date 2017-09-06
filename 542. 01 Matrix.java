/*
// version 1: bfs with queue
class Solution {
    
    public int[][] updateMatrix(int[][] matrix) {
        
        int r = matrix.length;
        int c = matrix[0].length;
        if( r == 0 ) return null;
        
        int[][] res = new int[r][c];
        
        for( int i = 0; i < r; i++ ){
            for( int j = 0; j < c; j++ ){
                System.out.println( );
                res[i][j] = bfs( matrix, i, j, r, c);
            }
        }
        return res;
    }
    
    // could use : Queue<int[]> queue = new LinkedList<>();
    private int bfs( int [][] matrix, int i, int j, int r, int c ){
        Deque<ArrayList<Integer>> q = new LinkedList<>();
        ArrayList<Integer> cor = new ArrayList<>();
        int[][] passed = new int[r][c];
        cor.add(i);
        cor.add(j);
        
        q.offer(cor);
        
        while( q.size() > 0 ){
            ArrayList<Integer> cur = q.poll();
            int x = cur.get(0);
            int y = cur.get(1);
            passed[x][y] = 1;
            
            System.out.println( x + ":" + y + ":" + matrix[x][y] );
            if( matrix[x][y] == 0 ){
                return Math.abs(x-i) + Math.abs(y-j);
            }else{
                if( y+1 != c && passed[x][y+1] != 1 ){
                    ArrayList<Integer> corRight = new ArrayList<>();
                    corRight.add(x);
                    corRight.add(y+1);
                    q.offer(corRight);
                }
                
                if( y-1 != -1 && passed[x][y-1] != 1 ){
                    ArrayList<Integer> corLeft = new ArrayList<>();
                    corLeft.add(x);
                    corLeft.add(y-1);
                    q.offer(corLeft);
                }
                
                if( x-1 != -1 && passed[x-1][y] != 1 ){
                    ArrayList<Integer> corLower = new ArrayList<>();
                    corLower.add(x-1);
                    corLower.add(y);
                    q.offer(corLower);
                }
                
                if( x+1 != r && passed[x+1][y] != 1 ){
                    ArrayList<Integer> corUpper = new ArrayList<>();
                    corUpper.add(x+1);
                    corUpper.add(y);
                    q.offer(corUpper);
                }
                
            }
        }
        return Integer.MAX_VALUE;
    }
    
}
*/

// version 2: Decrease and Conquer( LIKE a hill with different level wells)
// Key point: let all '1' be MAX_VALUE
// first, let all cells next to '0' cell be '1'
// second, let all cells next to '1' cell be '2'
// third, let all cells next to '2' cell be '3'

// general case: let all cell whose value larger than value of current level be (current value + 1)
class Solution {
    
    public int[][] updateMatrix(int[][] matrix) {
        
        int r = matrix.length;
        int c = matrix[0].length;
        if( r == 0 ) return null;
        
        Deque<Integer> q = new LinkedList<>();
        for( int i = 0; i < r; i++ ){
            for( int j = 0; j < c; j++ ){
                if(matrix[i][j] == 0 ){
                    q.offer( i*c + j );
                }else{
                    matrix[i][j] = Integer.MAX_VALUE;
                }
            }
        }
        
        while( q.size() > 0 ){
            int corr = q.poll();
            int x = corr / c ;
            int y = corr % c ;
            int val = matrix[x][y];
            
            if( x+1!=r && matrix[x+1][y] > val+1 ) {
                matrix[x+1][y] = val+1;
                q.offer((x+1)*c+y);
            }
               
            if( x-1!=-1 && matrix[x-1][y] > val+1 ) {
                matrix[x-1][y] = val+1;
                q.offer((x-1)*c+y);
            }
               
            if( y+1!=c && matrix[x][y+1] > val+1 ) {
                matrix[x][y+1] = val+1;
                q.offer(x*c+y+1);
            }
               
            if( y-1!=-1 && matrix[x][y-1] > val+1  ) {
                matrix[x][y-1] = val+1;
                q.offer(x*c+y-1);
            }
        }
        return matrix;
    }
    
}
