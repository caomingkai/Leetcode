/*
// version 1: not so good, many places to be improved.
public class Solution {
    public String addBinary(String a, String b) {
        int la = a.length();
        int lb = b.length();
        
        if( la == 0 ) return b;
        if( lb == 0 ) return a;
        StringBuilder sbr = new StringBuilder();
        
        char c = '0';                   // carray digit
        int i = la-1;
        int j = lb-1;
        for( ;i>=0&&j>=0; i--,j-- ){
            char[] rtn = new char[2]; 
            rtn = calcDigit( a.charAt(i) , b.charAt(j), c );
            sbr.append( rtn[0] );
            c = rtn[1];
        }
        
        while( i >= 0 ){
            char[] rtn = new char[2]; 
            rtn = calcDigit( a.charAt(i) , '0', c );
            sbr.append( rtn[0] );
            c = rtn[1];
            i--;
        }
        
        while( j >= 0 ){
            char[] rtn = new char[2]; 
            rtn = calcDigit( '0' , b.charAt(j), c );
            sbr.append( rtn[0] );
            c = rtn[1];
            j--;
        }
        
        if( c == '1'){
           sbr.append( c );
        }
        
        return sbr.reverse().toString();
    }
    
    private char[] calcDigit( char a, char b, char c){
        char[] r = new char[2];
        if( a == '0' && b == '0' && c == '0' ){
            r[0] = '0'; r[1] = '0';
        }else if( a == '0' && b == '0' && c == '1' ){
            r[0] = '1'; r[1] = '0';
        }else if( a == '0' && b == '1' && c == '0' ){
            r[0] = '1'; r[1] = '0';
        }else if( a == '0' && b == '1' && c == '1' ){
            r[0] = '0'; r[1] = '1';
        }else if( a == '1' && b == '0' && c == '0' ){
            r[0] = '1'; r[1] = '0';
        }else if( a == '1' && b == '0' && c == '1' ){
            r[0] = '0'; r[1] = '1';
        }else if( a == '1' && b == '1' && c == '0' ){
            r[0] = '0'; r[1] = '1';
        }else{
            r[0] = '1'; r[1] = '1';
        }
        return r;
    }
}
*/


// version 2: make use of the definition of 'binary system'
// ---  digit = sum % 2
// ---  carry = sum / 2
//Improvement:
// 1. consider two string with different length together, not seperately. No need for extra loops as above
// 2. how to calculate current digit?  make use of binary system; No need for switch case!
public class Solution {
    public String addBinary(String a, String b) {
        StringBuilder sb = new StringBuilder();
        int i = a.length() - 1, j = b.length() -1, carry = 0;
        while (i >= 0 || j >= 0) {
            int sum = carry;
            if (j >= 0) sum += b.charAt(j--) - '0';
            if (i >= 0) sum += a.charAt(i--) - '0';
            sb.append(sum % 2);
            carry = sum / 2;
        }
        if (carry != 0) sb.append(carry);
        return sb.reverse().toString();
    }
}