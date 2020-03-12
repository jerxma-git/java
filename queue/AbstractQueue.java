package queue;

public abstract class AbstractQueue implements Queue {
	protected int size;

    public void enqueue(Object element) {
        assert element != null;
        enqueueImplementation(element);
        size++;
    }

    protected abstract void enqueueImplementation(Object element);

    public Object element() {
        assert size > 0;
        return elementImplementation();
    }

    protected abstract Object elementImplementation();

    public Object dequeue() {
        assert size > 0;
        Object result = element();
		dequeueImplementation();
        size--;
        return result;
    }

    protected abstract void dequeueImplementation();

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }
     
	 public void clear() {
        size = 0;
		clearImplementation();
    }
	
	protected abstract void clearImplementation();
    
	public Object[] toArray() {
		Object array[] = new Object[size];
		return toArr(array);
	}
	
	protected abstract Object[] toArr(Object[] array);
}