import java.util.Arrays;

public class Lists<T> implements ListInterface<T> {

	private T[] list;
	private int numberOfEntries;
    private static final int DEFAULT_CAPACITY = 50;
    private static final int MAX_CAPACITY = 10000;
    
    public Lists() {
    	this(DEFAULT_CAPACITY);
    }
    
    
	public Lists(int initialCapacity) {
		  @SuppressWarnings("unchecked")
		T[] tempList = (T[]) new Object[initialCapacity + 1];
	        list = tempList;
	        numberOfEntries = 0;
	}
	
	
	
	
	@Override
	public void add(T newEntry) {
		list[numberOfEntries + 1] = newEntry;
		numberOfEntries++;
		ensureCapacity();
		
		
	}

	
	
	private void ensureCapacity() {
		int capacity  = list.length - 1;
		if(numberOfEntries >= capacity) {
			int newCapacity = 2 * capacity;
			list = Arrays.copyOf(list, newCapacity + 1);
		}
	}


	@Override
	public void add(int newPosition, T newEntry) {
		if((newPosition >= 1) && (newPosition <= numberOfEntries + 1)){
			makeRoom(newPosition);
		list[newPosition] = newEntry;
		numberOfEntries++;
		ensureCapacity();
		}
		else {
			throw new IndexOutOfBoundsException(
			"Given position of add's new entry is out of bounds");
		}
	}

	
	
	private void makeRoom(int newPosition) {
		assert(newPosition >= 1) && (newPosition <= numberOfEntries + 1);
		int newIndex = newPosition;
		int lastIndex = numberOfEntries;
		
		for(int index = lastIndex; index >= newIndex; index--) {
			list[index + 1] = list[index];
		}	
	}


	@Override
	public T remove(int givenPosition) {
		if ((givenPosition >= 1) && (givenPosition) <= numberOfEntries) {
			assert !isEmpty();
			T result = list[givenPosition];
			
			if(givenPosition < numberOfEntries) {
				removeGap(givenPosition);
			}
			numberOfEntries--;
			return result;
		}
		else {
			throw new IndexOutOfBoundsException(
					"Illegal position given to remove operation");
		}
	}

	private void removeGap(int givenPosition) {
		assert(givenPosition >= 1) && (givenPosition < numberOfEntries);
		int removedIndex = givenPosition;
		int lastIndex = numberOfEntries;
		
		for(int index = removedIndex; index < lastIndex; index++) {
			list[index] = list[index + 1];
		}
		
	}


	@Override
	public void clear() {
		while(!isEmpty()) {
			numberOfEntries--;
		}
		
	}

	@Override
	public T replace(int givenPosition, T newEntry) {
		if ((givenPosition >= 1) && (givenPosition) <= numberOfEntries) {
			assert !isEmpty();
			T originalEntry = list[givenPosition];
			list[givenPosition] = newEntry;
			return originalEntry;
		}
		else {
			throw new IndexOutOfBoundsException("Illegal position given to replace opeartion.");
	}
		}

	@Override
	public T getEntry(int givenPosition) {
		if ((givenPosition >= 1) && (givenPosition) <= numberOfEntries) {
			assert !isEmpty();
			return list[givenPosition];
		}
		else {
			throw new IndexOutOfBoundsException("Illegal position given to getEntry operation.");
		}
	}

	@Override
	public T[] toArray() {
		@SuppressWarnings("unchecked")
		T[] result = (T[])new Object[numberOfEntries];
		for(int index = 0; index< numberOfEntries; index++) {
			result[index] = list[index + 1];
		}
		return result;
	}

	@Override
	public boolean contains(T anEntry) {
		boolean found = false;
		int index =1;
		while(!found &&( index <= numberOfEntries)) {
			if( anEntry.equals(list[index])) {
				found = true;
			}
			index++;
		}
		return found;
	}

	@Override
	public int getLength() {
		return numberOfEntries;
	}

	@Override
	public boolean isEmpty() {
		return numberOfEntries == 0;
	}

}
