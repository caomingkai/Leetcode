
// Version 1: binaray search + bit shift
public class Solution {
    public int arrangeCoins(int n) {
        int start = 0;
        int end = n;
        int mid = 0;
        while (start <= end){  // using two pointer comparasion as ending point 
            mid = (start + end) >>> 1; // unsigned right bit shift, ie.  x/2
            if ((0.5 * mid * mid + 0.5 * mid ) <= n){
                start = mid + 1;
            }else{
                end = mid - 1;
            }
        }
        return start - 1;
    }
}

/*
// version 2: Math solution
// denote N as the result
// (1+N)*N/2 ~= n
// Math.ceiling(N)
public class Solution {
    public int arrangeCoins(int n) {
        
        if( n == 0 ) return 0;
        
        return (int) Math.floor( Math.sqrt(2.0*n + 0.25) -0.5 );
    }
}
*/