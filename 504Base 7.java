public class Solution {
    public String convertToBase7(int num) {
        int n = num;
        final int DEVIDER = 7;
        String r = "";
        
        if( n < 0){
            n = -n;
        }
        
        int remainder = n % DEVIDER;
        int quotient = n / DEVIDER;
        
        r = Integer.toString(remainder) + r;
        
        while( quotient >= DEVIDER ){
            remainder = quotient % DEVIDER;
            r = Integer.toString(remainder) + r;
            quotient = quotient / DEVIDER;
        }
        
        if(quotient > 0){
            r = Integer.toString(quotient) + r;
        }
        
        if(num < 0){
            r = "-" + r;
        }
        return r;
    }
}