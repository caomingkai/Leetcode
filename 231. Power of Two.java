//version 1:
public class Solution {
    public boolean isPowerOfTwo(int n) {
        
        for( int i = 0; i < 31; i++){
            int powerOfTwo = 1<<i;
            if( n == powerOfTwo ) return true;
        }
        
        return false;
    }
}

/*
// version 2:
// 8 = 1000 ; 7 = 0111  8 & 7 = 0
public class Solution {
    public boolean isPowerOfTwo(int n) {
        
        return (!(n <= 0) && ((n & n-1) == 0));
    }
}
*/