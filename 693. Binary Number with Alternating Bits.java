/*
// version 1: loop
//            将n向右逐次移动一位，提取该位值
//            将该位bit值与上一位bit值进行对比，只要不一样就return，直到 n右移至为0
class Solution {
    public boolean hasAlternatingBits(int n) {
        
        int bit = -1;  
        
        while( n > 0){
            if( (n & 1) == 1 ){  //  (n & 1) == 1  要加括号！！
                if( bit == 1 ) return false;
                bit = 1;
            }else{
                if( bit == 0 ) return false;
                bit = 0;
            }
            n = n >> 1;
        }
        return true;
        
    }
}
*/

/*
 version 2: bit manipulation 
   原理： 1. 利用了0和1的交替的特性，进行错位相加，从而组成全1的二进制数
         2. 然后再用一个检测全1的二进制数的trick，就是‘与’上加1后的数，因为全1的二进制数加1，就会进一位，并且除了最高位，其余位都是0，跟原数相‘与’就会得0，所以我们可以这样判断。比如n是10101，那么n>>1就是1010，二者相加就是11111，再加1就是100000，二者相‘与’就是0，参见代码如下：


*/

class Solution {
    public boolean hasAlternatingBits(int n) {
        int x = (n>>1) + n;               //    21:10101  
        return ( x & ( x+ 1 ) ) == 0;     //  + 10: 1010
                                          //-----------------
                                          //       11111   -> x
                                          //  &   100000   -> x+1
                                          //-----------------
                                          //      000000
    
    }
}
