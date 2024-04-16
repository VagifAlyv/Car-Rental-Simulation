

public class Deques<T> implements DequeInterface<T>  {

	 	private T[] deque;
	    private int frontIndex;
	    private int backIndex;
	    private int numberOfEntries;
	    private static final int DEFAULT_CAPACITY = 10;


	    public Deques() {
	        this(DEFAULT_CAPACITY);
	    }

	    @SuppressWarnings("unchecked")
		public Deques(int initialCapacity) {
	    	deque = (T[]) new Object[initialCapacity];
	        frontIndex = 0;
	        backIndex = initialCapacity - 1;
	        numberOfEntries = 0;
	    }

	    @Override
	    public void addToFront(T newEntry) {
	        ensureCapacity();
	        frontIndex = (frontIndex - 1 + deque.length) % deque.length;
	        deque[frontIndex] = newEntry;
	        numberOfEntries++;
	    }

	    @Override
	    public void addToBack(T newEntry) {
	        ensureCapacity();
	        backIndex = (backIndex + 1) % deque.length;
	        deque[backIndex] = newEntry;
	        numberOfEntries++;
	    }

	    @Override
	    public T removeFront() {
	        if (isEmpty()) {
	            return null;
	        }
	        T front = deque[frontIndex];
	        deque[frontIndex] = null;
	        frontIndex = (frontIndex + 1) % deque.length;
	        numberOfEntries--;
	        return front;
	    }

	    @Override
	    public T removeBack() {
	        if (isEmpty()) {
	        	return null;
	        }
	        T back = deque[backIndex];
	        deque[backIndex] = null;
	        backIndex = (backIndex - 1 + deque.length) % deque.length;
	        numberOfEntries--;
	        return back;
	    }

	    @Override
	    public T getFront() {
	        if (isEmpty()) {
	        	return null;
	        }
	        return deque[frontIndex];
	    }

	    @Override
	    public T getBack() {
	        if (isEmpty()) {
	        	return null;
	        }
	        return deque[backIndex];
	    }

	    @Override
	    public boolean isEmpty() {
	        return frontIndex == (backIndex + 1) % deque.length;
	    }

	    @Override
	    public void clear() {
	        for (int i = 0; i < deque.length; i++) {
	        	deque[i] = null;
	        }
	        frontIndex = 0;
	        backIndex = deque.length - 1;
	        numberOfEntries = 0;
	    }

	    private void ensureCapacity() {
	        if (frontIndex == (backIndex + 2) % deque.length) {
	            @SuppressWarnings("unchecked")
				T[] newData = (T[]) new Object[deque.length * 2];
	            int j = 0;
	            for (int i = frontIndex; i != backIndex; i = (i + 1) % deque.length) {
	                newData[j] = deque[i];
	                j++;
	            }
	            newData[j] = deque[backIndex];
	            deque = newData;
	            frontIndex = 0;
	            backIndex = j;
	        }
	    }

	    public int size() {
	    	return numberOfEntries;
	    }
	
	
}

