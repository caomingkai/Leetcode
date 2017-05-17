public class Solution {
    public int[][] matrixReshape(int[][] nums, int r, int c) {
        
        int row = nums.length;
        int col = nums[0].length;
        
        if( row * col != r*c ){
            return nums;
        }
        
        int[][] result = new int[r][c];
        
        int row_i = 0, col_j = 0;
        
        for( int i = 0; i < row; i++){
            for( int j = 0; j < col; j++){
                
                result[row_i][col_j++] = nums[i][j] ;
                if( col_j == c ){ 
                    col_j = 0;
                    row_i++; 
                    
                }
            }
        }
        
        return result;
    }
}