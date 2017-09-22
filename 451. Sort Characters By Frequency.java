
class Solution {
    public String frequencySort(String s) {
        
        StringBuilder res = new StringBuilder();
        // edge case
        if( s == null || s.length() == 0 )
            return res.toString();
        
        // general case
        // get the frequence of each character
        Map<Character, Integer> map = new HashMap<>();  //  frequently used front half of 256
        int l = s.length();
        for( int i = 0; i < l; i++ ){
            char c = s.charAt(i);
            map.put(c, map.getOrDefault(c,0) + 1 ) ;
        }

        
        PriorityQueue<Map.Entry<Character, Integer>> pq = new PriorityQueue<>( new PQcomparator() );
        pq.addAll( map.entrySet() );
        
        while( pq.size() > 0 ){
            Map.Entry<Character, Integer> en = pq.poll();
            char c = en.getKey();
            int  f = en.getValue();
            for( int i = 0; i < f; i++ ){
                res.append(c);
            }
        }
        return res.toString();
    }
    
    
    private class PQcomparator implements Comparator<Map.Entry<Character, Integer>>{
        @Override
        public int compare( Map.Entry<Character, Integer> e1, Map.Entry<Character, Integer> e2 ){
            return e2.getValue() - e1.getValue();
        }
    }
}


