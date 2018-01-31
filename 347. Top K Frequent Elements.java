
/*

// version 1: hashMap + heap
class Solution {
    public List<Integer> topKFrequent(int[] nums, int k) {
        
        // 1. count the frequence for unique number  m < n
        HashMap<Integer, Integer> map = new HashMap<>();
        for( int i = 0; i < nums.length; i++ ){
            map.put( nums[i], map.getOrDefault(nums[i],0) + 1 );
        }
        
        
        // 2. put them into into a size k min_heap( priorityQueue ),  output reverse order
//         PriorityQueue< Map.Entry<Integer,Integer> > pq = new PriorityQueue< Map.Entry<Integer,Integer> >( new Comparator< Map.Entry<Integer,Integer> > (){
//             @Override
//             public int compare( Map.Entry<Integer,Integer> m1, Map.Entry<Integer,Integer> m2 ){
//                 return m2.getValue() - m1.getValue();
//             }
//         } );
        
//         pq.addAll( map.entrySet() );
        
        PriorityQueue< Integer > pq = new PriorityQueue<>(
                                           (Integer n1, Integer n2)->map.get(n1) - map.get(n2));
        
        for( Map.Entry<Integer,Integer> en : map.entrySet() ){
            pq.offer( en.getKey() );
            if( pq.size() > k )
                pq.poll();
        }
        
        List<Integer> res = new ArrayList<>(k);
        for( int i = k-1; i >=0; i-- ){
            res.add(pq.poll());
        }
        
        return res;
    }
}
*/
// version 2: bucket sort
class Solution {
    public List<Integer> topKFrequent(int[] nums, int k) {

        List<Integer>[] bucket = new List[nums.length + 1];
        Map<Integer, Integer> frequencyMap = new HashMap<Integer, Integer>();

        for (int n : nums) {
            frequencyMap.put(n, frequencyMap.getOrDefault(n, 0) + 1);
        }

        for (int key : frequencyMap.keySet()) {
            int frequency = frequencyMap.get(key);
            if (bucket[frequency] == null) {
                bucket[frequency] = new ArrayList<>();
            }
            bucket[frequency].add(key);
        }

        List<Integer> res = new ArrayList<>();

        for (int pos = bucket.length - 1; pos >= 0 && res.size() < k; pos--) {
            if (bucket[pos] != null) {
                res.addAll(bucket[pos]);
            }
        }
        return res;
    }
}