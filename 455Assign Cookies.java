
public class Solution {
    public int findContentChildren(int[] g, int[] s) {
        
        int lg = g.length;
        int ls = s.length;
        
        if( ls == 0 || lg == 0 ) return 0;
        
        Arrays.sort(g);
        Arrays.sort(s);
        
        int i = 0 , j = 0, cnt = 0;
        while( i < lg ){
            if( g[i] > s[j] ){
                j++;
            }else{
                cnt++;
                i++;
                j++;
            }
            if( j >= ls ) break;
        }
        
        return cnt;
    }
}