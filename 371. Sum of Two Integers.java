// version 1: iteration - bit manipulation
public class Solution {
    public int getSum(int a, int b) {
        
        while( b != 0 ){
            int diff = a ^ b;   // a ^ b: filt out the diff bit
            b = ( a & b ) << 1; // a & b: filt out the same bit, then move leftward by 1( ie. times 2, because two same item)
            a = diff;
        }
        return a;
    }
}

/*
// version 2: recurion  - bit manipulation
public class Solution {
    public int getSum(int a, int b) {
        
       return b==0? a : getSum(a^b, (a&b)<<1); // terminating condition: b == 0
    }
}
*/