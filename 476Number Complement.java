// Version 1: bit manipulation
public class Solution {
    public int findComplement(int num) {
       int d = 31;
       int mask = 1<<d;
       while ((num&mask)==0) { // find out the Most Significant Bit (MSB)
           mask = 1<<d--;
       }
       // mask: 1000  --- mask-1: 111
       mask = mask-1;
       return ~num & mask;
    }
}

/*
// Version 2: Clever Math way: Sum and Complement( 1001 + 0110 = 1111 ),so we only need to cal the sum, then minus num
 public class Solution {
    public int findComplement(int num) {
        int sum = 0;
        int i = 0;
        while(sum < num){
            sum += Math.pow(2,i);
            i++;
        }
        return sum - num;
    }
}

*/

/*
// Version 3: Intuitive way
public class Solution {
    public int findComplement(int num) {
        if(num == 1){
            return 0;
        }
        
        int sum = 0, power = 0, quotient = num;
        while( quotient >= 2 ){
            int remainder = quotient % 2;
            if( remainder == 0){
                sum +=  (int)Math.pow(2 , power);
            }
            
            quotient = quotient / 2;
            power++;
        }
        
        return sum;
    }
}


*/