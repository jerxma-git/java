package queue;

public abstract class AbstractQueue implements Queue {
    protected int size = 0;

    
    protected abstract void enqueueImpl(Object element);
    protected abstract Object dequeueImpl();
    protected abstract void clearImpl();
    protected abstract Object elementImpl();
    
    public Object element() {
        assert size > 0;
        return elementImpl();
    }

    public void enqueue(Object element) {
        assert element != null;
        enqueueImpl(element);
        size++;
    }

    public Object dequeue() {
        assert size > 0;
        Object dequeued = dequeueImpl();
        size--;
        return dequeued;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    
    public void clear() {
        clearImpl();
        size = 0;
    }

    public Object[] toArray() {
        Object[] arr = new Object[size];
        for (int i = 0; i < size; i++) {
            arr[i] = dequeue();
            enqueue(arr[i]);
        }
        return arr;
    }


}