// version 1: directly convert into Hex with bit manipulation.
public class Solution {
    public String toHex(int num) {
        if(num == 0) return "0";
        char[] map = {'0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f'};
        StringBuilder sb = new StringBuilder();
        while(num != 0){             // this could ensure there would not exist leading '0'
            sb.insert(0, map[num & 15]); // num & 15 ---> get the last 4 digit sum( surely < 16 )
            num = num >>> 4;        // >>> : unsigned right shift operator ">>>" shifts a zero into the leftmost position
        }
        return sb.toString();
    }
}


/*
// version 2:  convert from decimal to binary, and then convert from binary to hex
public class Solution {
    public String toHex(int num) {
        
        // convert num to its binary expression, No matter positive(regular binary) or negative( complement binary )
        char[] binary = new char[32];
        binary = toBinary( num );
        
        return toHex( binary );
    }
    
    // convert decimal to binary (leading '0' is not truncated yet)
    private char[] toBinary( int num ){
        char[] r = new char[32];
        for( int i = 0; i < 32; i++){
            r[i] = (num&(1<<i)) == 0 ? '0' : '1' ;
        }
        return r;
    }
    
    // convert binary to hex
    private String toHex( char[] bi ){
        char[] h = {'0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f'};
        StringBuilder r = new StringBuilder();
        int sum = 0;
        int j = 3;
        for( int i = 31; i >= 0; i--){
            int cur = bi[i] - '0';
            sum += cur * Math.pow(2,j--);
            if( i%4 == 0 ){
                r.append( h[sum] );
                sum = 0;
                j = 3;
            }
        }

        // truncate leading zero
        int i = 0;
        int l = r.length();
        while( i < l && r.charAt(i) == '0' ){
            i++;
        }
        if( i == l ) return "0";
        return r.substring(i, r.length() );
    }
}

*/
