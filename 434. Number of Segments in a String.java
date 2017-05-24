// Version 1: 
public class Solution {
    public int countSegments(String s) {
        
        if( s == null || s.length() == 0 ) return 0;
        
        int l = s.length();
        boolean f = false;
        int i = 0;
        int cnt = 0;
        
        while( i < l ){
            if( s.charAt(i) != ' ' ){
                f = true;
            }else{
                if( f == true ) cnt++;
                f = false;
            }
            i++;
        }
        
        return f==true ? ++cnt : cnt;
    }
}

/*
// find the criteria : contigous non-space and space -> constitute a space
public class Solution {
    public int countSegments(String s) {
        if(s == null || s.length() == 0 || s.trim().length() == 0) return 0;
        s = s.trim();
        int countOfSpace = 0;
        char[] c= s.toCharArray();
        for(int i = 1; i < c.length - 1; i++){
            if(c[i] == ' ' && c[i - 1] != ' ') countOfSpace++;
        }
        return countOfSpace + 1;
    }
}
*/

/*
// Version 2: Scanner(String)
public class Solution {
    public int countSegments(String s) {
        
        if( s == null || s.length() == 0 ) return 0;
        Scanner in = new Scanner(s);
        int cnt = 0;
        while( in.hasNext() ){
            cnt++;
            in.next();
        }
        return cnt;
    }
}

*/