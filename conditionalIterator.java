public class MyIterator<T> implements selector<T>{
	
	private T next = null;
	private Iterator<T> myIter;

	public MyIterator(Iterator<T> iterator) {
		this.myIter = iterator;
	}

	public boolean hasNext() {
		while( myIter.hasNext() ){
			T cur = myIter.next();
			if( isOK(cur) ){
				next = cur;
				return true;
			}
		}
		return false;
	}

	public T next() throws Exception {
		if( next == null )
			throw new Exception();
		return next;
	}
}
