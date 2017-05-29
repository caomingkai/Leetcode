// version 1: only one HashMap
// Map.put()  return the value.
// count the occurence number of corresponding character and string, if they are same, OK; otherwise, return false.

public class Solution {
    public boolean wordPattern(String pattern, String str) {
        String[] words = str.split(" ");
        if (words.length != pattern.length())
            return false;
        Map<String, Integer> index = new HashMap<String, Integer>();
        for (Integer i=0; i<words.length; ++i)
        // 'Integer' allows to compare with '!=' because there's no autoboxing-same-value-to-different-objects-problem anymore. 
            if (index.put(pattern.charAt(i)+" ", i) != index.put(words[i], i))
                return false;
        return true;
    }
}

/*
// version 2:  two HashMap
public class Solution {
    public boolean wordPattern(String pattern, String str) {
        
        String[] arr = str.split(" ");
        int ls = arr.length;
        int lp = pattern.length();
        if( ls != lp ) return false;
        
        Map<String, Character> dict1  = new HashMap<>();
        for( int i = 0; i < ls; i++){
            char c = pattern.charAt(i);
            dict1.put( arr[i] , c );
        }
        
        Map< Character, String> dict2  = new HashMap<>();
        for( int i = 0; i < lp; i++){
            char c = pattern.charAt(i);
            dict2.put( c , arr[i] );
        }
        
        if(dict1.size() != dict2.size() ) return false;
        
        for( char key : dict2.keySet() ){
            String value2 = dict2.get( key );
            if( dict1.get(value2) != key) return false;
        }
        return true;
    }
}
*/

