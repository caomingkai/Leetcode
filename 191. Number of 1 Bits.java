
// version 1: hamming weight calculation: n & (n - 1)
public class Solution {
    // you need to treat n as an unsigned value
    public int hammingWeight(int n) {
        int sum = 0;
        while( n != 0 ){
            n = n & (n - 1);
            sum++;
        }
        return sum;
    }
}

/*
// version 2: filter out the '1'
public class Solution {
    // you need to treat n as an unsigned value
    public int hammingWeight(int n) {
        int sum = 0;
        for( int i = 0; i < 32; i++ ){
             if( ( n & 1 )!= 0 )sum++;
             n = n>>>1;
        }
        return sum;
    }
}
*/