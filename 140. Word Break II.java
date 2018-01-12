class Solution {
    
    /*
    version 1: TLE
    List<String> res = new ArrayList<>();
    
    public List<String> wordBreak(String s, List<String> wordDict) {
        int l = s.length();
        HashSet<String> set = new HashSet<>(wordDict);
        Queue<String> q = new LinkedList<>();
        String prefix = "";
        q.offer(prefix);
        
        while( !q.isEmpty() ){
            prefix = q.poll(); 
            String compactStr = prefix.replaceAll("\\s","");
            int preLen = compactStr.length();
            for( int i = preLen+1; i <= l; i++ ){
                if( set.contains(s.substring(preLen,i))){
                    String newCompact = compactStr + s.substring(preLen,i);
                    String newPrefix = "";
                    if( newCompact.equals(s) ){
                        newPrefix = prefix + s.substring(preLen,i);
                        res.add(newPrefix);
                    }else
                        newPrefix = prefix + s.substring(preLen,i) + " ";
                        q.offer(newPrefix);
                }
            }
        }
        return res;
    }
    */    
    
    /*
    // VERSION 2: use hashmap to store visited String
    HashMap<String,List<String>> map = new HashMap<String,List<String>>();
    public List<String> wordBreak(String s, List<String> wordDict) {
        List<String> res = new ArrayList<String>();
        if(s == null || s.length() == 0) {
            return res;
        }
        if(map.containsKey(s)) {
            return map.get(s);
        }
        if(wordDict.contains(s)) {
            res.add(s);
        }
        for(int i = 1 ; i < s.length() ; i++) {
            String t = s.substring(i);
            if(wordDict.contains(t)) {
                List<String> temp = wordBreak(s.substring(0 , i) , wordDict);
                if(temp.size() != 0) {
                    for(int j = 0 ; j < temp.size() ; j++) {
                        res.add(temp.get(j) + " " + t);
                    }
                }
            }
        }
        map.put(s , res);
        return res;
    }
    */
    
    private final Map<String, List<String>> cache = new HashMap<>();
    // a linear check: before jumping into recursion we could also check that the right reminder has a prefix from the dictionary, because if it hasn’t then there’s no sense in splitting the reminder into sub-strings
    private boolean containsSuffix(List<String> dict, String str) {
        for (int i = 0; i < str.length(); i++) {
            if (dict.contains(str.substring(i))) return true;
        }
        return false;
    }

    public List<String> wordBreak(String s, List<String> dict) {
        
        if (cache.containsKey(s)) return cache.get(s);
        List<String> result = new LinkedList<>();
        if (dict.contains(s)) result.add(s);
        for (int i = 1; i < s.length(); i++) {
            String left = s.substring(0,i), right = s.substring(i);
            if (dict.contains(left) && containsSuffix(dict, right)) {
                for (String ss : wordBreak(right, dict)) {
                    result.add(left + " " + ss);
                }
            }
        }
        cache.put(s, result);
        return result;
    }

}