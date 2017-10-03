class Solution {
    public List<Integer> topKFrequent(int[] nums, int k) {
        
        // 1. count the frequence for unique number  m < n
        HashMap<Integer, Integer> map = new HashMap<>();
        for( int i = 0; i < nums.length; i++ ){
            map.put( nums[i], map.getOrDefault(nums[i],0) + 1 );
        }
        
        
        // 2. put them into into a size k min_heap( priorityQueue ),  output reverse order
        PriorityQueue< Map.Entry<Integer,Integer> > pq = new PriorityQueue< Map.Entry<Integer,Integer> >( new Comparator< Map.Entry<Integer,Integer> > (){
            @Override
            public int compare( Map.Entry<Integer,Integer> m1, Map.Entry<Integer,Integer> m2 ){
                return m2.getValue() - m1.getValue();
            }
        } );
        
        pq.addAll( map.entrySet() );
        
        List<Integer> res = new ArrayList<>(k);
        for( int i = 0; i < k; i++ ){
            res.add(pq.poll().getKey());
        }
        
        return res;
    }
}