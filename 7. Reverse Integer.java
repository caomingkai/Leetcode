
// NO need for 'long' type.
// --- if overflow, the new result will not equal previous one.
public class Solution {
public int reverse(int x)
{
    int result = 0;

    while (x != 0)
    {
        int tail = x % 10;
        int newResult = result * 10 + tail;
        if ((newResult - tail) / 10 != result)  // check if overflows!!!
        { return 0; }
        result = newResult;
        x = x / 10;
    }

    return result;
}
}


/*
// NOTE:  in JAVA, negative number 'mod' and 'divide' is similar to positive number
public class Solution {
    public int reverse(int x) {
        // how to determine a num overflows?
        // 1. could use 'long', and then compare result with Integer.MAX (MIN)
        // 2. 
        long sum = 0;
        int q = x;
        int r = x; 
        
        while( q != 0 ){
            r = q % 10;
            sum *= 10;
            sum += r;
            q = q / 10;
        }
        if( sum > Integer.MAX_VALUE || sum < Integer.MIN_VALUE) return 0;
        return (int)sum;
    }
}

*/