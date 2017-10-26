
/*
public class Solution {
    public double myPow(double x, int n) {
        long p = (long)n;
        if(p == 0)
            return 1;
        if(p<0){
            p = -p;
            x = 1/x;
        }
        return (p%2 == 0) ? myPow(x*x, (int)p/2) : x*myPow(x*x, (int)p/2);
    }
}
*/
public class Solution {
    public double myPow(double x, int n) {
        double ans = 1;
        long absN = Math.abs((long)n);
        while(absN > 0) {
            if((absN&1)==1) ans *= x;
            absN >>= 1;
            x *= x;
        }
        return n < 0 ?  1/ans : ans;
    }
}