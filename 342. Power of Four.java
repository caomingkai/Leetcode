// bit manipulation: n&(n-1) - count # of '1' 
public class Solution {
    public boolean isPowerOfFour(int num) {
        // 1. !(n&(n-1)) 
        // n&(n-1) == 0, we can say num is power of 2 (or 4 or 8 ...), and there would only '1' in the binary representation
        // in order to filter power of 4, we know the 1 should only exist on even number position: 0, 2, 4, 6, 8 ...
        
        // 2. n&0x55555555
        // 0x5555== 0101 0101 0101 0101, this way we can get even number position, to see if the only '1' on the even position
        if( ( num&(num-1) ) == 0 ){
            if( (num&0x55555555) != 0 ){
                return true;
            }
        }
        return false;
    }
}