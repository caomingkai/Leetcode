// version 1:  iteration
// keep dividing by 2 / 3 / 5, check the final num cannot be divided by any of them, if ==1, return true;
public class Solution {
    public boolean isUgly(int num) {
        if(num < 1) return false;
        while(num%2 ==0) num = num >> 1;
        while(num%3 == 0) num = num/3;
        while(num%5 == 0) num = num/5;
        return num == 1;
    }
}

/*
// version 2:  recursion
public class Solution {
    public boolean isUgly(int num) {
        if( num == 1 ) return true;
        
        int quotient = num;
        if( num % 2 == 0 ){
            quotient = num / 2;
        }else if ( num % 3 == 0 ){
            quotient = num / 3;
        }else if ( num % 5 == 0 ){
            quotient = num / 5;
            
        }else{
            return false;
        }
        
        if( quotient == 1 ) return true;
        if( quotient == 0 ) return false;
        
        return isUgly( quotient );
    }
}
*/