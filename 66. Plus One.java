/*
// version 1: x + 1= ?
public class Solution {
    public int[] plusOne(int[] digits) {
        int c = 1;
        int l = digits.length;
        int[] r = new int[l+1];
        
        for( int i = l-1; i >= 0; i-- ){
            int sum = digits[i] + c;
            r[i+1] = sum % 10;
            c = sum / 10;
        }
        if( c == 0 ){
            return Arrays.copyOfRange( r, 1, l+1 );
        }else{
            r[0] = c;
        }
        return r;
    }
}
*/

// version 2: Actually no need to calculate all digits
// IF any digit < 9, add 1 to it, directly return new value is OK!

public class Solution {
    public int[] plusOne(int[] digits) {
            
        int n = digits.length;
        for(int i=n-1; i>=0; i--) {
            if(digits[i] < 9) {
                digits[i]++;
                return digits;
            }
            
            digits[i] = 0;
        }
        
        int[] newNumber = new int [n+1];
        newNumber[0] = 1;
        
        return newNumber;
    }
     