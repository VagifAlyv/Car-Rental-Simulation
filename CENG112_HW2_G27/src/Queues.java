public class Queues<T> implements QueueInterface<T> {

    private T[] queue;
    private int frontIndex;
    private int backIndex;
    private int numberOfItems;
    
    private static final int DEFAULT_CAPACITY = 50;
    
    private static final int MAX_CAPACITY = 10000;
    
    
    public Queues() {
        this(DEFAULT_CAPACITY);
    }

    public Queues(int initialCapacity) {
        @SuppressWarnings("unchecked")
        T[] tempQueue = (T[]) new Object[initialCapacity + 1];
        queue = tempQueue;
        frontIndex = 0;
        backIndex = initialCapacity;
        numberOfItems = 0;
    }

    @Override
    public void enqueue(T newEntry) {
        ensureCapacity();

        backIndex = (backIndex + 1) % queue.length;
        queue[backIndex] = newEntry;

        numberOfItems++;
    }

    private void ensureCapacity() {
        if (frontIndex == ((backIndex + 2) % queue.length)) {
            T[] oldQueue = queue;
            int oldSize = oldQueue.length;
            int newSize = 2 * oldSize;

            @SuppressWarnings("unchecked")
            T[] tempQueue = (T[]) new Object[newSize];
            queue = tempQueue;

            for (int i = 0; i < oldSize - 1; i++) {
                queue[i] = oldQueue[frontIndex];
                frontIndex = (frontIndex + 1) % oldSize;
            }

            frontIndex = 0;
            backIndex = oldSize - 2;
        }
    }

    @Override
    public T dequeue() {
        if (isEmpty()) {
            return null;
        } else {
            T front = queue[frontIndex];
            queue[frontIndex] = null;
            frontIndex = (frontIndex + 1) % queue.length;
            numberOfItems--;
            return front;
        }
    }

    @Override
    public T getFront() {
        if (isEmpty()) {
            return null;
        } else {
            return queue[frontIndex];
        }
    }

    @Override
    public boolean isEmpty() {
        return frontIndex == ((backIndex + 1) % queue.length);
    }

    @Override
    public void clear() {
        while (!isEmpty()) {
            dequeue();
        }
    }

    public int size(){
        return numberOfItems;
    }

}
