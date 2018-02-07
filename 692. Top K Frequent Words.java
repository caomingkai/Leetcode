/*
class Solution {
    public List<String> topKFrequent(String[] words, int k) {
        if( words == null || words.length == 0 )
            return new ArrayList<>();
        
        List<String> res = new ArrayList<>();
        TreeMap<String, Integer> map = new TreeMap<>();
        
        for( String w : words ){
            map.put( w, map.getOrDefault(w,0)+1 );
        }
        
        System.out.println(map);
        EntryComparator entryCmptor = new EntryComparator();
        PriorityQueue<Map.Entry<String, Integer>> pq = new PriorityQueue<>(k, entryCmptor );
        
        int i = 0;
    
        for( Map.Entry<String, Integer> w : map.entrySet() ){
            if( i < k ){
                pq.offer(w);
            }else{
                Map.Entry<String, Integer> firstEntry = pq.peek();
                if( entryCmptor.compare(w, firstEntry) >= 0){
                    pq.poll();
                    pq.offer(w);
                }
            }
            i++;
        }

        for( i = 0; i < k; i++ ){
            Map.Entry<String, Integer> en = pq.poll();
            res.add(0,en.getKey());
        }
        return res;
    }
    
    class EntryComparator implements Comparator<Map.Entry<String, Integer>>{
        @Override
        public int compare( Map.Entry<String, Integer> m1, Map.Entry<String, Integer> m2 ){
            if( m1.getValue() != m2.getValue() )
                return m1.getValue() - m2.getValue();
 
                return m2.getKey().compareTo( m1.getKey() );
        }
    }
}
*/
    
/*
    PriorityQueue 即便提前声明了 capacity， 那也只是 initial capacity！
    如果后边加入的元素数量大于capacity，capacity会持续增大。
    ———— 并无卵用。。。
*/
class Solution {
    public List<String> topKFrequent(String[] words, int k) {
        Map<String, Integer> count = new HashMap();
        for (String word: words) {
            count.put(word, count.getOrDefault(word, 0) + 1);
        }
        PriorityQueue<String> heap = new PriorityQueue<String>(
                (w1, w2) -> count.get(w1) != count.get(w2) ?
                count.get(w1) - count.get(w2) : w2.compareTo(w1) );

        for (String word: count.keySet()) {   // 不必先判断、再删值； 而是依靠pq自带的比较特性，先插值、再删除
            heap.offer(word);
            if (heap.size() > k) heap.poll();
        }

        List<String> ans = new ArrayList();
        while (!heap.isEmpty()) ans.add(heap.poll());
        Collections.reverse(ans);
        return ans;
    }
}