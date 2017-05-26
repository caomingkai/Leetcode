// How to reduce the overflowing possiblity?
// 1 -- mid = (head + tail) / 2;   
// 2 -- mid = i + (j - i) / 2;
// The 1st one would exceed Integer.MAX_VALUE, if target < mid, we let head = mid, the new (head+tail) ~= 1.5*tail. '/2' would never be performed
// The 2nd one never exceed Integer.MAX_VALUE.

/* The guess API is defined in the parent class GuessGame.
   @param num, your guess
   @return -1 if my number is lower, 1 if my number is higher, otherwise return 0
      int guess(int num); */

public class Solution extends GuessGame {
    public int guessNumber(int n) {

        int head = 1;
        int tail = n;
        // no need to check edge cases: there must be an element between 1~n
        while( tail > head ){
            int mid = head + ( tail- head ) / 2;
            int res = guess(mid);
            if( res == 0) {
                return mid;
            }else if( res < 0 ){
                tail = mid - 1;
            }else{
                head = mid + 1;
            }
        }
        return tail;
    }
   
    // public int guessNumber(int n) {
    // int i = 1, j = n;
    // while(i < j) {
    //     int mid = i + (j - i) / 2;
    //     if(guess(mid) == 0) {
    //         return mid;
    //     } else if(guess(mid) == 1) {
    //         i = mid + 1;
    //     } else {
    //         j = mid;
    //     }
    // }
    // return i;
    // }
}


