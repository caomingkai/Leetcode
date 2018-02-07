class Solution {
    
    
    // version 1: 非常棒的解法---pattern
    /*
        1. 从string的头部开始，对遇到的每一个字母分配编号，编号从0开始分配
        2. 若后边遇到之前visited过的字母，分配相同编号
        3. 用map来存之前是否visited过字母的 <字母：编号> pair
    */
    public boolean isIsomorphic(String s, String t) {
        if( s == null || s.length() == 0 )
            return true;
    
        int sCode = encode(s);
        int tCode = encode(t);
        return sCode == tCode;
    }
    
    private int encode( String s ){
        Map<Character, Integer> map = new HashMap<>();
        char[] strArr = s.toCharArray();
        int res = 0;
        
        int code = 0;
        int len = s.length();
        for( int i = 0; i < len; i++ ){
            char c = strArr[i];
            if( map.containsKey(c) ){
                res = 10*res + map.get(c);
            }else{
                res = 10*res + code;
                map.put(c, code++);
            }
        }
        return res;
    }
    
    
    // KEY IDEA: 
    // 1 : two character in s don't point to same character in t
    // 2 : two character in t don't point to same character in s
    
    /*
    // version 1:  two pass : one in-order, one reverse-order
    public boolean isIsomorphic(String s, String t) {
        return helper(s,t) && helper(t,s);
    }
    
    private boolean helper( String s, String t ){
        if( s == null || s.length() == 0) return true;
        
        HashMap<Character, Character> map = new HashMap<>();
        int l = s.length();
        for( int i = 0; i < l; i++ ){
            char sc = s.charAt(i);
            char tc = t.charAt(i);
            
            if( map.containsKey(sc) ){
                char tc_visited = map.get(sc);
                if( tc_visited != tc )
                    return false;
            }else
                map.put( sc, tc );
        }
        
        return true;
    }
    */
    
    /*
    // version 2: hashMap + hashSet(used?)
    public boolean isIsomorphic(String s, String t) {
        if( s == null || s.length() == 0) return true;
        HashSet<Character> set = new HashSet<>();
        HashMap<Character, Character> map = new HashMap<>();
        int l = s.length();
        for( int i = 0; i < l; i++ ){
            char sc = s.charAt(i);
            char tc = t.charAt(i);
            if( map.containsKey(sc) ){
                char tc_visited = map.get(sc);
                if( tc_visited != tc )
                    return false;
            }else{
                if(set.contains(tc))
                    return false;
                set.add(tc);
                map.put( sc, tc );
            }
        }
        
        return true;
    }
    */
    
    /*
    // version 3: char array, default char is equavilent to 0
    public boolean isIsomorphic(String s, String t) {
	        char[] map = new char[256];
	        boolean[] used = new boolean[256];
	        char[] sc = s.toCharArray();
	        char[] tc = t.toCharArray();
	        for (int i = 0; i < sc.length; i++) {
	            if (map[sc[i]] == 0) {   // char array default value
	                if (used[tc[i]]) {   // 1.ensure: two character in s don't point to same character in t
	                	return false;	
	                }
	                map[sc[i]] = tc[i];  
	                used[tc[i]] = true;
	            } else {
	                if (map[sc[i]] != tc[i]) return false; // 2.ensure: two character in t don't point to same character in s
	            }
	        }
	        return true;
    }
    */
    
}