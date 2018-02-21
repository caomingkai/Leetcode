/*
class Solution {
    
    // {9, 9009, 906609, 99000099, 9966006699, 999000000999,  
    //                   99956644665999, 9999000000009999}
    public int largestPalindrome(int n) {
        // if input is 1 then max is 9 
        if(n == 1){
            return 9;
        }
        
        // if n = 3 then upperBound = 999 and lowerBound = 99
        // 这个必须要限制死，否则找出来的不是“指定位数”的回文
        int upperBound = (int) Math.pow(10, n) - 1, lowerBound = upperBound / 10;
        long maxNumber = (long) upperBound * (long) upperBound;
        
        // represents the first half of the maximum assumed palindrom.
        // e.g. if n = 3 then maxNumber = 999 x 999 = 998001 so firstHalf = 998
        int firstHalf = (int)(maxNumber / (long) Math.pow(10, n));
        
        boolean palindromFound = false;
        long palindrom = 0;
        
        while (!palindromFound) {
            // creates maximum assumed palindrom
            // e.g. if n = 3 first time the maximum assumed palindrom will be 998 899
            palindrom = createPalindrom(firstHalf);
            
            // here "i and palindrom/i" forms the two factor of assumed palindrom
            // 1. 检查 palindrom是否超最大值product
            // 2. 检查
            for (long i = upperBound; i > lowerBound; i--) {
                // if n= 3 none of the factor of palindrom 
                // can be more than 999 or less than square root of assumed palindrom 
                if ( i * i < palindrom) {
                    break;
                }
                
                // if two factors found, where both of them are n-digits,
                if (palindrom % i == 0) {
                    palindromFound = true;
                    break;
                }
            }

            firstHalf--;
        }
        return (int) (palindrom % 1337);
    }

    private long createPalindrom(long num) {
        String str = num + new StringBuilder().append(num).reverse().toString();
        return Long.parseLong(str);
    }
    
}
*/


// version 2: 更清楚
public class Solution {
    public int largestPalindrome(int n) {
        // 边界处理
        if(n==1){
            return 9; // 单位数是回文数
        }
        int maxnumber = (int)Math.pow(10,n)-1;

        for(int i=maxnumber;i>maxnumber/10;i--){ // 必须限制下限为maxnumber/10，这样才能保证指定的n位数
            long num = toLong(i);

            for(long j=maxnumber;j*j>=num;j--){
                if(num%j==0){                   // 只要mod为0就证明num是某两个n位数的乘积
                    return (int)(num%1337);
                }
            }
        }
        return 0;
    }
    public long toLong(int number){

        StringBuffer b = new StringBuffer();
        String str = b.append(number).reverse().toString();

        return Long.valueOf(number+str);
    }
}