// # of zero  ==  # of 5 factor
// http://www.purplemath.com/modules/factzero.htm
public class Solution {
    public int trailingZeroes(int n) {
    
        int cnt = 0;  // count number of 5
        int q = n;
    
        while( n/5 > 0 ){
            cnt += n / 5;
            n = n / 5;
        }
        return cnt;
    }
}
