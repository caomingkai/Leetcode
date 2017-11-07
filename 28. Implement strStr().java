class Solution {
    public int strStr(String haystack, String needle) {
        
        if( needle.length() == 0 )
            return 0;
        if( haystack.length() == 0 && needle.length() != 0)
            return -1;
        
        int lenLong = haystack.length();
        int lenShort = needle.length();
        
        if( lenShort > lenLong ) return -1;
        
        int end = lenLong - lenShort;
        for( int res = 0; res <= end ; res++ ){
            int i = 0;
            while(i < lenShort ){
                if( haystack.charAt(res+i) != needle.charAt(i) )
                    break;
                i++;
            }
            
            if( i == lenShort )
                return res;
        }
        return -1;
    }
}