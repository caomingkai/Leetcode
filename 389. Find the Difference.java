// version 1: COOL !!!  No need for 'sorting', just need the different value.......................................
public class Solution {
    public char findTheDifference(String s, String t) {
        int code = t.charAt(s.length());
        
        for(int i =0; i < s.length(); i++){
            code -= (int)s.charAt(i);
            code += (int)t.charAt(i);
        }
        //System.out.println(code);
        return (char)code;
    }
}


/*
// version 2
// Bucket sort
public class Solution {
    public char findTheDifference(String s, String t) {
        if(s.length() == 0 ) return t.toCharArray()[0];
        
        char[] sarr = s.toCharArray();
        char[] tarr = t.toCharArray();
        int[] diff = new int[26];
        
        int ls = sarr.length;
        for( int i = 0; i < ls; i++){
            diff[ sarr[i] - 'a']++; 
        }
        
        char res = 'A';
        int lt = tarr.length;
        for(int i = 0; i < lt; i++){
            int cnt = --diff[ tarr[i]  - 'a'];
            if(cnt < 0) res = tarr[i];
        }
        
        return res;
    }
}

