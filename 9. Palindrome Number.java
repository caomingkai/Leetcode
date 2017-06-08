/*
public class Solution {
    public boolean isPalindrome(int x) {
        if( x < 0 ) return false;
     
        ArrayList<Integer> digit = new ArrayList<>();
        while( x > 0 ){
            digit.add(x%10);
            x /= 10;
        }
        
        int l = digit.size();
        for( int i = 0; i < l/2; i++){
            if( digit.get(i) != digit.get(l-1-i) ) return false;
        }
        return true;
    }
}
*/

// version 2: only need extra space for one variable
// create the reversed-digit version of original integer number, check if they are the same
public class Solution {
    public boolean isPalindrome(int x) {
        if( x < 0 ) return false;
        int o = x; // original num
        int r = 0; // reversed num
        // while( o > 0 ){  // ----- will overflow if o = 2121212129  -----
        //     r *= 10;
        //     r += o % 10;
        //     o /= 10;
        // }
        while( o >= 10 ){   // ----- will not overflow   -----
            r *= 10;
            r += o % 10;
            o /= 10;
        }
        
        return r == x / 10;
    }
}