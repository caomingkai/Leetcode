
// version 1: shorter version
class WordDistance {
    Map<String, List<Integer>> map = new HashMap<>();
    public WordDistance(String[] words) {
        for(int i = 0; i < words.length; i++) {
            if(!map.containsKey(words[i])) map.put(words[i], new ArrayList<>());
            map.get(words[i]).add(i);
        }       
    }
    
    public int shortest(String word1, String word2) {
        List<Integer> list1 = map.get(word1);
        List<Integer> list2 = map.get(word2);
        int i = 0;
        int j = 0;
        int p1 = -1;
        int p2 = -1;
        int min = Integer.MAX_VALUE;
        while (i < list1.size() && j < list2.size()) {
            p1 = list1.get(i);
            p2 = list2.get(j);
            if (p1 > p2) {
                min = Math.min(p1 - p2, min);
                j++;
            } else {
                min = Math.min(p2 - p1, min);
                i++;
            }
        }
        return min;
    }
}

/*
// version 2 : mine
class WordDistance {

    Map<String, ArrayList<Integer>> map;
    public WordDistance(String[] words) {
        map = new HashMap<>();
        
        int l = words.length;
        for( int i = 0; i < l; i++ ){
            String w = words[i];
            if( map.containsKey(w) ){
                ArrayList<Integer> list = map.get(w);
                list.add(i);
            }else{
                ArrayList<Integer> list = new ArrayList<>();
                list.add(i);
                map.put( w, list );
            }
        }
    }
    
    
    
    //    [ A1 A4 B5 A9 B13 B14 A19 ]
    
    public int shortest(String word1, String word2) {
        ArrayList<Integer> list1 = map.get(word1);
        ArrayList<Integer> list2 = map.get(word2);
        
        int res = Integer.MAX_VALUE;
        int i = 0, j = 0;
        while(i < list1.size() && j < list2.size() ){
            int idx1 = list1.get(i), idx2 = list2.get(j); 
            if( idx1 < idx2 ){
                res = res < idx2-idx1 ? res : idx2-idx1;
                i++;
            }else{
                res = res < idx1-idx2 ? res : idx1-idx2;
                j++;
            }    
        }
        return res;
    }
}
*/
    
/**
 * Your WordDistance object will be instantiated and called as such:
 * WordDistance obj = new WordDistance(words);
 * int param_1 = obj.shortest(word1,word2);
 */