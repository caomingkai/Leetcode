// digit root formula:
// n = 0, dr = 0
// 0 < n < 9, dr = 9
// n >= 9, dr = n % 9   num%9==0?9:(num%9), since n%9 = 0 ~ 8

// OR general formular :  [   1 + (num - 1) % 9   ]

public class Solution {

    public int addDigits(int num) {
        
        //  return 1 + (num - 1) % 9;
        return num==0?0:(num%9==0?9:(num%9));

    }

}

/*
public class Solution {
    public int addDigits(int num) {
        int sum = 0;
        int quotient = num;
        
        do{
            sum = 0;
            while( quotient > 0 ){
                int remainder = quotient % 10;
                sum += remainder;
                quotient /= 10;
            }
            quotient = sum;
        }while( sum > 9 );
        
        return sum;
    }
}

*/