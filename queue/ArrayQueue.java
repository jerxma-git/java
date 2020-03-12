package queue;


public class ArrayQueue extends AbstractQueue {
    private int left = 0;
    private Object[] items = new Object[32];
	
    private int next() {
        return (left + size) % items.length;
    }
    
	protected void enqueueImplementation(Object element) {
        items[next()] = element;
        ensureCapacity(size + 1);
    }

    private void ensureCapacity(int capacity) {
        if (capacity < items.length - 1) {
            return;
        }
        Object[] newItems = new Object[2 * capacity];
		for(int j = 0; j <= size ; j++) {
            newItems[j] = items[left];
            left = (left + 1) % items.length;
        }
        items = newItems;
        left = 0;
    }

    protected void dequeueImplementation() {
        left = (left + 1) % items.length;
    }

    protected Object elementImplementation() {
		return items[left];
    }
    
	protected void clearImplementation() {
		items = new Object[32];
        left = 0;
	}
	
	protected Object[] toArr(Object[] array) {
        for (int i = 0; i < size; i++) {
            array[i] = items[(left + i) % items.length];
        }
        return array;
    }
}