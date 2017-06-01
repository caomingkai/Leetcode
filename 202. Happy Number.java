
// version 1: circular problem -->  Floyd's cycle-finding Algorithm : slow(1) / fast(2) pointer(操场套圈)
public class Solution {
    public boolean isHappy(int n) {
        int slow = n, fast = n;
        do {
            slow = getNext(slow);
            fast = getNext(getNext(fast));
            if (fast == 1) return true;
        } while (slow != fast);
        
        return false;
    }
    
    private int getNext(int n) {
        int res = 0;
        while (n != 0) {
            res += (n%10)*(n%10);
            n /= 10;
        }
        return res;
    }
}

/*
// version 2: circular problem --> HashSet
public class Solution {
    public boolean isHappy(int n) {
        
        int q = n; // quotient
        int r = n; // remainder
        int s = 0; // sum
        Set<Integer> set = new HashSet<>();
        
        while( s != 1 ){
            s = 0;
            while( q > 0 ){
                r = q % 10;
                s += r*r;
                q = q / 10;
            }
            if( !set.add(s) ) return false;
            q = s;
        }
        return true;
    }
}
*/