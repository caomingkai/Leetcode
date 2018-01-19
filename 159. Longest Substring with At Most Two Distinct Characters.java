class Solution {
    
    
    /*
          a b a b b a a e a a 
          |   | |       |
          i             j
          
          less = 0
          if( size < 2 )
            map.put(s.j, j);
            j++;
            max = Math.max( max, j-i)
          if( size == 2 )
            if( set.contains(j) )
                map.put(s.j, j)
                j++;
                max = Math.max( max, j-i)
            else
                i = Math.min(map.getAllValue()) + 1;
                remove correspoinding character
    
    */
    
    
/*
    public int lengthOfLongestSubstringTwoDistinct(String s) {
        
        if( s == null )
            return 0;
        if( s.length() <= 2 )
            return s.length();
        int res = 0;
        int i = 0, j = 0;
        int l = s.length();
        Map<Character, Integer> map = new HashMap<>();
        while( j < l && i < l ){
            char c = s.charAt(j);
            if( map.size() < 2 ){
                map.put( c, j );
                j++;
                res = Math.max( res, j-i );
            }else{
                if( map.containsKey(c) ){
                    map.put( c, j );
                    j++;
                    res = Math.max( res, j-i );
                }else{
                    char lessC = '0';
                    int lessI = Integer.MAX_VALUE;
                    for( char letter : map.keySet() ){
                        if( map.get(letter) < lessI ){
                            lessI = map.get(letter);
                            lessC = letter;
                        }
                    }
                    
                    i = 1 + lessI;
                    map.remove(lessC);
                }
            }
        }
        return res;
    }

*/

    public int lengthOfLongestSubstringTwoDistinct(String s) {
        if(s.length() < 1) return 0;
        HashMap<Character,Integer> index = new HashMap<Character,Integer>();
        int lo = 0;
        int hi = 0;
        int maxLength = 0;
        while(hi < s.length()) {
            if(index.size() <= 2) {
                char c = s.charAt(hi);
                index.put(c, hi);
                hi++;
            }
            if(index.size() > 2) {
                int leftMost = s.length();
                for(int i : index.values()) {
                    leftMost = Math.min(leftMost,i);
                }
                char c = s.charAt(leftMost);
                index.remove(c);
                lo = leftMost+1;
            }
            maxLength = Math.max(maxLength, hi-lo);
        }
        return maxLength;
    }
    
    
    
    // no need for hashMap
    // use two pointers : left1 & left2 , to record the last seen two characters
    // when encounter a third character, select the smaller one from left1 and left2

}