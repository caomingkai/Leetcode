 /*
    测试用例：
    string s1 = "0"; // True
    string s2 = " 0.1 "; // True
    string s3 = "abc"; // False
    string s4 = "1 a"; // False
    string s5 = "2e10"; // True

    string s6 = "-e10"; // False
    string s7 = " 2e-9 "; // True
    string s8 = "+e1"; // False
    string s9 = "1+e"; // False
    string s10 = " "; // False

    string s11 = "e9"; // False
    string s12 = "4e+"; // False
    string s13 = " -."; // False
    string s14 = "+.8"; // True
    string s15 = " 005047e+6"; // True

    string s16 = ".e1"; // False
    string s17 = "3.e"; // False
    string s18 = "3.e1"; // True
    string s19 = "+1.e+5"; // True
    string s20 = " -54.53061"; // True

    string s21 = ". 1"; // False
    
    */
    

class Solution {
    
    /* 
    We start with trimming white-space
    0. totally 5 cases for all letters :  0~9 | .  |  e  |  _+  |  invalid
        1. If we see [0-9] we reset the number flags to true.
        2. We can only see . if we didn’t see e or .
        3. We can only see e if we didn’t see e but we did see a number. We reset numberSeen flag.
        4. We can only see + and - in the beginning and after an e
        5. any other character break the validation.
    6. lastly, we want to make sure this string end with number, otherwise, return false;
      
         - if see  '0~9'  -- just set numSeen=true
         - if see  '.'    --  1. previous '.' existed => False 
                          --  2. previous 'e' existed => False 
                                        
         - if see  'e'    --  1. previous 'e' existed => False
                          --  2. no previous number existed => False
                          --  3. set numSeen=false, start checking again
                          
         - if see  '+-'   --  1. must be at start point OR right after 'e'
                
         - if see any others:  return false
    */
    
     public boolean isNumber(String s) {
         
         if( s == null || s.length() == 0 ) return false;
         s = s.trim();
         
         int l = s.length();
         boolean numSeen = false,
                 dotSeen = false,
                 eSeen = false;
         
         for( int i = 0; i < l; i++ ){
             char c = s.charAt(i);
             if( '0' <= c && c <= '9' ){
                 numSeen = true;
             }else if( c == '.' ){
                 if( dotSeen || eSeen )
                     return false;
                 dotSeen = true;
             }else if( c == 'e' ){
                 if( eSeen || !numSeen )
                     return false;
                 eSeen = true;
                 numSeen = false;
             }else if( c == '+' || c == '-' ){
                 if( i != 0 && s.charAt(i-1) != 'e' )
                     return false;
             }else {
                 return false;
             }
         }
         return numSeen;
     }
    
    
    
    /*
    // version 1:  totally 5 cases for all character in a string
    public boolean isNumber(String s) {
        s = s.trim();
        boolean pointSeen = false;
        boolean eSeen = false;
        boolean numberSeen = false;
        for(int i=0; i<s.length(); i++) {
            if('0' <= s.charAt(i) && s.charAt(i) <= '9') {
                numberSeen = true;
            } else if(s.charAt(i) == '.') {
                if(eSeen || pointSeen)
                    return false;
                pointSeen = true;
            } else if(s.charAt(i) == 'e') {
                if(eSeen || !numberSeen)
                    return false;
                numberSeen = false;
                eSeen = true;
            } else if(s.charAt(i) == '-' || s.charAt(i) == '+') {
                if(i != 0 && s.charAt(i-1) != 'e')
                    return false;
            } else
                return false;
        }
        return numberSeen;
    }
    */
    
    /*
    
    // version 2:  split by 'e', and handle each part separately
    
    public boolean isNumber(String s) {
        s = s.trim(); 
        if (s.length() > 0 && s.charAt(s.length() - 1) == 'e')
            return false; //avoid "3e" which is false
        String[] t = s.split("e");
        if (t.length == 0 || t.length > 2)
            return false;
        boolean res = valid(t[0], false);
        if (t.length > 1)
            res = res && valid(t[1], true);
        return res;
    }
    
    private boolean valid(String s, boolean hasDot) {
        if (s.length() > 0 && (s.charAt(0) == '+' || s.charAt(0) == '-')) //avoid "1+", "+", "+."
        s = s.substring(1);
        char[] arr = s.toCharArray();
        if (arr.length == 0 || s.equals("."))
            return false;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == '.') {
                if (hasDot)
                    return false;
                hasDot = true;
            } else if (!('0' <= arr[i] && arr[i] <= '9')) {
                return false;
            }
        }
        return true;
    }

    */
}