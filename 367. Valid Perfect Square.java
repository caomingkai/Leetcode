/*
// version 1:  n( sqrt(n) )
// squre num = 1 + 3 + 5 + 7 + ...
// 1=1, 4=1+3, 8=1+3+5, 16=1+3+5+7, 25=1+3+5+7,+9
public class Solution {
    public boolean isPerfectSquare(int num) {
       int i = 1;
       while( num > 0 ){
           num -= i;
           i += 2;
       }
       return num == 0;
    }
}
*/

// version 2: binaray search
public class Solution {
    public boolean isPerfectSquare(int num) {
        if(num == 1 || num == 4 ) return true;
       int h = 1;
       int t = num/2;
       while( h <= t ){
           long m = ( h + t ) >>> 1;
           if( m * m == num ){
               return true;
           }else if( m * m < num ){
               h = (int)( m ) + 1 ;
           }else{
               t = (int)( m ) - 1 ;
           }
       }
       return false;
    }
}
