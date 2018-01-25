// version 1 : O(n)

/*
 equals to find out the shortest distance from all "|" below:
    [ "A","B","C","A","D","B","F",A","E","F","G","B"]
       |   |       |       |      |               |
*/


public class Solution {
    public int shortestDistance(String[] words, String word1, String word2) {
        int index1 = -1, index2 = -1;
        int res = Integer.MAX_VALUE;
        for(int i = 0; i < words.length; i++) {
            if(words[i].equals(word1)) {
                index1 = i;
                if(index2 != -1 ) res = Math.min(res, Math.abs(index1 - index2));
            }
            
            if(words[i].equals(word2)) {
                index2 = i;
                if(index1 != -1 ) res = Math.min(res, Math.abs(index1 - index2));
            }
        }
        return res;
    }
}

/*

// version 2: BAD IDEA!!
class Solution {
    
    
    public int shortestDistance(String[] words, String word1, String word2) {
        
        int res = Integer.MAX_VALUE;
        
        Map<String, ArrayList<Integer>> map = new HashMap<>();
        
        int l = words.length;
        for(int i = 0; i < l; i++ ){
            String w = words[i];
            if( map.containsKey(w) ){
                ArrayList<Integer> list = map.get(w);
                list.add(i);
                map.put(w, list);
            }else{
                ArrayList<Integer> list = new ArrayList<Integer>();
                list.add(i);
                map.put(w, list);
            }
        }
        ArrayList<Integer> list1 = map.get(word1);
        ArrayList<Integer> list2 = map.get(word2); 
        ArrayList<Integer> listShorter = list1.size() > list2.size() ? list2 : list1;
        String word = list1.size() > list2.size() ? word1 : word2;

        for( Integer i: listShorter ){
            int j = 1;
            while( i+j<l || i-j>=0  ){
                if( i+j<l && words[i+j].equals(word) )
                    break;
                if( i-j>=0 && words[i-j].equals(word) )
                    break;
                j++;
            }
            res = res>j ? j: res;
        }
        
        return res;
    }
}
*/