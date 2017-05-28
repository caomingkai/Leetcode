// version 1: loop
public class Solution {
    public boolean isPowerOfThree(int n) {
        if( n == 0 ) return false;
        int r = 0;
        
        while( n > 1 ){
            r = n % 3;
            if( r != 0 ) return false;
            n = n / 3;
        }
        return n == 1 ?  true : false;
    }
}

/*
// version 2
// since 3 is prime number, we can use this approach
// the max num of power of 3 is Math.pow(3, (int)(Math.log(0x7fffffff) / Math.log(3)))
// if n can  divide max , we can say n is also power of 3
public class Solution {
    public boolean isPowerOfThree(int n) {
        int maxPowerOfThree = (int)Math.pow(3, (int)(Math.log(0x7fffffff) / Math.log(3)));
        return n>0 && maxPowerOfThree%n==0;
    }
}

*/