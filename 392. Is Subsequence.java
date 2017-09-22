/*
// version 1
// Greedy  & Two pointers

// since the sequence of existence matters, we need to go through "t" sequentially, in the process of check each char in "s"
// thus we need two pointers, one pointing to "s", one pointing to "t"
class Solution {
    public boolean isSubsequence(String s, String t) {
        
        int lenS = s.length();
        int lenT = t.length();
        
        // edge case:
        if( lenS == 0  && lenT == 0 ) return true;
        if( s == null || lenS == 0 ) return true;
        if( t == null || lenT == 0 ) return false;
        
        int indexS = 0;
        int indexT = 0;
        
        while( indexS < lenS ){
            if( indexT == lenT )
                    return false;
            char tempS = s.charAt(indexS);
            char tempT = t.charAt(indexT);
            
            if( tempS == tempT ){
                indexT++;
                indexS++;
                continue;
            }else{
                while( tempS != tempT ){
                    indexT++;
                    if( indexT == lenT )
                        return false;
                    tempT = t.charAt(indexT);
                }
            }
            indexT++;
            indexS++;
        }
        return true;
    }
}
*/

// version 2
public class Solution {
    public boolean isSubsequence(String s, String t) {
        if (s.length() == 0) return true;
        int indexS = 0, indexT = 0;
        while (indexT < t.length()) {
            if (t.charAt(indexT) == s.charAt(indexS)) {
                indexS++;
                if (indexS == s.length()) return true;
            }
            indexT++;
        }
        return false;
    }
}