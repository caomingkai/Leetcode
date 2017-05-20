// 1. No need loop through the second half: num/2
// 2. No need loop elements after the sqrt(num)

public class Solution {
    public boolean checkPerfectNumber(int num) {
         
         if(num == 1){
             return false;
         }
         
         int sum = 1;
         
         int limit = (int) Math.sqrt( (double)num );
         
         for( int i = 2; i <= limit; i++){
             if( num % i == 0){
                 sum += i + num/i;
             }
         }
         
         return sum == num;
    }
}