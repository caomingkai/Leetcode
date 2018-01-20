/*

ADOBBBCODEBANC
ACBA

left / right pointer for sliding window
HashMap to assist pointers moving
n = t.length(); // n : how many characters in "t" left needs be cancelled out 

*/

/*
class Solution {
    public String minWindow(String s, String t) {
        
        if( s == null || s.length() == 0 )
            return "";
        if( t == null || t.length() == 0 )
            return s.substring(0,1);
        
        int left = 0, right = 0;                            // two pointers for sliding window
        HashMap<Character, Integer> map = new HashMap<>();  // assist window shifting
        int n = t.length();          // how many characters in "t" left needs to be cancelled out 
        int minLen = s.length() + 1; // length of shortest substring 
        int minLeft = 0;             // starting point for this shortest substring
        
        for( int i = 0; i < n; i++ )
            map.put( t.charAt(i), map.getOrDefault(t.charAt(i),0)+1);
        
        while( right < s.length() ){
            if( n > 0 ){ // current window doesn't cover all letters in t
                char c = s.charAt(right);
                if( map.containsKey(c) ){
                    int cnt = map.get(c);
                    if( cnt > 0 ) n--;    // >0: means this letter haven't been cancelled, and will be 
                    map.put( c, --cnt);   // update the cnt of cancellment for this t's letter
                }
                right++;
            }
            while( n == 0 ){  // we keep narrow down the window size to find shortest string
                if( minLen > right-left ){
                    minLen = right-left;
                    minLeft = left;
                }
                char c = s.charAt(left);
                if( map.containsKey(c) ){
                    int cnt = map.get(c);
                    if( cnt == 0 ) n++; // =0: means this letter will recover back
                    map.put( c, ++cnt); // update the cnt of cancellment for this t's letter
                }
                left++;
            }
        }
        
        if( minLen == s.length() + 1 ) return "";
        return s.substring(minLeft, minLeft+minLen);
    }
    
}

*/
    
// VERSION 2: 
//               1--- bucket instead of hashmap 
//               2--- consider all the characters appears in "s". Since cnt=0, won't affect value of n
class Solution {
    public String minWindow(String s, String t) {
        
        if( s == null || s.length() == 0 )
            return "";
        if( t == null || t.length() == 0 )
            return s.substring(0,1);
        
        int left = 0, right = 0;                            // two pointers for sliding window
        int[] map = new int[256];    // assist window shifting
        int n = t.length();          // how many characters in "t" left needs to be cancelled out 
        int minLen = s.length() + 1; // length of shortest substring 
        int minLeft = 0;             // starting point for this shortest substring
        
        for( int i = 0; i < n; i++ )
            map[t.charAt(i)]++;
        
        // for(int i = 0; i < 256; i++){System.out.println(map[i]);}
        
        while( right < s.length() ){
            if( n > 0 ){ // current window doesn't cover all letters in t
                char c = s.charAt(right);
                int cnt = map[c];
                if( cnt > 0 ) n--;    // >0: means this letter haven't been cancelled, and will be 
                map[c] =--cnt;   // update the cnt of cancellment for this t's letter
                right++;
            }
            
            while( n == 0 ){  // we keep narrow down the window size to find shortest string
                if( minLen > right-left ){
                    minLen = right-left;
                    minLeft = left;
                }
                char c = s.charAt(left);
                int cnt = map[c];
                if( cnt == 0 ) n++; // =0: means this letter will recover back
                map[c]= ++cnt; // update the cnt of cancellment for this t's letter
                left++;
            }
        }
        
        if( minLen == s.length() + 1 ) return "";
        return s.substring(minLeft, minLeft+minLen);
    }
    
}