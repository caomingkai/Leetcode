// Bucket Sort instead of HashMap( Though they have the same idea)
public class Solution {
    public List<Integer> findAnagrams(String s, String p) {
        
        List<Integer> res = new ArrayList<>();
        if (p == null || s == null || s.length() < p.length()) return res;
        int m = s.length(), n = p.length();
        for (int i = 0; i < m-n+1; i++) {
            String cur = s.substring(i, i+n);
            if (helper(cur, p)) res.add(i);
        }
        return res;
    }
    
    public boolean helper(String a, String b) {
        if (a == null || b == null || a.length() != b.length()) return false;
        int[] dict = new int[26];
        for (int i = 0; i < a.length(); i++) {
            char ch = a.charAt(i);
            dict[ch-'a']++;
        }
        for (int i = 0; i < b.length(); i++) {
            char ch = b.charAt(i);
            dict[ch-'a']--;
            if (dict[ch-'a'] < 0) return false;
        }
        return true;
    }
}


/*
// HashMap Version:  Time Limit Exceeded
public class Solution {
    public List<Integer> findAnagrams(String s, String p) {
        
        // edge case
        if( s.length() == 0 || s.length() < p.length() ) return new ArrayList<Integer>();
        
        // create HashMap with < char, freq > for p
        int lp = p.length();
        Map< Character, Integer> dictP = new HashMap<>();
        for( int i = 0; i < lp; i++){
            dictP.put( p.charAt(i), dictP.getOrDefault( p.charAt(i), 0 )+1 );
        }
        
        // loop through each pattern in s, create its own HashMap, checking if an anagram for p
        int ls = s.length();
        List<Integer> res = new ArrayList<>();
        for( int i = 0; i <= ls-lp; i++){
            Map< Character, Integer> dict = new HashMap<>(dictP);
            if(isAnagram(s, i, lp, dict)) res.add(i);
        }
        return res;
    }
    
    // helper
    private boolean isAnagram( String s, int head, int lp, Map< Character, Integer> dictP ){
        for( int i = head; i < head + lp; i++){
            char c = s.charAt(i);
            dictP.put( c , dictP.getOrDefault(c,0) - 1 );
            if( dictP.get(c) < 0 ) return false;
        }
        return true;
    }
}
*/