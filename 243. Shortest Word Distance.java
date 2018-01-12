// version 1 : O(n)
class Solution {
    public int shortestDistance(String[] words, String word1, String word2) {
        int i1 = -1, i2 = -1;
        int minDistance = words.length;
        int currentDistance;
        for (int i = 0; i < words.length; i++) {
            if (words[i].equals(word1)) {
                i1 = i;
            } else if (words[i].equals(word2)) {
                i2 = i;
            }

            if (i1 != -1 && i2 != -1) {
                minDistance = Math.min(minDistance, Math.abs(i1 - i2));
            }
        }
        return minDistance;
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