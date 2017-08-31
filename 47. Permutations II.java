class Solution {
    
    List<List<Integer>> res = new ArrayList<>();
    
    public List<List<Integer>> permuteUnique(int[] nums) {
        
        int l = nums.length;
        if( l == 0 ) return null;
        
        HashMap<Integer, Integer> hash = new HashMap<>();
        for( int i = 0; i < l; i++ ){
            hash.put(nums[i], hash.getOrDefault(nums[i],0)+1 );
        }
        
        Set<Integer> set = hash.keySet();
        ArrayList<Integer> prefix = new ArrayList<Integer>();
        backtrack( hash, set, prefix, l );
        return res;
    }
    
    private void backtrack( HashMap<Integer, Integer> hash, Set<Integer>set, ArrayList<Integer> prefix ,int l ){
        //edge case:
        if( prefix.size() == l ){
            res.add(new ArrayList<>(prefix));
            return;
        }
        
        // recursive case:
        Iterator<Integer> iter = set.iterator();
        while( iter.hasNext() ){
            Integer digit = iter.next();
            if( hash.get(digit) > 0 ){
                prefix.add(digit);
                hash.put( digit, hash.get(digit)-1 );
                backtrack( hash, set, prefix, l );
                prefix.remove(prefix.size() - 1);
                hash.put( digit, hash.get(digit)+1 );
            }
        }
    }
}