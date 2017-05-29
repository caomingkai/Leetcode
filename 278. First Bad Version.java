/* The isBadVersion API is defined in the parent class VersionControl.
      boolean isBadVersion(int version); */

// binaray search
public class Solution extends VersionControl {
    public int firstBadVersion(int n) {
        
        int h = 1;
        int t = n;
        
        while( h < t ){
            int m = h + (t - h) / 2;
            // int m = h/2 + t/ 2;
            if( isBadVersion(m) ) 
                t = m;
            else
                h = m + 1;
        }
        
        return h;
    }
}