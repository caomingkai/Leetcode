/*

// Key Point: 1. HashMap + ArrayList : 
//            2. when remove(), no need move each item forward by 1,  swap deleted item and last item
//            
// NOTICE: don't use for-loop to increasement following element forward by one place
//         just swap the deleted element with last element
            
class RandomizedSet {
    
    List<Integer> list = new ArrayList<>();
    Map<Integer, Integer> loc = new HashMap<>();
    Random rand = new Random();
    
    public RandomizedSet() {
        
    }
    
   public boolean insert(int val) {
        if( loc.containsKey(val) )
            return false;
        
        loc.put( val, list.size() );
        list.add(val);
        return true;
    }
    
    
      public boolean remove(int val) {
        if( loc.containsKey(val)){
            int location = loc.get(val);
            loc.remove(val);
            //NOTICE: don't use for-loop to increasement following element forward by one place
            //        just swap the deleted element with last element
            if ( location < list.size() - 1 ) { // not the last one than swap the last one with this val
                int lastone = list.get(list.size() - 1 );
                list.set( location , lastone );
                loc.put(lastone, location);
            }
            loc.remove(val);
            list.remove(list.size() - 1);
            return true;
        }else
            return false;
    }
    
    
    
     public int getRandom() {
        int randLoc = rand.nextInt(list.size());
        return list.get(randLoc);
    }
}
*/


// version 2:  two  HashMap
class RandomizedSet {
		
		HashMap<Integer, Integer> k2i = new HashMap<>();
		HashMap<Integer, Integer> i2k = new HashMap<>();
		
		public RandomizedSet() {
	        
	    }

		public boolean insert(int val) {
	        if(k2i.containsKey(val)) return false;
	        int s = k2i.size();
	        k2i.put(val, s);
	        i2k.put(s, val);
	        return true;
	    }
	    
	    public boolean remove(int val) {
	    	if(!k2i.containsKey(val)) return false;
	    	int ti = k2i.remove(val);
	    	i2k.remove(ti);
	    	if(ti < i2k.size()) {
	    		int lastIndex = i2k.size();
	    		int tv = i2k.remove(lastIndex);
	    		i2k.put(ti, tv);
	    		k2i.put(tv, ti);
	    	}
	    	return true;
	    }
	    
	    public int getRandom() {
	        int d = (int)(Math.random() * i2k.size());
	        return i2k.get(d);
	    }
}