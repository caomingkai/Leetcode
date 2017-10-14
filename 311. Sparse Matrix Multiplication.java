// version 1
class Solution {
    public int[][] multiply(int[][] A, int[][] B) {
        int m = A.length;
        int l = A[0].length;
        int n = B[0].length;
        int[][] res = new int[m][n];
        
        boolean[] flagA = new boolean[m];
        boolean[] flagB = new boolean[n];
        for( int k = 0; k < l; k++ ){
            // fill flagA
            for( int i = 0; i < m; i++ ){
                if( flagA[i] ){
                    continue;
                }else{
                    if(A[i][k] != 0){
                        flagA[i] = true;
                    }
                }        
            }
            
            // fill flagB
            for( int j = 0; j < n; j++ ){
                if( flagB[j] ){
                    continue;
                }else{
                    if(B[k][j] != 0){
                        flagB[j] = true;
                    }
                }        
            }
        }
        

        
        for( int i = 0; i < m; i++ ){
            for( int j = 0; j < n; j++ ){
                if( flagA[i] && flagB[j] ){
                    int sum = 0;
                    for( int k = 0; k < l; k++ ){
                        sum += A[i][k]*B[k][j];
                    }
                    res[i][j] = sum;
                }else{
                    continue;
                }
                
            }
        }
        return res;
    }
}

/*
// version 2:

class Solution {
    public int[][] multiply(int[][] A, int[][] B) {
        if(A==null||B==null||A.length==0||B.length==0)
            return null;
        int m = A.length;
        int p = A[0].length;
        int n = B[0].length;
        int[][] C =  new int[m][n];
        for(int i=0;i<m;i++){
            for(int k=0;k<p;k++){
                if(A[i][k]!=0){
                    for(int j=0;j<n;j++){
                        if(B[k][j]!=0)
                            C[i][j] += A[i][k]*B[k][j];
                    }
                }
            }
        }
        return C;
    }
}

*/