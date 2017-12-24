class Solution {
    public int divide(int dividend, int divisor) {
        
        // edge case: 
        if( divisor == 0 || dividend == Integer.MIN_VALUE && divisor == -1 )  return Integer.MAX_VALUE;
        
        // extract the sign
        int sign = 1;
        if( dividend < 0 && divisor > 0 || dividend > 0 && divisor < 0 )
            sign = -1;
        
        // convert to long type 
        // here comes the OVERFLOW when dividend = Integer.MIN_VALUE, since abs(Integer.MIN_VALUE) = Integer.MIN_VALUE + 1
        long dd = Math.abs( (long)dividend );
        long ds = Math.abs( (long)divisor );
        
        // general case:
        long res = longDiv( dd, ds );
        
        if (res > Integer.MAX_VALUE){ //Handle overflow.
            res = (sign == 1)? Integer.MAX_VALUE : Integer.MIN_VALUE;
        } else {
            res = sign * res;
        }
        
        return (int)res;
    }
    
    private long longDiv( long dd, long ds ){
        
        if( dd < ds ) return 0;
        
        long sum = ds;
        long multiple = 1;
        while( sum + sum <= dd ){
            sum = sum + sum;
            multiple = multiple + multiple;
        }
        return multiple + longDiv( dd - sum, ds );
    }
    
}