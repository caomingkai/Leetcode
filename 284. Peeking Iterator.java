/*
// version 1: 使用 top variable来cache缓存iter.next()值
//            使用 null 作为标志来判断iter是否读完
class PeekingIterator implements Iterator<Integer> {
    Iterator<Integer> iter;
    Integer top;
    
	public PeekingIterator(Iterator<Integer> iterator) {
        iter = iterator;
	    if(iter.hasNext())
            top = iter.next();
        else
            top = null;
	}

	public Integer peek() {     
        return top;
	}

	@Override
	public Integer next() {
	    Integer ret = top;
        
        if( !iter.hasNext() )
            top = null;
        else
            top = iter.next();

        return ret;
	}

	@Override
	public boolean hasNext() {
        return top != null;
	}
}
*/

// version 2: 使用 top variable来cache缓存iter.next()值
//            使用 另外一个boolean var 作为标志来判断iter是否读完
class PeekingIterator implements Iterator<Integer> {
    
    Iterator<Integer> iter;
    Integer top;
    boolean done = false;
        
	public PeekingIterator(Iterator<Integer> iterator) {
        iter = iterator;
	    if(iter.hasNext())
            top = iter.next();
        else
            done = true;
	}

	public Integer peek() {     
        return top;
	}

	@Override
	public Integer next() {
	    Integer ret = top;
        
        if( !iter.hasNext() )
            done = true;
        else
            top = iter.next();

        return ret;
	}

	@Override
	public boolean hasNext() {
        return done != true;
	}
}