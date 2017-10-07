/*
// version 1:
class Solution {
    public boolean judgeSquareSum(int c) {
        
        for( long i = 0; i*i <= c; i++){
            double j = Math.sqrt(c- i* i);
            if( j == (int)j )
                return true;
        }
        return false;
    }
}
*/

// version 2: two pointers
class Solution {
    public boolean judgeSquareSum(int c) {
        
        double k = Math.sqrt( (double)c );
        int i = 0;
        int j = (int)k;
        
        while( i<=j ){
            int temp = i*i + j*j;
            if( temp == c )
                return true;
            else if( temp < c )
                i++;
            else
                j--;
        }
        return false;
    }
}