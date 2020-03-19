package queue;

// def a[1]..a[size] - elements of the queue; R - returned value
// inv : size >= 0 && for i in [1..size] a[i] != null

public abstract class AbstractQueue implements Queue {
    protected int size = 0;

    
    protected abstract void enqueueImpl(Object element);
    protected abstract Object dequeueImpl();
    protected abstract void clearImpl();

    protected abstract Object elementImpl();
    
    // pre  : inv && size > 0;
    // post : inv && R = a[1];
    public Object element() {
        assert size > 0;
        return elementImpl();
    }

    // pre  : inv && element != null
    // post : inv && a' = {a[1]..a[size], element} && size' = size + 1
    public void enqueue(Object element) {
        assert element != null;
        enqueueImpl(element);
        size++;
    }

    // pre  : inv && size > 0
    // post : inv && a = {a[1]..a[size - 1]} && size' = size - 1
    public Object dequeue() {
        assert size > 0;
        Object dequeued = dequeueImpl();
        size--;
        return dequeued;
    }

    // pre  : inv
    // post : inv && R = size == 0
    public boolean isEmpty() {
        return size == 0;
    }

    // pre  : inv
    // post : inv && R = size
    public int size() {
        return size;
    }

    // pre  : inv
    // post : inv && a = {}
    public void clear() {
        clearImpl();
        size = 0;
    }


}